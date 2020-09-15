package lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lesson6Test
{
    private Lesson6 lesson = new Lesson6();

    @Test
    public void testAfter4ArrayIsNull()
    {
        Assertions.assertEquals(null, this.lesson.getAfterFourArray(null));
    }

    @Test
    public void testAfter4ArrayIsEmpty()
    {
        Assertions.assertEquals(null, this.lesson.getAfterFourArray(new int[] {}));
    }

    @Test
    public void testAfter4FourAsFirstItem()
    {
        int[] expectedArray = new int[] {2, 3, 1};

        int[] resArray = this.lesson.getAfterFourArray(new int[] {4, 2, 3, 1});

        Assertions.assertEquals(expectedArray.length, resArray.length);
        Assertions.assertArrayEquals(expectedArray, resArray);
    }

    @Test
    public void testAfter4FourAsLastItem()
    {
        Assertions.assertEquals(0, this.lesson.getAfterFourArray(new int[] {1, 2, 3, 4}).length);
    }

    @Test
    public void testAfter4FourIsMoreTimes()
    {
        int[] expectedArray = new int[] {1};

        int[] resArray = this.lesson.getAfterFourArray(new int[] {4, 2, 3, 4, 1});

        Assertions.assertEquals(expectedArray.length, resArray.length);
        Assertions.assertArrayEquals(expectedArray, resArray);
    }

    @Test
    public void testAfter4ArrayHasNo4()
    {
        try
        {
            Assertions.assertEquals(null, this.lesson.getAfterFourArray(new int[] {1,2,3,5}));
        }
        catch (Exception e)
        {
            Assertions.assertEquals(RuntimeException.class.getName(), e.getClass().getName());
            Assertions.assertEquals(Lesson6Consts.MESSAGE_NO_4_IN_ARRAY, e.getMessage());
        }
    }

    @Test
    public void testCheck14ArrayIsNull()
    {
        Assertions.assertEquals(false, this.lesson.checkArrayForOneAndFour(null));
    }

    @Test
    public void testCheck14ArrayIsEmpty()
    {
        Assertions.assertEquals(false, this.lesson.checkArrayForOneAndFour(new int[] {}));
    }

    @Test
    public void testCheck14ContainsNo14()
    {
        Assertions.assertEquals(false, this.lesson.checkArrayForOneAndFour(new int[] {2, 3, 5, 6, 7}));
    }

    @Test
    public void testCheck14ContainsOnly1()
    {
        Assertions.assertEquals(false, this.lesson.checkArrayForOneAndFour(new int[] {1, 1, 1, 1, 1}));
    }

    @Test
    public void testCheck14ContainsOnly4()
    {
        Assertions.assertEquals(false, this.lesson.checkArrayForOneAndFour(new int[] {4, 4, 4, 4, 4}));
    }

    @Test
    public void testCheck14Contains14AndOtherNumbers()
    {
        Assertions.assertEquals(false, this.lesson.checkArrayForOneAndFour(new int[] {1, 3, 5, 4, 7}));
    }

    @Test
    public void testCheck14ContainsOnly14()
    {
        Assertions.assertEquals(true, this.lesson.checkArrayForOneAndFour(new int[] {1, 4, 1, 4, 4}));
    }
}
