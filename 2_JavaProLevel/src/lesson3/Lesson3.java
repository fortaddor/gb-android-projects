package lesson3;

import java.util.*;

/*
    1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        Посчитать, сколько раз встречается каждое слово.
    2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
        В этот телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать
        номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
        (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
*/
public class Lesson3
{
    public static void main(String[] args)
    {
        Lesson3 lesson = new Lesson3();

        lesson.task1UniqueWordsAndWordCount();
        lesson.task2Phonebook();
    }

    // Task 1
    private void task1UniqueWordsAndWordCount()
    {
        System.out.println("Task 1: task1UniqueWordsAndWordCount \n--------------------------------------------");

        String[] words = WordsMock.getInstance().getWordsArray(20);

        System.out.println("Original array");
        System.out.println(Arrays.toString(words));

        // Find unique words
        Set<String> uniqueWords = new HashSet<>();
        Collections.addAll(uniqueWords, words);

        System.out.println("\nUnique words");
        System.out.println(uniqueWords.toString());

        System.out.println("\nLength of Array: " + words.length);
        System.out.println("Length of Set: " + uniqueWords.size());

        // Count repeated words
        Map<String, Integer> repeatedWords = new HashMap<>();
        for (String word : words)
        {
            if (repeatedWords.containsKey(word))
            {
                int newCount = repeatedWords.get(word) + 1;
                repeatedWords.replace(word, newCount);
            }
            else
            {
                repeatedWords.put(word, 1);
            }
        }

        System.out.println("\nRepeated and counted words");
        System.out.println(repeatedWords.toString());

        System.out.println("\nLength of Array: " + words.length);
        System.out.println("Length of Map: " + repeatedWords.size());
    }

    // Task 2
    /*
        В этом решении все номера одного человека а также его однофамильцев будут внесены в одну запись, что не может
        быть верным в реальной жизни.
        Но для внесения однофамильцев в различные записи недостаточно регистрационных данных.
        При внесении второго номера телефона к уже существующей записи конкретного человека только по его фамилии
        не представляется возможным.
     */
    private void task2Phonebook()
    {
        System.out.println("Task 1: task2Phonebook \n--------------------------------------------");

        Phonebook phonebook = new Phonebook();

        phonebook.add("Smith", "+49179536328");
        phonebook.add("Smith", "+49179554545");
        phonebook.add("Miller", "+49176989898");

        System.out.println("Smith: " + phonebook.get("Smith"));
        System.out.println("Miller: " + phonebook.get("Miller"));
    }
}
