package lesson1.entity;

import lesson1.interfaces.Jumpable;
import lesson1.interfaces.Runnable;

/**
 * Abstract class for all athlete entity.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public abstract class Athlete implements Runnable, Jumpable
{
    private String name;

    private double jumpLimit;
    private int runLimit;

    private boolean stopsCompetition = false;

    public Athlete(String name)
    {
        this.name = name;
    }

    public Athlete(String name, double jumpLimit, int runLimit)
    {
        this.name = name;
        this.jumpLimit = jumpLimit;
        this.runLimit = runLimit;
    }

    public abstract String getSalutation();

    @Override
    public void jump(Wall wall)
    {
        if (this.checkParticipation())
        {
            return;
        }

        if (wall == null)
        {
            this.printInfo(String.format("%s successfully jumped in place...", this.getSalutation()));
            return;
        }

        if (this.getJumpLimit() < wall.getHigh())
        {
            this.printInfo(String.format("%s could not jump %s...", this.getSalutation(), wall.toString()));
            this.printInfo(String.format("%s stops the competition!", this.getSalutation()));
            this.setStopsCompetition(true);
        }
        else
        {
            this.printInfo(String.format("%s successfully jumped %s...", this.getSalutation(), wall.toString()));
        }
    }

    @Override
    public void run(Track track)
    {
        if (this.checkParticipation())
        {
            return;
        }

        if (track == null)
        {
            this.printInfo(String.format("%s successfully ran some distance...", this.getSalutation()));
            return;
        }

        if (this.getRunLimit() < track.getDistance())
        {
            this.printInfo(String.format("%s could not ran %s...", this.getSalutation(), track.toString()));
            this.printInfo(String.format("%s stops the competition!", this.getSalutation()));
            this.setStopsCompetition(true);
        }
        else
        {
            this.printInfo(String.format("%s successfully ran %s...", this.getSalutation(), track.toString()));
        }
    }

    private boolean checkParticipation()
    {
        if (this.isStopsCompetition())
        {
            this.printInfo(String.format("%s stops already the competition!", this.getSalutation()));
        }

        return this.isStopsCompetition();
    }

    public void printInfo(String message)
    {
        System.out.println(message);
    }

    @Override
    public String toString()
    {
        return this.getSalutation() + String.format(": Jumplimit: %1$,.1f", this.getJumpLimit()) + String.format("; Runlimit: %d", this.getRunLimit());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public double getJumpLimit()
    {
        return jumpLimit;
    }

    @Override
    public void setJumpLimit(double jumpLimit)
    {
        this.jumpLimit = jumpLimit;
    }

    @Override
    public int getRunLimit()
    {
        return runLimit;
    }

    @Override
    public void setJumpLimit(int runLimit)
    {
        this.runLimit = runLimit;
    }

    public boolean isStopsCompetition() {
        return stopsCompetition;
    }

    public void setStopsCompetition(boolean stopsCompetition) {
        this.stopsCompetition = stopsCompetition;
    }
}
