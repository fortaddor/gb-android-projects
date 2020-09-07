package server;

import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService
{
    private DbProvider dbProvider;

    public BaseAuthService()
    {
        this.dbProvider = new DbProvider();
    }

    @Override
    public void start()
    {
        try
        {
            this.dbProvider.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();

            this.dbProvider.disconnect();
        }

        System.out.println("Authentication service started");
    }

    @Override
    public void stop()
    {
        this.dbProvider.disconnect();

        System.out.println("Authentication service stopped");
    }

    @Override
    public User getUserByLoginPass(String login, String pass)
    {
        User user = null;

        try
        {
            user = this.dbProvider.readUser(login, pass);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return user == null ? new User() : user;
    }

    @Override
    public void changeNickname(String user, String pass, String newNickname) throws SQLException
    {
        this.dbProvider.updateNickname(user, pass, newNickname);
    }
}