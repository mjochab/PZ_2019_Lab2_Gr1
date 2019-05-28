package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import myPck.database.models.User;
import myPck.services.UserService;

import java.io.IOException;

import static myPck.utils.Password.checkPassword;
import static myPck.utils.Session.setCurrentUser;

public class LogPanelController extends Controller {

    public Label errorLabel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    private UserService userService;

    public LogPanelController() {
        this.userService = new UserService();
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        String login = loginField.getText();
        String password = passField.getText();
        try {
            if (!login.isEmpty() && !password.isEmpty()) {
                errorLabel.setVisible(false);
                User user = userService.findByLogin(login);

                if (null != user) {
                    String hashedPassInDatabase = user.getPassword();
                    if (checkPassword(password, hashedPassInDatabase)) {
                        errorLabel.setVisible(false);
                        mainStackPaneController.setUser(user);
                        mainStackPaneController.setAccountType();
                        setCurrentUser(user);

                        mainStackPaneController.loadMainWindow();
                    }
                }
                errorLabel.setText("Wrong login or password.");
                errorLabel.setVisible(true);
            } else {
                errorLabel.setText("Provide login and password.");
                errorLabel.setVisible(true);
            }
        } catch (IllegalArgumentException e) {
            errorLabel.setText("Invalid login and password.");
            errorLabel.setVisible(true);
        }
    }
}
