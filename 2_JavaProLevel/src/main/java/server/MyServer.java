package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static common.ChatConsts.*;

public class MyServer
{
    private Map<String, ClientHandler> clients;
    private AuthService authService;

    public MyServer()
    {
        try (ServerSocket server = new ServerSocket(SERVER_PORT))
        {
            authService = new BaseAuthService();
            authService.start();
            clients = new HashMap<>();

            while (true)
            {
                System.out.println("Server wait for client connection");
                Socket socket = server.accept();
                System.out.println("Client connected");

                new ClientHandler(this, socket);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error on server");
            e.printStackTrace();
        }
        finally
        {
            if (this.authService != null)
            {
                this.authService.stop();
            }
        }
    }

    public synchronized boolean isUserOnline(String nick)
    {
        return this.clients.containsKey(nick);
    }

    public synchronized void broadcastMsg(String msg, String to)
    {
        for (ClientHandler client : this.clients.values())
        {
            if (to.equals(STR_EMPTY))
            {
                client.sendMsg(msg);
            }
            else if (client.getName().equals(to))
            {
                client.sendMsg(msg);
                break;
            }
        }
    }

    public synchronized void broadcastMsg(String from, String msg, String to)
    {
        this.broadcastMsg(this.formatMessage(from, msg), to);
    }

    public synchronized void unsubscribe(ClientHandler o)
    {
        this.clients.remove(o.getName());
        this.broadcastClients();
        this.broadcastMsg(o.getName() + " left chat", STR_EMPTY);
    }

    public synchronized void subscribe(ClientHandler o)
    {
        this.clients.put(o.getName(), o);
        this.broadcastClients();
        this.broadcastMsg(o.getName() + " entered chat", STR_EMPTY);
    }

    public synchronized void broadcastClients()
    {
        StringBuilder builder = new StringBuilder(KEY_CLIENTS + STR_BLANK);

        for (String nick : this.clients.keySet())
        {
            builder.append(nick).append(STR_BLANK);
        }

        this.broadcastMsg(builder.toString(), STR_EMPTY);
    }

    private String formatMessage(String from, String msg)
    {
        return from + ": " + msg;
    }

    public AuthService getAuthService()
    {
        return this.authService;
    }
}