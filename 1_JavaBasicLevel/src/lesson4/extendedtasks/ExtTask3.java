package lesson4.extendedtasks;

/*
* Дополнительные задания:
*
* 3 ****) Квадратов может быть вплоть до 10000. Понадобится import java.math.BigInteger;
*
*/

import java.util.Scanner;

public class ExtTask3
{
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        ExtTask2 lesson = new ExtTask2();

        System.out.println("\nTasks to Lesson 41");
        System.out.println("-------------------");
        System.out.println("Task 3. Calculate10000FibonacciSquares");

        lesson.calculateFibonacciSquaresArea(10000);
    }
}
