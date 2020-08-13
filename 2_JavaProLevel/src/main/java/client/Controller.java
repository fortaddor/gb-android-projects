package client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import static common.ChatConsts.*;

public class Controller implements Initializable
{
    @FXML
    TextArea textArea;

    @FXML
    TextField msgField, loginField;

    @FXML
    HBox msgPanel, authPanel, infoPanel;

    @FXML
    PasswordField passField;

    @FXML
    ListView<String> clientsList;

    private Network network;
    private String nickname;

    public void setAuthenticated(boolean authenticated)
    {
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        msgPanel.setVisible(authenticated);
        msgPanel.setManaged(authenticated);
        infoPanel.setVisible(authenticated);
        infoPanel.setManaged(authenticated);

        if (!authenticated)
        {
            nickname = "";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setAuthenticated(false);
        createNetwork();
        network.connect();
        passField.requestFocus();
    }

    public void sendAuth()
    {
        network.sendAuth(loginField.getText(), passField.getText());
        loginField.clear();
        passField.clear();
    }

    public void sendMsg()
    {
        if (network.sendMsg(msgField.getText()))
        {
            msgField.clear();
            msgField.requestFocus();
        }
    }

    public void sendExit()
    {
        network.sendMsg(KEY_END);
    }

    public void showAlert(String msg)
    {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
            alert.showAndWait();
        });
    }

    public void createNetwork()
    {
        this.network = new Network();
        this.network.setCallOnException(args -> showAlert(args[0]));

        this.network.setCallOnCloseConnection(args -> setAuthenticated(false));

        this.network.setCallOnAuthenticated(args -> {
            setAuthenticated(true);
            this.nickname = args[0];
        });

        this.network.setCallOnMsgReceived(args -> {
            String msg = args[0];
            String[] tokens = msg.split(STR_SPLIT);

            if (tokens[0].equals(KEY_CLIENTS))
            {
                Platform.runLater(() -> {
                    clientsList.getItems().clear();
                    for (int i = 1; i < tokens.length; i++)
                    {
                        if (!this.nickname.equals(tokens[i]))
                        {
                            this.clientsList.getItems().add(tokens[i]);
                        }
                    }
                });
            }
            else if (tokens[0].equals(KEY_EXCEPTION))
            {
                showAlert(tokens[1]);
            }
            else if (tokens[0].equals(KEY_PRIV))
            {
                this.textArea.appendText(this.nickname + " to " + tokens[1] + "> " + tokens[2] + STR_LF);
            }
            else
            {
                this.textArea.appendText(msg + STR_LF);
            }
        });
    }
}