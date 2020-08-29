package lesson8.titactoeui;

import javax.swing.*;
import java.awt.*;

/**
 * Extended button for Tic Tac Toe game.
 *
 * <author>Valerij Krauter</author>
 * <date>26.07.2020</date>
 */
public class TttButton extends JButton
{
    private boolean playerPressed = false;
    private boolean computerPressed = false;

    private char sign;

    public TttButton(String text)
    {
        super(text);

        this.setSign(Consts.SYMBOL_EMPTY);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));

        if (this.isPlayerPressed())
        {
            g2d.setColor(Color.BLUE);
            g2d.drawLine(10, 10, this.getWidth() - 10, this.getHeight() - 10);
            g2d.drawLine(this.getWidth() - 10, 10, 10, this.getHeight() - 10);
        }
        else if (computerPressed)
        {
            g2d.setColor(Color.RED);
            g2d.drawOval(10, 10, this.getWidth() - 20, this.getWidth() - 20);
        }
    }

    public void processComputerMove()
    {
        this.setComputerPressed(true);
        this.paint(this.getGraphics());
    }

    public boolean isPlayerPressed()
    {
        return playerPressed;
    }

    public void setPlayerPressed(boolean playerPressed)
    {
        this.playerPressed = playerPressed;
        this.sign = Consts.SYMBOL_PLAYER;
    }

    public boolean isComputerPressed()
    {
        return computerPressed;
    }

    public void setComputerPressed(boolean computerPressed)
    {
        this.computerPressed = computerPressed;
        this.sign = Consts.SYMBOL_COMPUTER;
    }

    public char getSign()
    {
        return sign;
    }

    public void setSign(char sign)
    {
        this.sign = sign;
    }
}
