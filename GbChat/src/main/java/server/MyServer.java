package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static common.ChatConsts.*;

public class MyServer
{
    private Map<String, ClientHandler> clients;
    private AuthService authService;
    private ExecutorService executorService;

    public MyServer()
    {
        this.authService = new BaseAuthService();
        this.clients = new HashMap<>();
        this.executorService = Executors. newCachedThreadPool();

        try (ServerSocket server = new ServerSocket(SERVER_PORT))
        {
            this.authService.start();

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
            this.stopServices();
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

    public AuthService getAuthService()
    {
        return this.authService;
    }

    public ExecutorService getExecutorService()
    {
        return this.executorService;
    }

    private String formatMessage(String from, String msg)
    {
        return from + ": " + msg;
    }

    private void stopServices()
    {
        if (this.authService != null)
        {
            this.authService.stop();
        }

        if (this.getExecutorService() != null)
        {
            this.getExecutorService().shutdown();
        }
    }
}