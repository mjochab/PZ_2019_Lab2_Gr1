package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import myPck.database.models.User;
import myPck.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static myPck.utils.Password.checkPassword;

public class LogPanelController extends Controller {

    public Label errorLabel;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button loginButton;

    private UserService userService;

    public LogPanelController() {
        this.userService = new UserService();
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        mainStackPaneController.loadMainWindow();
    }
}
        /** Kod poniżej działa, został zakomentowany dla wygody debugowania aplikacji */

//        String login = loginField.getText();
  //      String password = passField.getText();


/*
        if (!login.isEmpty() && !password.isEmpty()) {
            errorLabel.setVisible(false);
            User user = userService.findByLogin(login);

            if (null != user) {
                String hashedPassInDatabase = user.getPassword();
                if (checkPassword(password, hashedPassInDatabase)) {
                    errorLabel.setVisible(false);
                    mainStackPaneController.setUser(user);
                    mainStackPaneController.setAccountType();
                    mainStackPaneController.loadMainWindow();
                }
            }
            errorLabel.setText("Wrong login or password.");
            errorLabel.setVisible(true);
        } else {
            errorLabel.setText("Provide login and password.");
            errorLabel.setVisible(true);
        }
    }
}
*/