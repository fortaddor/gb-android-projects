package lesson7;

import lesson6.AnimalMock;

import java.util.Random;

/**
 * Entity Cat.
 *
 * <author>Valerij Krauter</author>
 * <date>20.07.2020</date>
 */
public class Cat extends Animal
{
    public final static int MAX_RUN_DISTANCE = 200;
    public final static double MAX_JUMP_HIGH = 2;

    private int appetite;
    private boolean wellfed;

    public Cat(String name, String color, int age, int appetite)
    {
        super(name, color, age);

        this.appetite = appetite;
    }

    public void eat(Plate plate)
    {
        if (plate.getFoodAmount() < this.getAppetite())
        {
            this.setWellfed(false);
            return;
        }

        plate.reduce(this.getAppetite());
        this.setWellfed(true);
    }

    public void printAppetite()
    {
        System.out.println("Appetite: " + this.getAppetite());
    }

    public void printWelfed()
    {
        System.out.println(this.isWellfed() ? "Cat is wel-fed" : "Cat isn't wel-fed");
    }

    @Override
    public void swim(int distance)
    {
        super.swim(distance);

        System.out.println("The cats don't like swim!");
    }

    public int getMaxRunDistance()
    {
        return MAX_RUN_DISTANCE;
    }

    public double getMaxJumpHigh()
    {
        return MAX_JUMP_HIGH;
    }

    public Integer getMaxSwimDistance()
    {
        return null;
    }

    @Override
    protected String getBreed()
    {
        return "CAT";
    }

    public int getAppetite()
    {
        return appetite;
    }

    public void setAppetite(int appetite)
    {
        this.appetite = appetite;
    }

    public boolean isWellfed()
    {
        return wellfed;
    }

    public void setWellfed(boolean wellfed)
    {
        this.wellfed = wellfed;
    }
}