package common;

public class ChatConsts
{
    public static final String STR_EMPTY = "";
    public static final String STR_BLANK = " ";
    public static final String STR_SPLIT = "\\s";
    public static final String STR_LF = "\n";

    public static final String KEY_AUTH = "/auth";
    public static final String KEY_AUTHOK = "/authok";
    public static final String KEY_END = "/end";
    public static final String KEY_CLIENTS = "/clients";
    public static final String KEY_PRIV = "/priv";
    public static final String KEY_EXCEPTION = "/exception";

    public static final String FORMAT_LOGIN_3_PARAMS = "%s %s %s";
    public static final String FORMAT_AUTHOK_2_PARAMS = "%s %s";

    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8080;

    public static final int AUTH_TIMEOUT = 120;
}
