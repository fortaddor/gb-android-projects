package lesson4.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Chat main window.
 *
 * <author>Valerij Krauter</author>
 * <created>30.07.2020</created>
 */
public class ChatMainForm extends JFrame
{
    private JTextArea corrTextArea = new JTextArea();
    private JTextField inputField = new JTextField();

    public ChatMainForm()
    {
        this.init();
    }

    private void init()
    {
        this.initUi();
    }

    private void initUi()
    {
        setTitle("Chat");
        setBounds(300,100, 500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(300, 400));
        setLayout(new BorderLayout());

        add(this.createChatPanel(), BorderLayout.CENTER);
        add(this.createInputArea(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createChatPanel()
    {
        JPanel chatArea = new JPanel();
        chatArea.setLayout(new BorderLayout());
        chatArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel corrLabel = new JLabel("Conversation");
        corrLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        chatArea.add(corrLabel, BorderLayout.NORTH);

        this.corrTextArea.setEditable(false);
        this.corrTextArea.setDisabledTextColor(Color.BLACK);
        this.corrTextArea.setBackground(new Color(247,247,247));
        this.corrTextArea.setForeground(Color.BLACK);
        Font font = this.inputField.getFont().deriveFont(Font.PLAIN, 13f);
        this.corrTextArea.setFont(font);

        JScrollPane sp = new JScrollPane(this.corrTextArea);

        chatArea.add(sp, BorderLayout.CENTER);

        return chatArea;
    }

    private JPanel createInputArea()
    {
        JPanel inputArea = new JPanel();
        inputArea.setLayout(new GridBagLayout());
        inputArea.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.9;
        gbc.gridx = 0;
        gbc.gridy = 0;

        Font font = this.inputField.getFont().deriveFont(Font.PLAIN, 17f);
        this.inputField.setFont(font);
        this.inputField.addActionListener(this::listenerToAction);
        inputArea.add(this.inputField, gbc);

        gbc.weightx = 0.1;
        gbc.gridx = 1;
        gbc.gridy = 0;

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(this::listenerToAction);
        inputArea.add(sendButton, gbc);

        return inputArea;
    }

    private void listenerToAction(ActionEvent actionEvent)
    {
        String message = this.inputField.getText();
        if (message.length() == 0)
        {
            return;
        }

        if (this.corrTextArea.getText().length() != 0)
        {
            this.corrTextArea.append("\n");
        }

        this.corrTextArea.append("You: " + message);

        this.inputField.setText("");
    }
}