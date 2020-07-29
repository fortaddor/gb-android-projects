package lesson8.titactoeui;

/*
    Добавить графический интерфейс к игре TicTacToe с 4 занятия.
 */
public class StartGame
{
    public static void main(String[] args) throws InterruptedException {
        InputFrame inputFrame = new InputFrame();

        while (inputFrame.isVisible())
        {
            Thread.sleep(500);
        }

        System.out.println("Rows: " + inputFrame.getArraySize());
        System.out.println("Columns: " + inputFrame.getArraySize());

        if (inputFrame.getArraySize() > 0)
        {
            new TicTacToeFrame(inputFrame.getArraySize());
        }
        else
        {
            System.out.println("You haven't set the size of the playing field!");
        }
    }
}
