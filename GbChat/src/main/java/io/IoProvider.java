package io;

import server.MyServer;

import java.io.*;
import java.util.logging.Logger;

import static common.ChatConsts.*;

public class IoProvider
{
    public static final String HISTORY_FOLDER = "chathistory";

    private static final Logger logger = Logger.getLogger(IoProvider.class.getName());

    public IoProvider()
    {
    }

    public String readLines(String username, int linesNumber)
    {
        String filename = this.getFilename(username);
        File file = new File(filename);

        if (!file.exists())
        {
            return STR_EMPTY;
        }

        BufferedReader bufferedReader = null;

        try
        {
            bufferedReader = new BufferedReader(new FileReader(file));

            Object[] lines = bufferedReader.lines().toArray();
            int startIdx = lines.length <= linesNumber ? 0 : lines.length - linesNumber;

            StringBuilder linesToLoad = new StringBuilder();
            for (int i = startIdx; i < lines.length; i++)
            {
                linesToLoad.append(lines[i].toString()).append(STR_LF);
            }

            return linesToLoad.toString();
        }
        catch (IOException e)
        {
            this.logger.severe(e.getStackTrace().toString());
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    this.logger.severe(e.getStackTrace().toString());
                }
            }
        }

        return STR_EMPTY;
    }

    public void writeLines(String username, String lines)
    {
        String filename = this.getFilename(username);

        try (FileOutputStream fos = new FileOutputStream(filename,false))
        {
            fos.write(lines.getBytes());
        }
        catch (Exception e)
        {
            this.logger.severe(e.getStackTrace().toString());
        }
    }

    private String getFilename(String username)
    {
        File file = new File(HISTORY_FOLDER);
        file.mkdir();

        return String.format("%s/history_%s.txt", HISTORY_FOLDER, username);
    }
}
