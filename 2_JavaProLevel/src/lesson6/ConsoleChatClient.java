package lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static lesson6.CommConsts.*;

/**
 * Chat client console implementation.
 *
 * @author Valerij Krauter
 * @created 09.08.2020
 */
public class ConsoleChatClient
{
    private Scanner scanner = new Scanner(System.in);

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ConsoleChatClient()
    {
        try
        {
            System.out.println("Open connection. Thread: " + Thread.currentThread().getName());
            this.openConnection();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void openConnection() throws IOException
    {
        this.socket = new Socket(SERVER_HOST, SERVER_PORT);
        this.inputStream = new DataInputStream(this.socket.getInputStream());
        this.outputStream = new DataOutputStream(this.socket.getOutputStream());

        System.out.println("\nStart chat...");

        // Server-Chat-Thread
        new Thread(() ->
        {
            while (true)
            {
                try
                {
                    String msg = this.inputStream.readUTF();

                    if (!this.processMessage(msg, true))
                    {
                        break;
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            System.exit(0);
        }).start();

        // Client-Chat-Thread
        new Thread(() ->
        {
            try
            {
                while (true)
                {
                    if (!this.processMessage("", false))
                    {
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    private boolean processMessage(String message, boolean serverCall) throws IOException
    {
        String msg = serverCall
                ? message
                : this.scanner.next();

        if (msg.equals(END_MESSAGE))
        {
            this.outputStream.writeUTF(msg);
            return false;
        }

        if (serverCall)
        {
            System.out.println(msg);
        }
        else
        {
            this.outputStream.writeUTF(msg);
        }

        return true;
    }

    public static void main(String[] args)
    {
        new ConsoleChatClient();
    }
}
