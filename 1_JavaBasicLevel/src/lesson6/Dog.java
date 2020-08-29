package lesson6;

/**
 * Entity Cat.
 *
 * <author>Valerij Krauter</author>
 * <date>14.07.2020</date>
 */
public class Dog extends Animal
{
    public final static int MAX_RUN_DISTANCE = 500;
    public final static double MAX_JUMP_HIGH = 0.5;
    public final static int MAX_SWIM_DISTANCE = 10;

    private int runLimit;

    public Dog(String name, String color, int age)
    {
        super(name, color, age);
    }

    public Dog(String name, String color, int age, int runLimit)
    {
        this(name, color, age);

        this.runLimit = runLimit;
    }

    @Override
    public int getMaxRunDistance()
    {
        return this.hasRunLimit()
                ? this.getRunLimit()
                : MAX_RUN_DISTANCE;
    }

    @Override
    public double getMaxJumpHigh()
    {
        return MAX_JUMP_HIGH;
    }

    @Override
    public Integer getMaxSwimDistance()
    {
        return MAX_SWIM_DISTANCE;
    }

    @Override
    protected String getBreed()
    {
        return "DOG";
    }

    @Override
    public void printInfo()
    {
        super.printInfo();

        if (this.hasRunLimit())
        {
            System.out.println("Run limit: " + this.getRunLimit());
        }
    }

    private boolean hasRunLimit()
    {
        return this.getRunLimit() > 0;
    }

    public int getRunLimit()
    {
        return runLimit;
    }

    public void setRunLimit(int runLimit)
    {
        this.runLimit = runLimit;
    }
}
