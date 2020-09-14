package lesson5;

public class Tunnel extends Stage
{
    public Tunnel()
    {
        this.length = 80;
        this.description = "Tunnel " + length + " meter";
    }

    @Override
    public void go(Car car)
    {
        try
        {
            try
            {
                System.out.println(car.getName() + " preparing for stage (waiting): " + this.getDescription());
                car.getSemaphore().acquire();
                System.out.println(car.getName() + " starts stage: " + this.getDescription());

                Thread.sleep(length / car.getSpeed() * 1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                System.out.println(car.getName() + " finished stage: " + this.getDescription());

                car.getSemaphore().release();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
