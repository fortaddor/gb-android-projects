package lesson1;

/*

    1. Создайте три класса Человек, Кот, Робот. Эти классы должны уметь бегать и прыгать (методы просто выводят
        информацию о действии в консоль). Подумайте, что вам сможет помочь не дублировать код и унифицировать эти классы
        для дальнейшей работы с ими?
    2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять
        соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал,
        не смог пробежать и т.д.).
    3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
    4. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
        Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
 */

import lesson1.entity.*;
import lesson1.interfaces.Obstacle;

import java.util.Random;

public class Lesson1
{
    private static final int MIN_SIZE_OBSTACLES = 5;

    private Random random = new Random();

    public static void main(String[] args)
    {
        Lesson1 lesson = new Lesson1();

        lesson.testSimpleJumpingAndRunning();
        lesson.testJumpingAndRunningWithWallAndTrack();

        lesson.testCompetitionWithManyObstacles();
    }

    private void testSimpleJumpingAndRunning()
    {
        System.out.println("\ntestSimpleJumpingAndRunning() \n----------------------------------------");

        Athlete[] athletes = AthleteMock.getInstance().getAthletes(1);

        for (Athlete athlete : athletes)
        {
            athlete.jump(null);
            athlete.run(null);
        }
    }

    private void testJumpingAndRunningWithWallAndTrack()
    {
        System.out.println("\n\ntestJumpingAndRunningWithWallAndTrack() \n----------------------------------------");

        Athlete[] athletes = AthleteMock.getInstance().getAthletes(1);

        Wall wall = new Wall(2);
        Track track = new Track(100);

        for (Athlete athlete : athletes)
        {
            athlete.jump(wall);
            athlete.run(track);
        }
    }

    private void testCompetitionWithManyObstacles()
    {
        System.out.println("\n\ntestCompetitionWithManyObstacles() \n----------------------------------------");

        Athlete[] athletes = AthleteMock.getInstance().getAthletes(this.random.nextInt(5) + 1);
        Obstacle[] obstacles = this.createObstacles();

        for (int i = 0; i < obstacles.length; i++)
        {
            Obstacle obstacle = obstacles[i];

            System.out.println(String.format("\n%d. %s \n-------------------------", i + 1, obstacle.getInfo()));

            int count = 0;
            for (Athlete athlete : athletes)
            {
                if (athlete.isStopsCompetition())
                {
                    continue;
                }

                count++;

                System.out.println(athlete);

                if (obstacle instanceof Wall)
                {
                    athlete.jump((Wall)obstacle);
                }
                else if (obstacle instanceof Track)
                {
                    athlete.run((Track)obstacle);
                }
            }

            if (count == 0)
            {
                System.out.println("All athletes dropped out of the competition!");
            }
        }
    }

    private Obstacle[] createObstacles()
    {
        int size = random.nextInt(10) + MIN_SIZE_OBSTACLES;

        Obstacle[] obstacles = new Obstacle[size];

        for (int i = 0; i < size; i++)
        {
            boolean createWall = random.nextBoolean();

            if (createWall)
            {
                obstacles[i] = new Wall((this.random.nextInt(30) + 1) / 10.0);
            }
            else
            {
                obstacles[i] = new Track((this.random.nextInt(10) + 1) * 100);
            }
        }

        return obstacles;
    }
}
