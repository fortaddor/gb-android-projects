package server;

import java.io.IOException;

public class ServerApp
{
    public static void main(String[] args) throws IOException
    {
        LogUtils.getInstance().createLogManager();

        new MyServer();
    }
}