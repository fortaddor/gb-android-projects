package lesson4.titactoe;

import java.util.Random;
import java.util.Scanner;
import static lesson4.titactoe.Consts.*;

/**
 * Tic Tac Toe game.
 *
 * <author>Valerij Krauter</author>
 * <date>05.07.2020</date>
 */
public class TicTacToe
{
    private char[][] map = new char[SIZE][SIZE];
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public static void main(String[] args)
    {
        TicTacToe ttt = new TicTacToe();

        ttt.startGame();
    }

    private void startGame()
    {
        this.initMap();
        this.printMap();

        this.playGame();
    }

    private void initMap()
    {
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                this.setSymbolToMapField(i, j, SYMBOL_EMPTY);
            }
        }
    }

    private void setSymbolToMapField(int rowNumber, int columnNumber, char symbol)
    {
        this.map[rowNumber][columnNumber] = symbol;
    }

    protected void printMap()
    {
        printMapHeader();
        printMapBody();

        System.out.println();
    }

    private void printMapHeader()
    {
        printSign(BLANK);

        for (int i = 0; i < SIZE; i++)
        {
            printSign(i + 1);
        }

        System.out.println();
    }

    private void printMapBody()
    {
        for (int i = 0; i < SIZE; i++)
        {
            printSign(i + 1);

            for (int j = 0; j < SIZE; j++)
            {
                printSign(map[i][j]);
            }

            System.out.println();
        }
    }

    private void printSign(Object sign)
    {
        System.out.print(sign.toString() + BLANK);
    }

    private void playGame()
    {
        while (true)
        {
            this.playerGameProcess();

            this.computerGameProcess();
        }
    }

    private void playerGameProcess()
    {
        this.playerMove();
        this.printMap();

        if (this.checkEnd(SYMBOL_PLAYER))
        {
            System.out.println("You win!!!");
            System.exit(0);
        }
    }

    private void computerGameProcess()
    {
        this.computerMove();
        this.printMap();

        if (this.checkEnd(SYMBOL_COMPUTER))
        {
            System.out.println("You lose!!!");
            System.exit(0);
        }
    }

    private void playerMove()
    {
        int rowNumber;
        int columnNumber;

        System.out.println("Player's move... ");

        do {
            System.out.print("Enter row: ");
            rowNumber = this.scanner.nextInt();
            System.out.print("Enter column: ");
            columnNumber = this.scanner.nextInt();
        }
        while (!this.isValidInput(rowNumber, columnNumber, true));

        this.setSymbolToMapField(rowNumber - 1, columnNumber - 1, SYMBOL_PLAYER);
    }

    private void computerMove()
    {
        System.out.println("Computer's move... ");

        // Move to win or block the human player
        if (this.computerTryToWin() || this.blockPlayer())
        {
            return;
        }

        this.computerRandomMove();
    }

    private boolean computerTryToWin()
    {
        WinChecker checker = new WinChecker(this.map);
        return checker.checkAndWinOrBlock(SYMBOL_COMPUTER, SYMBOL_EMPTY);
    }

    private boolean blockPlayer()
    {
        WinChecker checker = new WinChecker(this.map);
        return checker.checkAndWinOrBlock(SYMBOL_PLAYER, SYMBOL_COMPUTER);
    }

    private void computerRandomMove()
    {
        int rowNumber;
        int columnNumber;

        do
        {
            rowNumber = this.random.nextInt(SIZE) + 1;
            columnNumber = this.random.nextInt(SIZE) + 1;
        }
        while (!this.isValidInput(rowNumber, columnNumber, false));

        this.setSymbolToMapField(rowNumber - 1, columnNumber - 1, SYMBOL_COMPUTER);
    }

    private boolean isValidInput(int rowNumber, int columnNumber, boolean showHints)
    {
        if (!this.checkRange(rowNumber, columnNumber, showHints)
                || !this.checkPlayField(rowNumber, columnNumber, showHints))
        {
            return false;
        }

        return true;
    }

    private boolean checkRange(int rowNumber, int columnNumber, boolean showHints)
    {
        boolean result = true;

        if (rowNumber < 1 || rowNumber > SIZE)
        {
            if (showHints)
            {
                System.out.println("\nYou input wrong row!");
            }
            result = false;
        }

        if (columnNumber < 1 || columnNumber > SIZE)
        {
            if (showHints)
            {
                System.out.println("You input wrong column!");
            }
            result = false;
        }

        return result;
    }

    private boolean checkPlayField(int rowNumber, int columnNumber, boolean showHints)
    {
        if (this.map[rowNumber - 1][columnNumber - 1] != SYMBOL_EMPTY)
        {
            if (showHints)
            {
                System.out.println("\nSelected field is not empty!\n");
            }
            return false;
        }

        return true;
    }

    private boolean checkEnd(char sign)
    {
        WinChecker winChecker = new WinChecker(this.map);
        if (winChecker.check(sign))
        {
            return true;
        }

        if (this.isMapFull())
        {
            System.out.println("Dead heat!");
            return true;
        }

        return false;
    }

    private boolean isMapFull()
    {
        for (char[] mapChars : this.map)
        {
            for (char mapChar : mapChars)
            {
                if (mapChar == SYMBOL_EMPTY)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
