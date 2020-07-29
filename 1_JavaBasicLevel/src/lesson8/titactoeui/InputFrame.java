package lesson8.titactoeui;

import javax.swing.*;
import java.awt.event.*;

/**
 * Input dialog of Tic Tac Toe game.
 *
 * <author>Valerij Krauter</author>
 * <date>26.07.2020</date>
 */
public class InputFrame extends JFrame
{
    JLabel label = new JLabel();

    private int arraySize;

    public InputFrame()
    {
        this.init();
        this.initUi();
    }

    private void init()
    {
        this.setLabel();
    }

    private void initUi()
    {
        setTitle("Play Size input");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(400, 300, 300, 100);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(this.label);
        add(this.createField());

        setVisible(true);
    }

    private void setLabel()
    {
        if (this.label == null)
        {
            return;
        }

        this.label.setText("Please input the number of rows and columns and press enter");
    }

    private JTextField createField()
    {
        return new JTextField()
        {
            {
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        applyInputValue(getText());
                        setText(null);
                    }
                });
            }
        };
    }

    private void applyInputValue(String text)
    {
        this.setArraySize(Integer.valueOf(text));
        this.dispose();
    }

    public int getArraySize()
    {
        return this.arraySize;
    }

    public void setArraySize(int arraySize)
    {
        this.arraySize = arraySize;
    }
}
