package lesson1;

import lesson1.entity.Athlete;
import lesson1.entity.Cat;
import lesson1.entity.Human;
import lesson1.entity.Robot;

import java.util.Random;

/**
 * Mock class for Athlete.
 *
 * <author>Valerij Krauter</author>
 * <date>22.07.2020</date>
 */
public class AthleteMock
{
    public static final int MAX_NUMBER = 10;

    private Random random = new Random();

    private static final String[] HUMAN_NAMES = new String[]
            {
                    "Chita Brita",
                    "Dory Merlin",
                    "Chip Moldun",
                    "Zig Zuk",
                    "Brave Mirida",
                    "Dusty Polejpole",
                    "Mater Radiatorspringsovskij",
                    "Alex Lion",
                    "Dash Paar",
                    "Lotus Po"
            };

    private static final String[] CAT_NAMES = new String[]
            {
                    "Wolly Bally",
                    "Kuschel Kneul",
                    "Kishka Micka",
                    "Kot Barbarosa",
                    "Blacky Black",
                    "Seals Silovich",
                    "Tortie Cat",
                    "Mur Murma",
                    "Schurr Bayun",
                    "Lovely Pot"
            };

    private static final String[] ROBOT_NAMES = new String[]
            {
                    "Karda Moon",
                    "U2B2",
                    "4ZBJO",
                    "Gromozeka",
                    "Zaznajka",
                    "Sim Udostover",
                    "Bamble Bee",
                    "Some Prime",
                    "On Mini",
                    "Barbadon"
            };

    private static AthleteMock instance = new AthleteMock();

    private AthleteMock()
    {

    }

    public static AthleteMock getInstance()
    {
        return instance;
    }

    public Athlete[] getAthletes(int numberEachTypes)
    {
        int number = numberEachTypes;
        if (number > MAX_NUMBER)
        {
            number = MAX_NUMBER;
        }

        Athlete[] athletes = new Athlete[number * 3];

        for (int i = 0; i < number; i++)
        {
            athletes[i] = new Human(HUMAN_NAMES[i], this.getHigh(), this.getDistance());
            athletes[i + number] = new Cat(CAT_NAMES[i], this.getHigh(), this.getDistance());
            athletes[i + 2 * number] = new Robot(ROBOT_NAMES[i], this.getHigh(), this.getDistance());
        }

        return athletes;
    }

    private double getHigh()
    {
        return (this.random.nextInt(30) + 1) / 10.0;
    }

    private int getDistance()
    {
        return (this.random.nextInt(10) + 1) * 100;
    }
}
