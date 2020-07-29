package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class DrawingExample extends JFrame
{
    public DrawingExample()
    {
        super("Drawing Demo");

        setSize(680,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        simpleDrawLines(g);
    }

    private void simpleDrawLines(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(120,50,360,50);

        g2d.draw(new Line2D.Double( 59.2d, 99.8d, 419.1d, 99.8d));
        g2d.draw(new Line2D.Float( 59.2f, 109.8f, 419.1f, 109.8f));

        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(150,150,150,150);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(151,151,149,75);

        g2d.drawOval(350,150,150,150);
        g2d.setColor(Color.RED);
        g2d.fillOval(351,151,149,149);
    }
}
