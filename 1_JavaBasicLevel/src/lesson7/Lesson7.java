package lesson7;

/*
    Урок 7. Практика ООП и работа со строками
    1. Расширить задачу про котов и тарелки с едой
    2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды,
        а кот пытается покушать 15-20)
    3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило
        еды), сытость = true
    4. Считаем, что если коту мало еды в тарелке, то он ее просто не трогает, то есть не может быть наполовину сыт (это
        сделано для упрощения логики программы)
    5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию
        о сытости котов в консоль
    6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку
 */

public class Lesson7
{
    public static void main(String[] args)
    {
        Lesson7 lesson = new Lesson7();

        lesson.task1CatWithNormalAppetiteCanEat();
        lesson.task2NoNegativeFoodOnPlate();
        lesson.task3CatIsWelFedAfterEating();
        lesson.task4CatIsntWelFedAfterEating();
        lesson.task5CatsEatFromPlateFoodNotEnough();
        lesson.task6CatsEatFromPlateAddFoodIfNotEnough();
    }

    // Task 1
    private void task1CatWithNormalAppetiteCanEat()
    {
        System.out.println("\nTask 1");
        System.out.println("The cat has normal appetite. On the plate is enough food.");

        Cat cat = new Cat("Koshara", "White", 4, 10);
        Plate plate = new Plate(15);

        // Print initial food amount
        plate.printInfo();
        // Appetite is less as food amount on the plate
        plate.reduce(cat.getAppetite());
        // Food changed
        plate.printInfo();
    }

    // Task 2
    private void task2NoNegativeFoodOnPlate()
    {
        System.out.println("\nTask 2");
        System.out.println("The cat has big appetite. On the plate is less food as cat's appetite.");

        Cat cat = new Cat("Koshan", "Black", 3, 20);
        Plate plate = new Plate(10);

        // Print initial food amount
        plate.printInfo();
        // Appetite is bigger as food amount on the plate
        plate.reduce(cat.getAppetite());
        // No food changes
        plate.printInfo();
    }

    // Task 3
    private void task3CatIsWelFedAfterEating()
    {
        System.out.println("\nTask 3");
        System.out.println("The cat eat from plate. The are enough food.");

        Cat cat = new Cat("Koshan", "Black", 3, 10);
        Plate plate = new Plate(20);


        plate.printInfo();

        cat.eat(plate);
        cat.printWelfed();

        plate.printInfo();
    }

    // Task 4
    private void task4CatIsntWelFedAfterEating()
    {
        System.out.println("\nTask 4");
        System.out.println("The cat eat from plate. The are not enough food.");

        Cat cat = new Cat("Koshan", "Black", 3, 20);
        Plate plate = new Plate(10);


        plate.printInfo();

        cat.eat(plate);
        cat.printWelfed();

        plate.printInfo();
    }

    // Task 5
    private void task5CatsEatFromPlateFoodNotEnough()
    {
        System.out.println("\nTask 5");
        System.out.println("The cats eat from plate. The food is not for all cats enough.");

        Cat[] cats = new AnimalMock().createCats(5);
        Plate plate = new Plate(50);


        plate.printInfo();

        for (Cat cat : cats)
        {
            cat.eat(plate);

            cat.printInfo();
            cat.printAppetite();
            cat.printWelfed();

            plate.printInfo();
        }
    }

    // Task 6
    private void task6CatsEatFromPlateAddFoodIfNotEnough()
    {
        System.out.println("\nTask 6");
        System.out.println("The cats eat from plate. The food is not for all cats enough. Add food to the plate.");

        Cat[] cats = new AnimalMock().createCats(10);
        Plate plate = new Plate(50);


        plate.printInfo();

        for (Cat cat : cats)
        {
            cat.printInfo();
            cat.printAppetite();

            cat.eat(plate);
            cat.printWelfed();

            while (!cat.isWellfed())
            {
                System.out.println("Add some food to plate...");

                plate.add(10);

                cat.eat(plate);
                cat.printWelfed();
            }

            plate.printInfo();
        }
    }
}
