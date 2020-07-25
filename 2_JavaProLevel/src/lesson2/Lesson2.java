package lesson2;

import lesson2.exception.MyArrayDataException;
import lesson2.exception.MyArraySizeException;

import java.util.Random;

/*
    1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4, при подаче массива другого
        размера необходимо бросить исключение MyArraySizeException.
    2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. Если в каком-то
        элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть
        брошено исключение MyArrayDataException – с детализацией, в какой именно ячейке лежат неверные данные.
    3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
        MyArrayDataException и вывести результат расчета.
* */
public class Lesson2
{
    private static final int SIZE = 4;
    private static final String[] ABC =
            {
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z"
            };

    private Random random = new Random();

    public static void main(String[] args)
    {
        Lesson2 lesson = new Lesson2();

        lesson.testCorrectArraySize();
        lesson.getSumOfAllArrayValues();
    }

    private void getSumOfAllArrayValues()
    {
        System.out.println("\ngetSumOfAllArrayValues() \n-----------------------------");

        String [][] array = this.createTwoDimensionalArray(this.random.nextInt(SIZE) + 1,
                this.random.nextInt(SIZE) + 1, this.random.nextBoolean());

        try
        {
            int sum = this.calculateSumOfAllArrayValues(array);
        }
        catch(MyArraySizeException ex)
        {
            System.out.println(ex.getErrorMessage());
            System.out.println("Please tray again!");
        }
        catch(MyArrayDataException ex)
        {
            System.out.println(ex.getErrorMessage());
            System.out.println("Please tray again!");
        }
    }

    private int calculateSumOfAllArrayValues(String[][] array) throws MyArraySizeException, MyArrayDataException
    {
        int sizeRow = array.length;
        int sizeColumn = array[0].length;

        if (sizeRow != SIZE && sizeColumn != SIZE)
        {
            throw new MyArraySizeException(SIZE, sizeRow, sizeColumn);
        }

        System.out.println("The array has correct size! \nThe size is " + SIZE + "x" + SIZE);

        int sum = 0;

        for (int i = 0; i < sizeRow; i++)
        {
            for (int j = 0; j < sizeColumn; j++)
            {
                try
                {
                    sum += Integer.valueOf(array[i][j]);
                }
                catch (NumberFormatException ex)
                {
                    throw new MyArrayDataException(ex, array[i][j], i, j);
                }
            }
        }

        return sum;
    }

    private void testCorrectArraySize()
    {
        System.out.println("\ntestCorrectArraySize() \n-----------------------------");

        String [][] array = this.createTwoDimensionalArray(SIZE, SIZE, false);

        if (array.length == SIZE && array[0].length == SIZE)
        {
            System.out.println("The array has correct size! \nThe size is " + SIZE + "x" + SIZE);
        }
    }

    private String[][] createTwoDimensionalArray(int sizeRow, int sizeColumn, boolean manipulate)
    {
        String[][] array = new String[sizeRow][sizeColumn];

        for (int i = 0; i < sizeRow; i++)
        {
            for (int j = 0; j < sizeColumn; j++)
            {
                array[i][j] = this.random.nextInt(100) + "";
            }
        }

        if (manipulate)
        {
            this.manipulateArray(array);
        }

        return array;
    }

    /**
     * One element of the array must be manipulated.
     *
     * @param array
     */
    private void manipulateArray(String[][] array)
    {
        int sizeRow = array.length;
        int sizeColumn = array[0].length;

        // Define the index of manipulating array element
        int i = this.random.nextInt(sizeRow);
        int j = this.random.nextInt(sizeColumn);

        // Find index for one letter in ABC-Array.
        array[i][j] = ABC[this.random.nextInt(ABC.length)];
    }
}
