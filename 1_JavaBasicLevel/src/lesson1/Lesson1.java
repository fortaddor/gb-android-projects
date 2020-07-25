package lesson1;

public class Lesson1
{
    // Task 1
    public static void main(String[] args)
    {
        Lesson1 process = new Lesson1();

        // Task 2
        process.initVars();
        process.println();

        // Task 3
        process.print("Calculate result", String.valueOf(process.calculate(23, 6, 36, 9)));
        process.println();

        // Task 4
        process.print("Sum of 23 and 6 is between 10 and 20: ", String.valueOf(process.compareNumbers(23, 6)));
        process.println();

        // Task 5
        process.checkIsNumberNegativeOrPositive(-2345);
        process.println();

        // Task 6
        process.print("The number 87878 is negative: " + String.valueOf(process.checkIsNumberNegative(87878)));
        process.println();

        // Task 7
        process.printName("Valerij Krauter");
        process.println();

        // Task 8
        process.checkLeapYear(1997);
        process.println();
    }

    private void initVars()
    {
        byte byteVarMin = -128;
        byte byteVarMax = 127;
        short shortVarMin = -32768;
        short shortVarMax = 32767;
        int intVarMin = -2147483648;
        int intVarMax = 2147483647;
        long longVarMin = Long.MIN_VALUE;
        long longVarMax = Long.MAX_VALUE;
        float floatVarMin = Float.MIN_VALUE;
        float floatVarMax = Float.MAX_VALUE;
        double doubleVarMin = Float.MIN_VALUE;
        double doubleVarMax = Float.MAX_VALUE;
        boolean boolVarFalse = false;
        boolean boolVarTrue = true;
        char charVar = 'A';
        String strVar = "Hello World!";

        this.printMinMax("byte", " .. ", String.valueOf(byteVarMin), String.valueOf(byteVarMax));
        this.printMinMax("short", " .. ",String.valueOf(shortVarMin), String.valueOf(shortVarMax));
        this.printMinMax("int", " .. ", String.valueOf(intVarMin), String.valueOf(intVarMax));
        this.printMinMax("long"," .. ",  String.valueOf(longVarMin), String.valueOf(longVarMax));
        this.printMinMax("float", " .. ", String.valueOf(floatVarMin), String.valueOf(floatVarMax));
        this.printMinMax("double", " .. ", String.valueOf(doubleVarMin), String.valueOf(doubleVarMax));
        this.printMinMax("boolean", "/", String.valueOf(boolVarFalse), String.valueOf(boolVarTrue));
        this.print("char", String.valueOf(charVar));
        this.print("String", strVar);
    }

    private int calculate(int a, int b, int c, int d)
    {
        return  a * (b + (c/d));
    }

    private boolean compareNumbers(int a, int b)
    {
        int sum = a + b;

        return (sum >= 10 && sum <= 20);
    }

    private void checkIsNumberNegativeOrPositive(int number)
    {
        if (number < 0)
        {
            print("The number is negative: " + number);
        }
        else
        {
            print("The number is positive: " + number);
        }
    }

    private boolean checkIsNumberNegative(int number)
    {
        return number < 0;
    }

    private void printName(String name)
    {
        this.print("Hello, " + name + "!");
    }

    private void checkLeapYear(int year)
    {
        if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0))
        {
            print("The year " + year + " is a leap year!");
        }
        else
        {
            print("The year " + year + " isn't a leap year!");
        }
    }

    private void printMinMax(String type, String divider, String minValue, String maxValue)
    {
        System.out.println(type + ": " + minValue + divider + maxValue);
    }

    private void print(String type, String value)
    {
        System.out.println(type + ": " + value);
    }

    private void print(String str)
    {
        System.out.println(str);
    }

    private void println()
    {
        System.out.println();
    }
}