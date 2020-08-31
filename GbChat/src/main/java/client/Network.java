package client;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static common.ChatConsts.*;

public class Network implements Closeable
{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Callback callOnMsgReceived;
    private Callback callOnAuthenticated;
    private Callback callOnException;
    private Callback callOnCloseConnection;

    public void setCallOnMsgReceived(Callback callOnMsgReceived)
    {
        this.callOnMsgReceived = callOnMsgReceived;
    }

    public void setCallOnAuthenticated(Callback callOnAuthenticated)
    {
        this.callOnAuthenticated = callOnAuthenticated;
    }

    public void setCallOnException(Callback callOnException)
    {
        this.callOnException = callOnException;
    }

    public void setCallOnCloseConnection(Callback callOnCloseConnection)
    {
        this.callOnCloseConnection = callOnCloseConnection;
    }

    public void sendAuth(String login, String password)
    {
        try
        {
            this.connect();
            this.out.writeUTF(String.format(FORMAT_LOGIN_3_PARAMS, KEY_AUTH, login, password));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void connect()
    {
        if (this.socket != null && !this.socket.isClosed())
        {
            return;
        }

        try
        {
            this.socket = new Socket(SERVER_HOST, SERVER_PORT);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

            Thread clientListenerThread = new Thread(() -> {
                try
                {
                    while (true)
                    {
                        String msg = this.in.readUTF();
                        String[] tokens = msg.split(STR_SPLIT);

                        if (tokens[0].startsWith(KEY_AUTHOK))
                        {
                            this.callOnAuthenticated.callback(tokens[1]);
                            break;
                        }
                        else
                        {
                            this.callOnException.callback(msg);
                        }
                    }

                    while (true)
                    {
                        String msg = this.in.readUTF();
                        if (msg.equals(KEY_END))
                        {
                            break;
                        }
                        this.callOnMsgReceived.callback(msg);
                    }
                }
                catch (IOException e)
                {
                    this.callOnException.callback("Connection to the server interrupted");
                }
                finally
                {
                    this.close();
                }
            });

            clientListenerThread.setDaemon(true);
            clientListenerThread.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean sendMsg(String msg)
    {
        if (this.out == null || this.socket == null || this.socket.isClosed())
        {
            this.callOnException.callback("Connection with the server not created");
            return false;
        }

        try
        {
            this.out.writeUTF(msg);

            String[] tokens = msg.split(STR_SPLIT);
            if (tokens[0].equals(KEY_END))
            {
                this.close();
            }
            else if (tokens[0].equals(KEY_PRIV))
            {
                this.callOnMsgReceived.callback(msg);
            }
            else if (tokens[0].equals(KEY_NICK))
            {
                this.callOnMsgReceived.callback(msg);
                this.close();
            }

            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close()
    {
        this.callOnCloseConnection.callback();
        this.close(this.in, this.out, this.socket);
    }

    private void close(Closeable... objects)
    {
        for (Closeable o : objects)
        {
            try
            {
                o.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}