package lesson7.tests;

import lesson7.annotations.AfterSuite;
import lesson7.annotations.BeforeSuite;
import lesson7.annotations.Test;
import org.junit.jupiter.api.Assertions;

public class ClassWithRightStructureTest
{
    private int valueTest1;
    private long valueTest2;
    private String valueTest3;
    private char valueTest4;
    private boolean valueTest5;
    private char valueTest6;

    @BeforeSuite
    public void setup()
    {
        System.out.println("Run setup of test suite");

        this.valueTest1 = 1;
        this.valueTest2 = 2;
        this.valueTest3 = "Lorem ipsum";
        this.valueTest4 = 'T';
        this.valueTest5 = true;
        this.valueTest6 = 'e';
    }

    @AfterSuite
    public void shutdown()
    {
        System.out.println("\nRun shutdown of test suite\n");
    }

    @Test(priority = 5)
    public void test1CompareInteger()
    {
        Assertions.assertEquals(1, this.valueTest1);

        System.out.print("order! ");
    }

    @Test(priority = 4)
    public void test2CompareLong()
    {
        Assertions.assertEquals(2, this.valueTest2);

        System.out.print("right ");
    }

    @Test(priority = 3)
    public void test3CompareString()
    {
        Assertions.assertFalse("ipsum".equals(this.valueTest3));

        System.out.print("in ");
    }

    @Test(priority = 2)
    public void test4CompareChar()
    {
        Assertions.assertEquals('T', this.valueTest4);

        System.out.print("run/tests ");
    }

    @Test(priority = 1)
    public void test5CompareBoolean()
    {
        Assertions.assertTrue(this.valueTest5);

        System.out.print("All ");
    }

    @Test(priority = 2)
    public void test6CompareChar()
    {
        Assertions.assertEquals('e', this.valueTest6);

        System.out.print("tests/run ");
    }
}
