package lesson1.entity;

import lesson1.interfaces.Obstacle;

/**
 * Entity Wall.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public class Wall implements Obstacle
{
    private double high;

    public Wall(double high)
    {
        this.high = high;
    }

    @Override
    public String toString()
    {
        return String.format("high %1$,.1f meter", this.getHigh());
    }

    @Override
    public String getInfo()
    {
        return "Wall: " + toString();
    }

    public double getHigh()
    {
        return high;
    }

    public void setHigh(double high)
    {
        this.high = high;
    }
}
