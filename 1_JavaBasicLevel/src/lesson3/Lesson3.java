package lesson3;/*
* Урок 3. Практика
* Делать только одну задачу
* 1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
*    При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
*    После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
* 2. * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
*    "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
*    "pear", "pepper", "pineapple", "pumpkin", "potato"};

*    При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
*    сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер
*    показывает буквы которые стоят на своих местах.
*    apple – загаданное
*    apricot - ответ игрока
*    ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
*    Для сравнения двух слов посимвольно, можно пользоваться:
*    String str = "apple";
*    str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
*    Играем до тех пор, пока игрок не отгадает слово
*    Используем только маленькие буквы
*/

import java.util.Random;
import java.util.Scanner;

public class Lesson3 {

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public static void main(String[] args) {
        Lesson3 lesson = new Lesson3();


        while (true) {
            System.out.println("Do you guess Numbers (1) or Words (2)? (Exit = 0) >>");
            int input = lesson.scanner.nextInt();

            if (input == 1) {
                lesson.task1GuessNumber();
            } else if (input == 2) {
                lesson.task2GuessFruitsAndVegitables();
            }
            else if (input == 0)
            {
                break;
            }
        }
    }

    // Task 1
    private void task1GuessNumber()
    {
        int newGame = 1;

        do {
            System.out.println();
            System.out.println("New Game!");
            System.out.println();

            int madeNumber = this.random.nextInt(10);

            System.out.println("Please guess the made number from 0 till 9!");

            int tryNumber = 0;
            while (tryNumber < 3) {
                System.out.print((tryNumber + 1) + ". Your choice >> ");
                int playerChoice = this.scanner.nextInt();

                if (playerChoice < 0 || playerChoice > 9)
                {
                    System.out.println("You select wrong number. Please select the number from 0 till 9!");
                    continue;
                }

                tryNumber++;

                if (playerChoice == madeNumber)
                {
                    System.out.println("Congratulations! You guess the number!");
                    System.out.println("Made number is: " + madeNumber);
                    break;
                }
                else
                {
                    if (tryNumber == 3)
                        System.out.println("You lost!");
                    else
                        System.out.println("You didn't guess! Please try again.");
                }
            }

            do {
                System.out.println();
                System.out.print("Do you want play new Game? (1 - Yes, 0 - No) >> ");
                newGame = this.scanner.nextInt();
            } while (newGame != 0 && newGame != 1);
        }
        while (newGame == 1);

        System.out.println("Bye, bye!");
    }

    // Task 2
    private void task2GuessFruitsAndVegitables()
    {
        // Array has 25 elements
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};

        System.out.println("Hidden word denotes fruit or vegetable");
        System.out.println();

        int madeIndex = this.random.nextInt(26);
        String madeWord = words[madeIndex];

        String guessWord = "***************";
        while(true) {
            System.out.println("Guess the word: " + guessWord);

            System.out.print("Please input your choice >> ");
            String playerInput = scanner.nextLine();
            System.out.println(playerInput);

            if (madeWord.equals(playerInput))
            {
                break;
            }

            int length = Integer.min(madeWord.length(), playerInput.length());
            for (int i = 0; i < length; i++)
            {
                if (madeWord.charAt(i) == playerInput.charAt(i))
                {
                    if (i == 0)
                    {
                        guessWord = madeWord.charAt(i) + guessWord.substring(i + 1);
                    }
                    else if (i == length - 1)
                    {
                        guessWord = guessWord.substring(0, i) + madeWord.charAt(i) + guessWord.substring(i + 1);
                    }
                    else
                    {
                        guessWord = guessWord.substring(0, i) + madeWord.charAt(i) + guessWord.substring(i + 1);
                    }
                }
            }

            if (madeWord.equals(guessWord.substring(0, madeWord.length())))
            {
                break;
            }
        }

        System.out.println();
        System.out.println("You win! Made word is '" + madeWord + "'");
    }
}
