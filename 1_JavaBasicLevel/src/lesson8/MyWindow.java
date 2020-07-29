package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyWindow extends JFrame
{
    public MyWindow()
    {
        this.init();
    }

    private void init()
    {
        setTitle("Test Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 300, 300, 300);

        JTextField field = new JTextField();
        add(field);
        field.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Your message: " + field.getText());
//                field.setText(null);
            }
        });

        field.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                if (e.isShiftDown())
                {
                    System.out.println("SHIFT");
                }
                System.out.println(e.getKeyChar() + " down " + e.getKeyLocation());
            }
        });

        //this.initControls();

        setVisible(true);
    }

    private void initControls()
    {
        JButton[] buttons = new JButton[9];
        setLayout(new GridLayout(3,3));

        for (int i = 0; i < buttons.length; i++)
        {
            JButton button = new JButton("#" + i);
            button.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Action!");
                }
            });

            buttons[i] = button;
            add(button);
        }
//        add(buttons[0], BorderLayout.EAST);
//        add(buttons[1], BorderLayout.WEST);
//        add(buttons[2], BorderLayout.SOUTH);
//        add(buttons[3], BorderLayout.SOUTH);
//        add(buttons[4], BorderLayout.SOUTH);
    }
}


/*
* Layouts
* BoxLayout
* BorderLayout
* GridLayout
* FlowLayoiut
* */