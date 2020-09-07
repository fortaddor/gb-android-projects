package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static common.ChatConsts.*;

public class ClientHandler
{
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;

    public ClientHandler(MyServer myServer, Socket socket)
    {
        try
        {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.name = "";

            new Thread(() -> {
                try
                {
                    this.authentication();
                    this.readMessages();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    this.closeConnection();
                }
            }).start();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Problems by creating of client handler");
        }
    }

    public void authentication() throws IOException
    {
        while (true)
        {
            String str = in.readUTF();
            if (str.startsWith(KEY_AUTH))
            {
                String[] parts = str.split(STR_SPLIT);
                String nick = this.myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null)
                {
                    if (!this.myServer.isUserOnline(nick))
                    {
                        this.sendMsg(String.format(FORMAT_AUTHOK_2_PARAMS, KEY_AUTHOK, nick));
                        this.name = nick;
                        this.myServer.subscribe(this);

                        return;
                    }
                    else
                    {
                        this.sendMsg("User is already online");
                    }
                }
                else
                {
                    this.sendMsg("Wrong user or password");
                }
            }
        }
    }

    public void readMessages() throws IOException
    {
        while (true)
        {
            String strFromClient = this.in.readUTF();
            System.out.println(name + " > " + strFromClient);

            String[] tokens = strFromClient.split(STR_SPLIT);
            if (tokens[0].equals(KEY_END))
            {
                return;
            }

            String message = strFromClient;
            String nick = STR_EMPTY;
            if (tokens[0].equals(KEY_PRIV))
            {
                nick = tokens[1];

                if (this.myServer.isUserOnline(nick))
                {
                    message = tokens[2];
                }
            }

            this.myServer.broadcastMsg(name, message, nick);
        }
    }

    public void sendMsg(String msg)
    {
        try
        {
            this.out.writeUTF(msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void closeConnection()
    {
        this.myServer.unsubscribe(this);

        try
        {
            this.in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            this.out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            this.socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getName()
    {
        return this.name;
    }
}