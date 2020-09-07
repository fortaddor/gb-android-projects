package server;

import entity.User;

import java.sql.SQLException;

public interface AuthService
{
    void start();

    void stop();

    User getUserByLoginPass(String login, String pass);

    void changeNickname(String user, String pass, String newNickname) throws SQLException;
}