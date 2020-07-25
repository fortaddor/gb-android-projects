package lesson2.exception;

/**
 * Exception for wrong array size.
 *
 * Author: Valerij Krauter
 * Date: 25.07.2020
 */
public class MyArraySizeException extends Exception
{
    private final int expectedSize;
    private final int wrongSizeRow;
    private final int wrongSizeColumn;

    public MyArraySizeException(int expectedSize, int wrongSizeRow, int wrongSizeColumn)
    {
        this.expectedSize = expectedSize;
        this.wrongSizeRow = wrongSizeRow;
        this.wrongSizeColumn = wrongSizeColumn;
    }

    public String getErrorMessage()
    {
        return "The expected array size is " + this.getExpectedSize() + "x" + this.getExpectedSize() +
                ". But array has size " + this.getWrongSizeRow() + "x" + this.getWrongSizeColumn();
    }

    public int getExpectedSize()
    {
        return this.expectedSize;
    }

    public int getWrongSizeRow()
    {
        return this.wrongSizeRow;
    }

    public int getWrongSizeColumn()
    {
        return this.wrongSizeColumn;
    }
}
