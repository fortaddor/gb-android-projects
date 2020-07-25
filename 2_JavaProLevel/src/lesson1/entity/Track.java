package lesson1.entity;

import lesson1.interfaces.Obstacle;

/**
 * Entity Track.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public class Track implements Obstacle
{
    private int distance;

    public Track(int distance)
    {
        this.distance = distance;
    }

    @Override
    public String toString()
    {
        return String.format("distance %d meter", this.getDistance());
    }

    @Override
    public String getInfo()
    {
        return "Track: " + toString();
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }
}
