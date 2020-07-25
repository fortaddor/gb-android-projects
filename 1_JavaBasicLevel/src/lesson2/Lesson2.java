package lesson2;/*
* Урок 2. Основные конструкции
* 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью
*    цикла и условия заменить 0 на 1, 1 на 0;
* 2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
* 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
* 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов)
*    заполнить его диагональные элементы единицами;
* 5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
* 6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если
*    в массиве есть место, в котором сумма левой и правой части массива равны.
*    Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
*    граница показана символами ||, эти символы в массив не входят.
* 7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или
*    отрицательным), при этом метод должен сместить все элементы массива на n позиций.
*    Для усложнения задачи нельзя пользоваться вспомогательными массивами.
*
*/

import java.util.Arrays;

public class Lesson2
{
    public static void main(String[] args)
    {
        Lesson2 lesson = new Lesson2();

        lesson.task1ReplaceElementsInArray();
        lesson.task2FillEmptyArray();
        lesson.task3IncreaseArrayElementsLessSix();
        lesson.task4FillDiagonalValuesInTwoDimensionalArray();
        lesson.task5FindMinAndMaxValueInArray();
        lesson.task6CheckSumOfLeftAndRightSitesOfArray();
        lesson.task7ShiftArrayElementsToNPositions();
    }

    // Task 1
    private void task1ReplaceElementsInArray()
    {
        System.out.println("Task 1");

        int[] array = new int[] { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0 };

        System.out.println("Original array");
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++)
        {
            int newValue = (array[i] == 1)
                    ? 0
                    : 1;

            array[i] = newValue;
        }

        System.out.println("Array after replacement 1 and 0");
        System.out.println(Arrays.toString(array));
        System.out.println();
    }

    // Task 2
    private void task2FillEmptyArray()
    {
        System.out.println("Task 2");

        int arraySize = 10;
        int[] array = new int[arraySize];

        for (int i = 0; i < arraySize; i++)
        {
            array[i] = i * 3;
        }

        System.out.println("Filled array");
        System.out.println(Arrays.toString(array));
        System.out.println();
    }

    // Task 3
    private void task3IncreaseArrayElementsLessSix()
    {
        System.out.println("Task 3");

        int[] array = new int[] {  1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };

        System.out.println("Original array");
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] < 6)
                array[i] *= 2;
        }

        System.out.println("Elements less 6 increased");
        System.out.println(Arrays.toString(array));
        System.out.println();
    }

    // Task 4
    private void task4FillDiagonalValuesInTwoDimensionalArray()
    {
        System.out.println("Task 4");

        int arraySize = 10;
        int[][] array = new int[10][10];

        System.out.println("Original array");
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }

        for (int i = 0; i < array.length; i++)
        {
            array[i][i] = 1;
            array[array.length - 1 - i][i] = 1;
        }

        System.out.println("Diagonal filled");
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }

        System.out.println();
    }

    // Task 5
    private void task5FindMinAndMaxValueInArray()
    {
        System.out.println("Task 5");

        int[] array = new int[] { 5, 9, 0, 2, 8, 20, 654, 3, 1, 11, 29 };
        int minValue = array[0];
        int maxValue = array[0];

        for (int number : array)
        {
            if (number < minValue)
                minValue = number;

            if (number > maxValue)
                maxValue = number;
        }

        System.out.println("Original array:");
        System.out.println(Arrays.toString(array));
        System.out.println("Min value: " + minValue);
        System.out.println("Max value: " + maxValue);
        System.out.println();
    }

    // Task 6
    private void task6CheckSumOfLeftAndRightSitesOfArray()
    {
        System.out.println("Task 6");

        int[] array = new int[] {  4, 8, 2, 2, 11, 5 };

        System.out.println("Original array");
        System.out.println(Arrays.toString(array));

        boolean checkResult = this.checkSumsLeftAndRight(array);

        if (checkResult)
            System.out.println("There are ONE place in array, that the sums of left and right site are equal.");
        else
            System.out.println("There are NO place in array, that the sums of left and right site are equal.");

        System.out.println();
    }

    private boolean checkSumsLeftAndRight(int[] array)
    {
        boolean leftEqualRight = false;

        for (int i = 0; i < array.length; i++)
        {
            int leftSum = this.sumElements(array, 0, i + 1);
            int rightSum = this.sumElements(array, i + 1, array.length);

            if (leftSum == rightSum)
                leftEqualRight = true;
        }

        return leftEqualRight;
    }

    private int sumElements(int[] array, int startPosition, int endPosition)
    {
        int sum = 0;

        for (int i = startPosition; i < endPosition; i++)
        {
            sum += array[i];
        }

        return sum;
    }

    // Task 7
    private void task7ShiftArrayElementsToNPositions()
    {
        System.out.println("Task 6");

        int[] array = new int[] {  0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

        System.out.println("Original array");
        System.out.println(Arrays.toString(array));

        this.shiftArrayElements(array, 3);
        System.out.println("Array after +3 shift");
        System.out.println(Arrays.toString(array));

        this.shiftArrayElements(array, -2);
        System.out.println("Array after -2 shift");
        System.out.println(Arrays.toString(array));

        System.out.println();
    }

    private void shiftArrayElements(int[] array, int shiftNumber)
    {
        int elementToShift;
        int indexToShift;
        int tempElement = array[0];
        int tempIndex = 0;

        for (int i = 0; i < array.length; i++)
        {
            elementToShift = tempElement;
            indexToShift = tempIndex;

            int shiftPosition = indexToShift + shiftNumber;
            if (shiftNumber < 0) {
                if (shiftPosition < 0)
                    shiftPosition += array.length;
            }
            else {
                if (shiftPosition > array.length - 1)
                    shiftPosition -= array.length;
            }

            tempIndex = shiftPosition;
            tempElement = array[tempIndex];


            array[shiftPosition] = elementToShift;
        }
    }
}
