package lesson4.extendedtasks;/*
* Дополнительные задания:
* 1** ) При помощи метода drawSpiral(int height, int weight) и двумерного массива нарисовать спиральную матрицу:
*
*   01 12 11 10
*   02 13 16 09
*   03 14 15 08
*   04 05 06 07
*
*   Принцип заполнения: вниз -> вправо -> вверх -> влево
*   Учтите, что столбцов и строк может быть минимум 1. А если числа разных разрядов, то еще нужно подумать о
*   форматировании (подсказка: printf() поможет)
*   Размер спрашивается у пользователя, выводить по порядку. Может быть по размеру 7х1, может 2х11
*
*/

import java.util.Scanner;

public class ExtTask1
{
    private Scanner scanner = new Scanner(System.in);

    private int rowNumber;
    private int columnNumber;

    public static void main(String[] args)
    {
        ExtTask1 lesson = new ExtTask1();

        System.out.println("\nTasks to Lesson 41");
        System.out.println("-------------------");
        System.out.println("Task 1. FillSpiralArray");

        lesson.task1FillSpiralArray();
    }

    // Task 1
    private void task1FillSpiralArray()
    {
        this.rowNumber = 0;
        this.columnNumber = 0;

        this.inputArraySize();

        // Define array
        int[][] array = new int[this.rowNumber][this.columnNumber];

        this.fillSpiralArray(array);
        this.printArray(array);
    }

    private void fillSpiralArray(int[][] array)
    {
        int totalNumbers = this.rowNumber * this.columnNumber;
        int number = 0;

        // 1-down, 2-right, 3-up, 4-left
        int direction = 0;

        int startIdx = 0;
        int maxValue = 0;
        int fixIdx = 0;

        int runCounter = 0;
        while (number != totalNumbers)
        {
            boolean fixX = false;
            boolean isReverse = false;

            direction++;

            if (direction == 1)
            {
                startIdx = runCounter;
                maxValue = this.rowNumber - runCounter;
                fixIdx = runCounter;
                fixX = false;
                isReverse = false;
            }
            else if (direction == 2)
            {
                startIdx = runCounter + 1;
                maxValue = this.columnNumber - runCounter;
                fixIdx = this.rowNumber - 1 - runCounter;
                fixX = true;
                isReverse = false;
            }
            else if (direction == 3)
            {
                startIdx = this.rowNumber - 2 - runCounter;
                maxValue = runCounter;
                fixIdx = this.columnNumber - 1 - runCounter;
                fixX = false;
                isReverse = true;
            }
           else if (direction == 4)
            {
                startIdx = this.columnNumber - 2 - runCounter;
                maxValue = runCounter + 1;
                fixIdx = runCounter;
                fixX = true;
                isReverse = true;
            }

            number = this.fillArray(array, startIdx, maxValue, fixIdx, fixX, isReverse, number);

            if (direction == 4)
            {
                direction = 0;
                runCounter++;
            }
        }
    }

    private void inputArraySize()
    {
        while (this.rowNumber < 1 || this.columnNumber < 1)
        {
            System.out.print("Rows number: ");
            this.rowNumber = scanner.nextInt();
            System.out.print("Columns number: ");
            this.columnNumber = scanner.nextInt();

            this.checkSizeInput();
        }
    }

    private void checkSizeInput()
    {
        if (this.rowNumber < 1)
        {
            System.out.println("Minimum possible rows number is 1! \nPlease repeat input.\n");
        }
        if (this.columnNumber < 1)
        {
            System.out.println("Minimum possible columns number is 1! \nPlease repeat input.\n");
        }
    }

    private int fillArray(int[][] array, int startIdx, int maxValue, int fixIdx, boolean fixX, boolean isReverse, int number)
    {
        if (isReverse)
        {
            for (int i = startIdx; i >= maxValue; i--)
            {
                number = this.fillArray(array, i, fixIdx, fixX, number);
            }
        }
        else
        {
            for (int i = startIdx; i < maxValue; i++)
            {
                number = this.fillArray(array, i, fixIdx, fixX, number);
            }
        }

        return number;
    }

    private int fillArray(int[][] array, int idx, int fixIdx, boolean fixX, int number)
    {
        number++;

        if (fixX)
        {
            array[fixIdx][idx] = number;
        }
        else
        {
            array[idx][fixIdx] = number;
        }

        return number;
    }

    private void printArray(int[][] array)
    {
        System.out.println("\nSpiral Array");

        String format = this.getPrintFormat();

        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                System.out.printf(format, array[i][j]);
            }

            System.out.println();
        }
    }

    private String getPrintFormat()
    {
        return "%0" + String.valueOf(this.rowNumber * this.columnNumber).length() + "d ";
    }
}
