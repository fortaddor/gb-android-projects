package lesson6;

import java.util.Random;

/**
 * Home work to lesson6
 *
 * <author>Valerij Krauter</author>
 * <date>14.07.2020</date>
 */
public class Lesson6
{
    private final static int SIZE = 10;
    private final static int MAX_AGE = 16;

    // Distance in meter, High in decimeter
    private final static int RUN_DISTANCE = 1000;
    private final static int JUMP_HIGH = 30;
    private final static int SWIM_DISTANCE = 15;

    private final static int DOG_RUN_LIMIT_400 = 400;
    private final static int DOG_RUN_LIMIT_600 = 600;

    private Animal[] animals = new Animal[SIZE];
    Random random = new Random();

    public static void main(String[] args)
    {
        Lesson6 lesson = new Lesson6();

        int dogNumber = lesson.random.nextInt(SIZE);

        lesson.createDogs(dogNumber);
        lesson.createCats(SIZE - dogNumber);

        lesson.processActions();
    }

    private void createDogs(int number)
    {
        for (int i = 0; i < number; i++)
        {
            int limit = random.nextInt(3) + 1;

            switch (limit)
            {
                case 2:
                    animals[i] = new Dog(AnimalMock.NAMES[i], AnimalMock.COLORS[random.nextInt(AnimalMock.COLORS.length)], random.nextInt(MAX_AGE) + 1, DOG_RUN_LIMIT_400);
                    break;

                case 3:
                    animals[i] = new Dog(AnimalMock.NAMES[i], AnimalMock.COLORS[random.nextInt(AnimalMock.COLORS.length)], random.nextInt(MAX_AGE) + 1, DOG_RUN_LIMIT_600);
                    break;

                default:
                    animals[i] = new Dog(AnimalMock.NAMES[i], AnimalMock.COLORS[random.nextInt(AnimalMock.COLORS.length)], random.nextInt(MAX_AGE) + 1);
                    break;
            }

        }
    }

    private void createCats(int number)
    {
        int startIdx = SIZE - number;
        for (int i = startIdx; i < SIZE; i++)
        {
            animals[i] = new Cat(AnimalMock.NAMES[i], AnimalMock.COLORS[random.nextInt(AnimalMock.COLORS.length)], random.nextInt(MAX_AGE) + 1);
        }
    }

    private void processActions()
    {
        for (int i = 0; i < SIZE; i++)
        {
            animals[i].printInfo();

            animals[i].run(random.nextInt(RUN_DISTANCE) + 1);
            animals[i].jump(random.nextInt(JUMP_HIGH) / 10 + 0.1);
            animals[i].swim(random.nextInt(SWIM_DISTANCE) + 1);
        }
    }
}
