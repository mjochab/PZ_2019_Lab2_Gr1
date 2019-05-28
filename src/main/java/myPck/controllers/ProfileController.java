package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import myPck.utils.Validator;
import myPck.database.models.User;
import myPck.modelsFx.UserFx;
import myPck.services.UserService;

import java.util.HashMap;

import static myPck.utils.Password.hashPassword;
import static myPck.utils.Session.getCurrentUser;

public class ProfileController extends Controller {

    public TextField emailTextField;
    public Label infoLabel;
    private UserService userService;
    private User user;
    private UserFx userFx;

    @FXML
    private StackPane stackPane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField pass1Field;

    @FXML
    private PasswordField pass2Field;

    @FXML
    private Button saveButton;

    @FXML

    /**
     * Metoda zapisuje zmienne do HashMapy, następnie sprawdza prawidłowość danych,
     * jeżeli są poprawne to dane użytkownika zostają zmienione
     */
    void save(ActionEvent event) {
        HashMap<String, String> formData = new HashMap<String, String>();
        formData.put("login", loginField.getText());
        formData.put("firstName", firstNameField.getText());
        formData.put("lastName", lastNameField.getText());
        formData.put("email", emailTextField.getText());
        formData.put("password", pass1Field.getText());

        if (isInputValid(formData)) {
            this.user.setFirstName(formData.get("firstName"));
            this.user.setLastName(formData.get("lastName"));
            this.user.setEmail(formData.get("email"));
            this.user.setLogin(formData.get("login"));
            this.user.setPassword(hashPassword(formData.get("password")));
            this.userService.update(this.user);

            infoLabel.setText("Data has been changed");
            infoLabel.setTextFill(Color.web("#007600"));
            infoLabel.setVisible(true);
        }
    }

    /**
     * Metoda sprawdza czy wszystkie pola są prawidłowe i wyświetla komunikaty
     *
     * @param data
     * @return
     */
    private boolean isInputValid(HashMap<String, String> data) {
        infoLabel.setVisible(false);
        infoLabel.setTextFill(Color.web("#ff0000"));

        if (!Validator.validateFirstName(data.get("firstName"))) {
            infoLabel.setText("First name is incorrect");
            infoLabel.setVisible(true);

            return false;
        } else if (!Validator.validateLastName(data.get("lastName"))) {
            infoLabel.setText("Last name is incorrect");
            infoLabel.setVisible(true);

            return false;
        } else if (!Validator.validateEmail(data.get("email"))) {
            infoLabel.setText("Email address is incorrect");
            infoLabel.setVisible(true);

            return false;
        } else if (!Validator.validatePassword(pass1Field.getText())) {
            infoLabel.setText("Password is incorrect");
            infoLabel.setVisible(true);

            return false;
        } else if (!pass1Field.getText().equals(pass2Field.getText())) {
            infoLabel.setText("Passwords does not match");
            infoLabel.setVisible(true);

            return false;
        }

        return true;
    }

    @FXML
    void initialize() {
        convertUserToUserFx();
        setUpUser();
    }

    public ProfileController() {
        userService = new UserService();
        user = getCurrentUser();
    }

    public void convertUserToUserFx() {
        userFx = new UserFx(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getRole());
    }

    /**
     * Metoda ustawia odpowiednie typy w polach
     */
    public void setUpUser() {
        firstNameField.textProperty().bindBidirectional(userFx.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(userFx.lastNameProperty());
        loginField.textProperty().bindBidirectional(userFx.loginProperty());
        emailTextField.textProperty().bindBidirectional(userFx.emailProperty());
    }
}
