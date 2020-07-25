package lesson7;

import java.util.Locale;

/**
 * Abstract class for animals.
 *
 * <author>Valerij Krauter</author>
 * <date>20.07.2020</date>
 */
public abstract class Animal
{
    private String name;
    private String color;
    private int age;

    public abstract int getMaxRunDistance();

    public abstract double getMaxJumpHigh();

    public abstract Integer getMaxSwimDistance();

    protected abstract String getBreed();

    public Animal(String name, String color, int age)
    {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public void run(int distance)
    {
        this.printAction(String.format("run %d meter", distance), distance <= this.getMaxRunDistance());
    }

    public void jump(double high)
    {
        this.printAction(String.format(Locale.GERMAN,"jump %1$,.1f meter", high), high <= this.getMaxJumpHigh());
    }

    public void swim(int distance)
    {
        this.printAction(String.format("swim %d meter", distance), (this.getMaxSwimDistance() != null && distance <= this.getMaxSwimDistance()));
    }

    public void printAction(String action, boolean success)
    {
        System.out.println(action + ": " + success);
    }

    public void printInfo()
    {
        System.out.println("\n" + this.getBreed() + "\nName: " + this.getName() + ", Color: " + this.getColor() + ", Age: " + this.getAge());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
