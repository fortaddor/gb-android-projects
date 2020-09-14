package lesson5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static lesson5.RaceConsts.*;

public class RaceGame
{
    public static void main(String[] args)
    {
        System.out.println("IMPORTANT ANNOUNCEMENT >>> Preparation!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        ConcurrencyProvider concurrencyProvider = new ConcurrencyProvider(3);

        for (int i = 0; i < cars.length; i++)
        {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), concurrencyProvider);
        }

        for (int i = 0; i < cars.length; i++)
        {
            new Thread(cars[i]).start();
        }

        try
        {
            concurrencyProvider.getRaceStartCountDownLatches().await();
            System.out.println("IMPORTANT ANNOUNCEMENT >>> Race starts!!!");

            concurrencyProvider.getRaceFinishCountDownLatches().await();
            System.out.println("IMPORTANT ANNOUNCEMENT >>> Race finished!!!");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
