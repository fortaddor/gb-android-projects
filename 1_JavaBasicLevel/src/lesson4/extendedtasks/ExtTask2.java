package lesson4.extendedtasks;

/*
* Дополнительные задания:
*
* 2) На КАРТИНКЕ представлена визуализация чисел Фибоначчи.
*   Необходимо подсчитать сумму периметров квадратов для n+1 квадратов
*   Для примера выше n=5,
*   1*4 + 1*4 + 2*4 + 3*4 + 5*4 + 8*4 = 80
*   Для n = 7 -> 216
*
*/

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class ExtTask2
{
    private static final int MIN_SQUARE_NUMBER = 1;
    private static final int SQUARE_SITES = 4;

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        ExtTask2 lesson = new ExtTask2();

        System.out.println("\nTasks to Lesson 41");
        System.out.println("-------------------");
        System.out.println("Task 2. CalculateFibonacciSquares");

        lesson.task2CalculateFibonacciSquares();
    }

    // Task 2
    private void task2CalculateFibonacciSquares()
    {
        int squareNumber;

        do
        {
            System.out.print("Enter the number > 0: ");
            squareNumber = scanner.nextInt();
        }
        while (squareNumber <= MIN_SQUARE_NUMBER - 1);

        this.calculateFibonacciSquaresArea(squareNumber);
    }

    public void calculateFibonacciSquaresArea(int squareNumber)
    {
        System.out.println("\nNumber for Fibonacci calculation: " + squareNumber);

        BigInteger squaresArea = BigInteger.ZERO;
        BigInteger[] array = new BigInteger[squareNumber + 1];

        for (int i = 0; i < array.length; i++)
        {
            if ((i - 2) < 0)
            {
                array[i] = BigInteger.valueOf(1);
                continue;
            }

            array[i] = array[i - 2].add(array[i - 1]);
        }

        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++)
        {
            squaresArea = squaresArea.add(array[i].multiply(BigInteger.valueOf(SQUARE_SITES)));
        }

        System.out.printf("\nThe area of %d Fibonacci squares is %s \n", squareNumber, squaresArea.toString());
    }
}
