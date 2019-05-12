package myPck.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.controllers.utils.Validator;
import myPck.database.models.User;
import myPck.modelsFx.UserFx;
import myPck.services.UserService;

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
    void save(ActionEvent event) {
        System.out.println("Zmieniono dane");
        infoLabel.setVisible(false);
//        System.out.println(firstNameField);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailTextField.getText();
        String password = pass1Field.getText();
        if (!Validator.validateFirstName(firstName)) {
            infoLabel.setText("First name is incorrect");
            System.out.println("wrong");
            infoLabel.setVisible(true);
        }
//        else if (!Validator.validateFirstName(firstName)) {
//
//        } else if (!Validator.validateFirstName(firstName)) {
//
//        }
       
//        this.user.setEmail(email);
//        this.user.setFirstName(firstName);
//        this.user.setLastName(lastName);
//        this.user.setPassword(password);
//        this.userService.update(this.user);
    }

    @FXML
    void initialize() {
//        User user = getUser();
//        this.user = new User();
//        this.user.setFirstName("Jan");
//        this.user.setLastName("Kowalski");
//        this.user.setEmail("sdg@gmail.com");
//        this.user.setLogin("JanK");
//        this.user = this.getUser();
//        this.getUser();
//        convertUserToUserFx();
//        setUpUser();
        System.out.println("init");
    }


    public ProfileController() {
        super();
        userService = new UserService();
//        this.user = mainStackPaneController.getUser();
        System.out.println("inicjalizacja");
        System.out.println(mainStackPaneController);
    }

    public void convertUserToUserFx() {
        userFx = new UserFx(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getRole());
    }

    public void setUpUser() {
        firstNameField.textProperty().bindBidirectional(userFx.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(userFx.lastNameProperty());
        loginField.textProperty().bindBidirectional(userFx.loginProperty());
    }

    public void getUser() {
        System.out.println(mainStackPaneController);
    }
}
