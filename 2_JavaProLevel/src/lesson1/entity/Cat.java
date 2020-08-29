package lesson1.entity;

/**
 * Entity Cat.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public class Cat extends Athlete
{
    public Cat(String name)
    {
        super(name);
    }

    public Cat(String name, double jumpLimit, int runLimit)
    {
        super(name, jumpLimit, runLimit);
    }

    @Override
    public String getSalutation()
    {
        return String.format("%s %s", this.getClass().getSimpleName(), this.getName());
    }
}
