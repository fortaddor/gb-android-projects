package lesson5;

public class Road extends Stage
{
    public Road(int length)
    {
        this.length = length;
        this.description = "Track " + length + " meter";
    }

    @Override
    public void go(Car car)
    {
        try
        {
            System.out.println(car.getName() + " starts stage: " + this.getDescription());

            Thread.sleep(this.length / car.getSpeed() * 1000);

            System.out.println(car.getName() + " finished stage: " + this.getDescription());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
