package client;

@FunctionalInterface
public interface Callback
{
    void callback(String... args);
}