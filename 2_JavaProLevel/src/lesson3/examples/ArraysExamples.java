package lesson3.examples;

import java.util.Arrays;

public class ArraysExamples
{
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));

        arr = increase(arr, 2);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] increase(int[] arr, int factor)
    {
        int[] newArr = new int[arr.length * factor];

        System.arraycopy(arr, 0, newArr, 0, arr.length);

        return newArr;
    }
}
