package lesson7;

import java.util.Random;

/**
 * Mock for automatically filling of animal objects.
 *
 * <author>Valerij Krauter</author>
 * <date>20.07.2020</date>
 */
public class AnimalMock
{
    public static final String[] NAMES = new String[]
            {
                    "Jack Sparrow",
                    "Modi Bick",
                    "Mister Cool",
                    "Sure Face",
                    "Codi Blanks",
                    "Shustraya Di",
                    "Dolbi Sur Round",
                    "Henri IV",
                    "American Pai",
                    "Ekaterina Velikaya"
            };

    public static final String[] COLORS = new String[]
            {
                    "BLACK",
                    "WHITE",
                    "BLUE",
                    "DARK GRAY",
                    "LIGHT GRAY",
                    "RED",
                    "ORANGE",
                    "YELLOW",
                    "LILA",
                    "PINK"
            };

    public static final int MAX_AGE = 15;

    public Cat[] createCats(int number)
    {
        Cat[] cats = new Cat[number];
        Random random = new Random();

        for (int i = 0; i < number; i++)
        {
            int appetite = random.nextInt(20) + 1;

            cats[i] = new Cat(AnimalMock.NAMES[i], AnimalMock.COLORS[random.nextInt(AnimalMock.COLORS.length)], random.nextInt(MAX_AGE) + 1, appetite);
        }

        return cats;
    }
}
