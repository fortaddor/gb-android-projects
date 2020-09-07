package entity;

import static common.ChatConsts.STR_EMPTY;

public class User
{
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NICKNAME = "nickname";

    private final String username;
    private final String password;
    private final String nickname;

    public User()
    {
        this.username = STR_EMPTY;
        this.password = STR_EMPTY;
        this.nickname = STR_EMPTY;
    }

    public User(String username, String password, String nickname)
    {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getNickname()
    {
        return this.nickname;
    }
}
