package lesson1.interfaces;

import lesson1.entity.Wall;

/**
 * Interface for all subjects, they can jump.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public interface Jumpable
{
    void jump(Wall wall);

    double getJumpLimit();

    void setJumpLimit(double jumpLimit);
}
