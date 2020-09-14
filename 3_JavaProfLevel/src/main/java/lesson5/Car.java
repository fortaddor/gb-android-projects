package lesson5;

import java.util.concurrent.Semaphore;

public class Car implements Runnable
{
    private static int CARS_COUNT;

    static
    {
        CARS_COUNT = 0;
    }

    private final Race race;
    private final int speed;
    private final String name;
    private final ConcurrencyProvider concurrencyProvider;

    public Car(Race race, int speed, ConcurrencyProvider concurrencyProvider)
    {
        this.race = race;
        this.speed = speed;
        this.concurrencyProvider = concurrencyProvider;

        CARS_COUNT++;

        this.name = "Car #" + CARS_COUNT;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(this.getName() + " preparing");

            Thread.sleep(500 + (int)(Math.random() * 800));
            this.concurrencyProvider.getCyclicBarrier().await();

            System.out.println(this.getName() + " ready");
            this.concurrencyProvider.getRaceStartCountDownLatches().countDown();
            this.concurrencyProvider.getCyclicBarrier().await();

            Thread.sleep(1000);

            for (int i = 0; i < this.race.getStages().size(); i++)
            {
                this.race.getStages().get(i).go(this);
            }

            this.race.setWinner(this);

            this.concurrencyProvider.getRaceFinishCountDownLatches().countDown();
            this.concurrencyProvider.getCyclicBarrier().await();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getName()
    {
        return this.name;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public Semaphore getSemaphore()
    {
        return this.concurrencyProvider.getSemaphore();
    }
}
