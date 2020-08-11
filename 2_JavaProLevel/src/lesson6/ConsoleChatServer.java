package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static lesson6.CommConsts.*;

/**
 * Chat server console implementation.
 *
 * @author Valerij Krauter
 * @created 09.08.2020
 */
public class ConsoleChatServer
{
    private Scanner scanner = new Scanner(System.in);

    private DataOutputStream outputStream;

    public ConsoleChatServer()
    {
        try
        {
            System.out.println("Open connection");
            this.openConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void openConnection() throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        System.out.println("Server started. Wait for client...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        System.out.println("\nStart chat...");

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        this.outputStream = new DataOutputStream(socket.getOutputStream());

        // Client-Chat-Thread
        new Thread(() ->
        {
            try
            {
                while (true)
                {
                    String str = inputStream.readUTF();

                    if (!this.processMessage(str, false))
                    {
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            System.exit(0);
        }).start();

        // Server-Chat-Thread
        new Thread(() ->
        {
            while (true)
            {
                try
                {
                    if (!this.processMessage("", true))
                    {
                        break;
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private boolean processMessage(String message, boolean serverCall) throws IOException
    {
        String msg = serverCall
                ? this.scanner.next()
                : message;

        if (msg.equals(END_MESSAGE))
        {
            this.outputStream.writeUTF(msg);
            return false;
        }

        if (serverCall)
        {
            this.outputStream.writeUTF(msg);
        }
        else
        {
            System.out.println(msg);
        }

        return true;
    }

    public static void main(String[] args)
    {
        new ConsoleChatServer();
    }
}
