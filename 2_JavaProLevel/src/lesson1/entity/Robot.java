package lesson1.entity;

/**
 * Entity Robot.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public class Robot extends Athlete
{
    public Robot(String name)
    {
        super(name);
    }

    public Robot(String name, double jumpLimit, int runLimit)
    {
        super(name, jumpLimit, runLimit);
    }

    @Override
    public String getSalutation()
    {
        return String.format("%s %s", this.getClass().getSimpleName(), this.getName());
    }
}
