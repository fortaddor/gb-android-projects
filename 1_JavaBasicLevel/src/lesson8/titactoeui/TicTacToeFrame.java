package lesson8.titactoeui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static lesson8.titactoeui.Consts.*;

/**
 * Main window of Tic Tac Toe game.
 *
 * <author>Valerij Krauter</author>
 * <date>26.07.2020</date>
 */
public class TicTacToeFrame extends JFrame
{
    private static final int BUTTON_SIZE = 75;

    private TttButton[][] buttons;
    private final int arraySize;

    private Random random = new Random();

    public TicTacToeFrame(int arraySize)
    {
        this.arraySize = arraySize;

        this.init();
    }

    private void init()
    {
        this.buttons = new TttButton[this.getArraySize()][this.getArraySize()];

        this.initUi();
    }

    private void initUi()
    {
        getContentPane().removeAll();

        setTitle("Play 'Tic Tac Toe'");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, this.getFrameWidth(), this.getFrameHeight());

        setLayout(new GridLayout(this.getArraySize(), this.getArraySize()));

        this.initControls();

        setVisible(true);
    }

    private void initControls()
    {
        for (int i = 0; i < this.getArraySize(); i++)
        {
            for (int j = 0; j < this.getArraySize(); j++)
            {
                this.buttons[i][j] = this.createButton();

                add(this.buttons[i][j]);
            }
        }
    }

    private TttButton createButton()
    {
        return new TttButton(String.valueOf(Consts.SYMBOL_EMPTY))
        {
            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        if (isComputerPressed() || isPlayerPressed())
                        {
                            return;
                        }
                        setPlayerPressed(true);
                        setText("");

                        boolean isEnd = playerGameProcess();

                        if (!isEnd)
                        {
                            computerGameProcess();
                        }
                    }
                });
            }
        };
    }

    /**
     * Process the players move.
     *
     * @return TRUE - if the game ends
     */
    private boolean playerGameProcess()
    {
        Boolean isEnd = this.checkEnd(SYMBOL_PLAYER);
        if (isEnd == null)
        {
            this.init();
            return true;
        }
        if (isEnd)
        {
            this.showDialog("You win!!!");
            this.init();

            return true;
        }

        return false;
    }

    private boolean computerGameProcess()
    {
        this.computerMove();

        Boolean isEnd = this.checkEnd(SYMBOL_COMPUTER);
        if (isEnd == null)
        {
            this.init();
            return true;
        }

        if (isEnd)
        {
            this.showDialog("You lose!!!");
            this.init();
            return true;
        }

        return false;
    }

    private void computerMove()
    {
        char[][] map = this.getMap();

        // Move to win or block the human player
        if (this.computerTryToWin(map) || this.blockPlayer(map))
        {
            this.applyMapChanges(map);
            return;
        }

        this.computerRandomMove();
    }

    private boolean computerTryToWin(char[][] map)
    {
        WinChecker checker = new WinChecker(map);
        return checker.checkAndWinOrBlock(SYMBOL_COMPUTER, SYMBOL_EMPTY);
    }

    private boolean blockPlayer(char[][] map)
    {
        WinChecker checker = new WinChecker(map);
        return checker.checkAndWinOrBlock(SYMBOL_PLAYER, SYMBOL_COMPUTER);
    }

    private void computerRandomMove()
    {
        int rowNumber;
        int columnNumber;

        do
        {
            rowNumber = this.random.nextInt(this.getArraySize()) + 1;
            columnNumber = this.random.nextInt(this.getArraySize()) + 1;
        }
        while (!this.isValidInput(rowNumber, columnNumber));

        this.buttons[rowNumber - 1][columnNumber - 1].setText("");
        this.buttons[rowNumber - 1][columnNumber - 1].processComputerMove();
    }

    private boolean isValidInput(int rowNumber, int columnNumber)
    {
        if (!this.checkRange(rowNumber, columnNumber)
                || !this.checkPlayField(this.getMap(), rowNumber, columnNumber))
        {
            return false;
        }

        return true;
    }

    private boolean checkRange(int rowNumber, int columnNumber)
    {
        boolean result = true;

        if (rowNumber < 1 || rowNumber > this.getArraySize())
        {
            result = false;
        }

        if (columnNumber < 1 || columnNumber > this.getArraySize())
        {
            result = false;
        }

        return result;
    }

    private boolean checkPlayField(char[][] map, int rowNumber, int columnNumber)
    {
        return map[rowNumber - 1][columnNumber - 1] == SYMBOL_EMPTY;
    }

    private Boolean checkEnd(char sign)
    {
        char[][] map = this.getMap();

        WinChecker winChecker = new WinChecker(map);
        if (winChecker.check(sign))
        {
            return true;
        }

        if (this.isMapFull(map))
        {
            this.showDialog("Dead heat!");
            return null;
        }

        return false;
    }

    private boolean isMapFull(char[][] map)
    {
        for (char[] mapChars : map)
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

    private void applyMapChanges(char[][] map)
    {
        for (int i = 0; i < this.getArraySize(); i++)
        {
            for (int j = 0; j < this.getArraySize(); j++)
            {
                if (map[i][j] == this.buttons[i][j].getSign())
                {
                    continue;
                }

                this.buttons[i][j].setText("");
                this.buttons[i][j].setSign(map[i][j]);
                this.buttons[i][j].processComputerMove();
            }
        }
    }

    private void showDialog(String message)
    {
        JDialog dialog = new JDialog(this, true)
        {
            {
                setTitle("Information");
                setBounds(300, 300, 300, 80);

                JLabel label = new JLabel(message);
                add(label);
            }
        };

        dialog.setVisible(true);
    }

    public int getArraySize()
    {
        return this.arraySize;
    }

    private int getFrameHeight()
    {
        return this.getArraySize() * BUTTON_SIZE;
    }

    private int getFrameWidth()
    {
        return this.getArraySize() * BUTTON_SIZE;
    }

    private char[][] getMap()
    {
        char[][] map = new char[this.getArraySize()][this.getArraySize()];

        for (int i = 0; i < this.getArraySize(); i++)
        {
            for (int j = 0; j < this.getArraySize(); j++)
            {
                map[i][j] = this.buttons[i][j].getSign();
            }
        }

        return map;
    }
}
