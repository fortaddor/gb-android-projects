package server;

import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BaseAuthService implements AuthService
{
    private static final Logger logger = Logger.getLogger(BaseAuthService.class.getName());

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

        this.logger.info("Authentication service started");
    }

    @Override
    public void stop()
    {
        this.dbProvider.disconnect();

        this.logger.info("Authentication service stopped");
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
            this.logger.severe(e.getStackTrace().toString());
        }

        return user == null ? new User() : user;
    }

    @Override
    public void changeNickname(String user, String pass, String newNickname) throws SQLException
    {
        this.dbProvider.updateNickname(user, pass, newNickname);
    }
}