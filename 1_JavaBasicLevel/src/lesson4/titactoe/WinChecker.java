package lesson4.titactoe;

import static lesson4.titactoe.Consts.*;
import static lesson4.titactoe.MapDirection.*;

/**
 * Checker class for the win chance in Tic Tac Toe game.
 *
 * <author>Valerij Krauter</author>
 * <date>07.07.2020</date>
 */
public class WinChecker
{
    public static final int IDX_INVALID = -1;

    private final char[][] map;

    public WinChecker(char[][] map)
    {
        this.map = map;
    }

    public boolean check(char symbol)
    {
        int winCountDiagonal = 0;
        int winCountBackDiagonal = 0;

        for (int i = 0; i < this.map.length; i++)
        {
            int winCountRow = 0;
            int winCountColumn = 0;

            for (int j = 0; j < this.map[i].length; j++)
            {
                winCountRow = this.checkMapSymbolForWinCount(this.map[i][j], symbol, winCountRow);
                if (winCountRow == SCORE_TO_WIN)
                {
                    return true;
                }

                winCountColumn = this.checkMapSymbolForWinCount(this.map[j][i], symbol, winCountColumn);
                if (winCountColumn == SCORE_TO_WIN)
                {
                    return true;
                }
            }

            winCountDiagonal = this.checkMapSymbolForWinCount(this.map[i][i], symbol, winCountDiagonal);
            if (winCountDiagonal == SCORE_TO_WIN)
            {
                return true;
            }

            winCountBackDiagonal = this.checkMapSymbolForWinCount(this.map[i][SIZE - 1 - i], symbol, winCountBackDiagonal);
            if (winCountBackDiagonal == SCORE_TO_WIN)
            {
                return true;
            }
        }

        return false;
    }

    public boolean checkAndWinOrBlock(char symbolToBlock, char blockSymbol)
    {
        int score = SCORE_TO_WIN - 1;

        int winCountDiagonal = 0;
        int winCountBackDiagonal = 0;

        for (int i = 0; i < this.map.length; i++)
        {
            int winCountRow = 0;
            int winCountColumn = 0;

            for (int j = 0; j < this.map[i].length; j++)
            {
                winCountRow += this.checkMapSymbolForTryWinCount(this.map[i][j], symbolToBlock);
                if (winCountRow == score)
                {
                    if (this.hasEmpty(ROWS, i))
                    {
                        if (this.checkAndFill(ROWS, symbolToBlock, blockSymbol, i))
                        {
                            return true;
                        }
                    }
                }

                winCountColumn += this.checkMapSymbolForTryWinCount(this.map[j][i], symbolToBlock);
                if (winCountColumn == score)
                {
                    if (this.hasEmpty(COLUMNS, i))
                    {
                        if (this.checkAndFill(COLUMNS, symbolToBlock, blockSymbol, i))
                        {
                            return true;
                        }
                    }
                }
            }

            winCountDiagonal += this.checkMapSymbolForTryWinCount(this.map[i][i], symbolToBlock);
            if (winCountDiagonal == score)
            {
                if (this.hasEmpty(DIAGONAL, IDX_INVALID))
                {
                    if (this.checkAndFill(DIAGONAL, symbolToBlock, blockSymbol, IDX_INVALID))
                    {
                        return true;
                    }
                }
            }

            winCountBackDiagonal += this.checkMapSymbolForTryWinCount(this.map[i][SIZE - 1 - i], symbolToBlock);
            if (winCountBackDiagonal == score)
            {
                if (this.hasEmpty(BACK_DIAGONAL, IDX_INVALID))
                {
                    if (this.checkAndFill(BACK_DIAGONAL, symbolToBlock, blockSymbol, IDX_INVALID))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkAndFill(MapDirection direction, char winSymbol, char blockSymbol, int fixIdx)
    {
        for (int k = 0; k < this.map.length; k++)
        {
            int i;
            int j;

            switch (direction)
            {
                case ROWS:
                    i = fixIdx;
                    j = k;
                    break;

                case COLUMNS:
                    i = k;
                    j = fixIdx;
                    break;

                case DIAGONAL:
                    i = k;
                    j = k;
                    break;

                default:
                    i = k;
                    j = SIZE - 1 - k;
            }

            if (this.tryToWinOrBlock(winSymbol, blockSymbol, i, j))
            {
                return true;
            }
        }

        return false;
    }

    private boolean tryToWinOrBlock(char winSymbol, char blockSymbol, int i, int j)
    {
        // If the field is SYMBOL_EMPTY, try to set it
        if (this.map[i][j] == SYMBOL_EMPTY)
        {
            // Set win symbol
            this.map[i][j] = winSymbol;

            // If the winning is possible
            if (this.check(winSymbol))
            {
                // .. and it could be blocked
                if (this.canBlock(blockSymbol))
                {
                    // .. than block it
                    this.map[i][j] = blockSymbol;
                }

                return true;
            }

            // If the winning is not possible, set it back to EMPTY
            this.map[i][j] = SYMBOL_EMPTY;
        }

        return false;
    }

    private boolean canBlock(char resetSymbol)
    {
        return resetSymbol != SYMBOL_EMPTY;
    }

    private int checkMapSymbolForWinCount(char mapChar, char symbol, int count)
    {
        if (mapChar == symbol)
        {
            return count + 1;
        }

        return 0;
    }

    private int checkMapSymbolForTryWinCount(char mapChar, char symbol)
    {
        return (mapChar == symbol) ? 1 : 0;
    }

    private boolean hasEmpty(MapDirection direction, int fixIdx)
    {
        for (int i = 0; i < this.map.length; i++)
        {
            char checkChar = SYMBOL_EMPTY;

            switch (direction)
            {
                case ROWS:
                    checkChar = this.map[fixIdx][i];
                    break;

                case COLUMNS:
                    checkChar = this.map[i][fixIdx];
                    break;

                case DIAGONAL:
                    checkChar = this.map[i][i];
                    break;

                case BACK_DIAGONAL:
                    checkChar = this.map[i][SIZE - 1 - i];
                    break;
            }

            if (checkChar == SYMBOL_EMPTY)
            {
                return true;
            }
        }

        return false;
    }
}
