package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class LogUtils
{
    private static LogUtils instance = new LogUtils();

    private LogUtils()
    {
        this.init();
    }

    public static LogUtils getInstance()
    {
        return instance;
    }

    public void createLogManager() throws IOException
    {
        LogManager manager = LogManager.getLogManager();
        manager.readConfiguration(new FileInputStream("logging.properties"));
    }

    private void init()
    {
        File dir = new File("logs");

        dir.mkdir();
    }
}
