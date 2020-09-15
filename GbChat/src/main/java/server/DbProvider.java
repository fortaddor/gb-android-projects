package server;

import entity.User;

import java.sql.*;
import java.util.logging.Logger;

public class DbProvider
{
    private static final Logger logger = Logger.getLogger(DbProvider.class.getName());

    private Connection connection;
    private PreparedStatement preparedStmt;

    public DbProvider()
    {
    }

    public void connect() throws ClassNotFoundException, SQLException
    {
        Class.forName("org.sqlite.JDBC");

        this.connection = DriverManager.getConnection("jdbc:sqlite:ChatDB.db");
    }

    public void disconnect()
    {
        try
        {
            this.preparedStmt.close();
        }
        catch (SQLException e)
        {
            this.logger.severe(e.getStackTrace().toString());
        }

        try
        {
            this.connection.close();
        }
        catch (SQLException e)
        {
            this.logger.severe(e.getStackTrace().toString());
        }
    }

    public User readUser(String username, String password) throws SQLException
    {
        this.preparedStmt = this.connection.prepareStatement(String.format("SELECT * FROM users WHERE %s = ? AND %s = ? ;",
                User.COL_USERNAME, User.COL_PASSWORD));
        this.preparedStmt.setString(1, username);
        this.preparedStmt.setString(2, password);

        ResultSet rs = this.preparedStmt.executeQuery();

        User user = null;
        if (rs.next())
        {
            user = new User(rs.getString(User.COL_USERNAME), rs.getString(User.COL_PASSWORD), rs.getString(User.COL_NICKNAME));
        }

        rs.close();

        return user;
    }

    public void updateNickname(String username, String password, String newNickname) throws SQLException
    {
        this.preparedStmt = this.connection.prepareStatement(String.format("UPDATE users SET %s = ? WHERE %s = ? AND %s = ? ;",
                User.COL_NICKNAME, User.COL_USERNAME, User.COL_PASSWORD));
        this.preparedStmt.setString(1, newNickname);
        this.preparedStmt.setString(2, username);
        this.preparedStmt.setString(3, password);

        this.preparedStmt.executeUpdate();
    }
}
