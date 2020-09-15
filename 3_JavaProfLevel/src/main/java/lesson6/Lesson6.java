package lesson6;

import java.util.Arrays;
import java.util.Random;

import static lesson6.Lesson6Consts.*;

/*
    1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
        Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
        идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
        необходимо выбросить RuntimeException.

        Написать набор тестов для этого метода (по 3-4 варианта входных данных).
        Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

    2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
        то метод вернет false; Если есть числа кроме 1 и 4 то должен вернуть false. Написать набор тестов для этого
        метода (по 3-4 варианта входных данных).

        [ 1 1 1 4 4 1 4 4 ] -> true
        [ 1 1 1 1 1 1 ] -> false
        [ 4 4 4 4 ] -> false
        [ 1 4 4 1 1 4 3 ] -> false

    3.* Добавить на серверную сторону сетевого чата логирование событий (сервер запущен, произошла ошибка,
        клиент подключился, клиент прислал сообщение/команду).
 */

public class Lesson6
{
    public static void main(String[] args)
    {
        Lesson6 lesson = new Lesson6();

        lesson.getAfterFourArray(lesson.generateArray(10, 9));

        boolean result;
        int count = 0;
        do
        {
            result = lesson.checkArrayForOneAndFour(lesson.generateArray(5, 4));
            count++;
            System.out.println("Run " + count + ": " + result);
        }
        while (!result);
    }

    // Task 1
    public int[] getAfterFourArray(int[] array)
    {
        if (array == null || array.length == 0)
        {
            return null;
        }

        int idxAfterLast4 = -1;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != 4)
            {
                continue;
            }

            idxAfterLast4 = i + 1;
        }

        if (idxAfterLast4 == -1)
        {
            throw new RuntimeException(MESSAGE_NO_4_IN_ARRAY);
        }

        if (idxAfterLast4 == array.length)
        {
            return new int[] {};
        }

        int outputArrayLength = array.length - idxAfterLast4;
        int[] outputArray = new int[outputArrayLength];

        for (int i = idxAfterLast4; i < array.length; i++)
        {
            outputArray[i - idxAfterLast4] = array[i];
        }

        return outputArray;
    }

    // Task 2
    public boolean checkArrayForOneAndFour(int[] array)
    {
        if (array == null || array.length == 0)
        {
            return false;
        }

        boolean has1 = false;
        boolean has4 = false;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] != 1 && array[i] != 4)
            {
                return false;
            }

            has1 |= array[i] == 1;
            has4 |= array[i] == 4;
        }

        return has1 && has4;
    }

    private int[] generateArray(int arrayLength, int randomBound)
    {
        Random random = new Random();
        int[] array = new int[arrayLength];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(randomBound) + 1;
        }

        return array;
    }
}
