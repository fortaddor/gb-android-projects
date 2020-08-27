package lesson1;

import lesson1.fruitbox.Apple;
import lesson1.fruitbox.Box;
import lesson1.fruitbox.Fruit;
import lesson1.fruitbox.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);

    2. Написать метод, который преобразует массив в ArrayList;

    3. Большая задача:

    Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
    Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку
    нельзя сложить и яблоки, и апельсины;
    Для хранения фруктов внутри коробки можно использовать ArrayList;
    Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
    (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
    Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут
    в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы
    можем сравнивать с коробками с апельсинами);
    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов:
    нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую
    перекидываются объекты, которые были в этой коробке;
    Не забываем про метод добавления фрукта в коробку.

 */
public class Lesson1
{
    public static void main(String[] args)
    {
        Lesson1 lesson = new Lesson1();

        Integer[] array = new Integer[] {1, 3, 5, 7, 11, 13, 17, 19};
        lesson.task1ReplaceTwoArrayElements(array, 5, 17);
        lesson.task2ArrayToArrayLists(array);
        lesson.task3FruitBoxOperations();
    }

    private <T> void task1ReplaceTwoArrayElements(T[] array, T value1, T value2)
    {
        System.out.println("\nTask 1");

        System.out.println("Array BEFORE replacement");
        System.out.println(Arrays.toString(array));

        int idx1 = -1;
        int idx2 = -1;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == value1 && idx1 < 0)
            {
                idx1 = i;
            }

            if (array[i] == value2 && idx2 < 0)
            {
                idx2 = i;
            }

            if (idx1 >= 0 && idx2 >= 0)
            {
                break;
            }
        }

        T temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;

        System.out.println("Array AFTER replacement");
        System.out.println(Arrays.toString(array));
    }

    private <T> void task2ArrayToArrayLists(T[] array)
    {
        System.out.println("\nTask 2");

        System.out.println("Array length: " + array.length);
        System.out.println(Arrays.toString(array));

        ArrayList<T> arrayList = new ArrayList<>(Arrays.asList(array));

        System.out.println("ArrayList size: " + arrayList.size());
        for (T item : arrayList)
        {
            System.out.print(item + " ");
        }

    }

    private void task3FruitBoxOperations()
    {
        System.out.println("\nTask 3");

        Box<Orange> orangeBox1 = new Box("Orange box 1");
        orangeBox1.add(new Orange());

        Box<Apple> appleBox1 = new Box("Apple box 1", new Apple[] {new Apple()});
        Box<Apple> appleBox2 = new Box("Apple box 2", new Apple[] {new Apple(), new Apple()});
        Box<Apple> bigBox = new Box<Apple>("Big Apple box", appleBox2);

        this.printBoxInfo(appleBox1);
        this.printBoxInfo(appleBox2);
        this.printBoxInfo(bigBox);

        System.out.println("\nAdd one apple to big box");
        bigBox.add(new Apple());

        this.printBoxInfo(appleBox1);
        this.printBoxInfo(appleBox2);
        this.printBoxInfo(bigBox);

        System.out.println("\nAdd one apple box 2");
        appleBox2.add(new Apple());

        this.printBoxInfo(appleBox1);
        this.printBoxInfo(appleBox2);
        this.printBoxInfo(bigBox);

        System.out.println("Compare box 1 and 2");
        boolean compareResult = appleBox1.compare(appleBox2);
        System.out.println("Boxes are " + (compareResult ? "" : "not ") + "equal");

        System.out.println("Compare box 1 and big box");
        compareResult = appleBox1.compare(bigBox);
        System.out.println("Boxes are " + (compareResult ? "" : "not ") + "equal");

        System.out.println("\nEmpty box 1");
        appleBox1.empty(bigBox);

        this.printBoxInfo(appleBox1);
        this.printBoxInfo(appleBox2);
        this.printBoxInfo(bigBox);
    }

    private void printBoxInfo(Box box)
    {
        System.out.println(box.getName() + " weight: " + box.getWeight() + "; (" + box.size() + " pieces)");
    }
}
