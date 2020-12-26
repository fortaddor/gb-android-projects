package de.fortaestro.weatherapp.utils;

import java.util.Random;

public final class RandomUtils
{
    private static RandomUtils instance = new RandomUtils();
    private Random random = new Random();

    private  RandomUtils()
    {
    }

    public static RandomUtils getInstance()
    {
        return instance;
    }

    public int getInt(int base, int range)
    {
        return base + this.random.nextInt(range + 1);
    }
}
