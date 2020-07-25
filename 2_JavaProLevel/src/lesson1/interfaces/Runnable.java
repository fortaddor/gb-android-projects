package lesson1.interfaces;

import lesson1.entity.Track;

/**
 * Interface for all subjects, they can run.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public interface Runnable
{
    void run(Track track);

    int getRunLimit();

    void setJumpLimit(int runLimit);
}
