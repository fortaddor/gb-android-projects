package lesson2.exception;

/**
 * Exception for wrong array size.
 *
 * Author: Valerij Krauter
 * Date: 25.07.2020
 */
public class MyArrayDataException extends Exception
{
    private String value;
    private int iIdx;
    private int jIdx;

    public MyArrayDataException(Exception innerEx, String value, int iIdx, int jIdx)
    {
        super(innerEx);

        this.value = value;
        this.iIdx = iIdx;
        this.jIdx = jIdx;
    }

    public String getErrorMessage()
    {
        return String.format("The array element [%d][%d] is not numeric. The value is '%s'.",
                    this.getiIdx(), this.getjIdx(), this.getValue());
    }

    public String getValue()
    {
        return value;
    }

    public int getiIdx()
    {
        return iIdx;
    }

    public int getjIdx()
    {
        return jIdx;
    }
}
