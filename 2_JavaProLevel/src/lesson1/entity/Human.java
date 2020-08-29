package lesson1.entity;

/**
 * Entity Human.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public class Human extends Athlete
{
    public Human(String name)
    {
        super(name);
    }

    public Human(String name, double jumpLimit, int runLimit)
    {
        super(name, jumpLimit, runLimit);
    }

    @Override
    public String getSalutation()
    {
        return String.format("%s %s", this.getClass().getSimpleName(), this.getName());
    }
}
