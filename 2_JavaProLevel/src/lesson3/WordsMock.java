package lesson3;

import java.util.Random;

/**
 * Mock class for creating of WordsArray.
 *
 * <author>Valerij Krauter</author>
 * <date>30.07.2020</date>
 */
public class WordsMock
{
    private static final String[] WORDS =
            {
                    "Zeus", "Hera", "Athena", "Aphrodite", "Artemis",
                    "Hephaistos", "Hermes", "Poseidon", "Hades", "Dionysos",
                    "Apollon", "Demeter", "Hestia", "Ares", "Herkules",
                    "Teseus", "Achilles", "Perseus", "Prometeus", "Odysseus"
            };

    private static WordsMock instance = new WordsMock();

    private Random random = new Random();

    private WordsMock()
    {
    }

    public static WordsMock getInstance()
    {
        return instance;
    }

    public String[] getWordsArray(int size)
    {
        String[] array = new String[size];

        for (int i = 0; i < size; i++)
        {
            int wIdx = this.random.nextInt(WORDS.length);

            array[i] = WORDS[wIdx];
        }

        return array;
    }
}
