package lesson6;

import java.awt.*;

/**
 * Entity Cat.
 *
 * <author>Valerij Krauter</author>
 * <date>14.07.2020</date>
 */
public class Cat extends Animal
{
    public final static int MAX_RUN_DISTANCE = 200;
    public final static double MAX_JUMP_HIGH = 2;

    public Cat(String name, String color, int age)
    {
        super(name, color, age);
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
}
