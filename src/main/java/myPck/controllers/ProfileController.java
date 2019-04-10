package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import myPck.database.models.User;
import myPck.modelsFx.UserFx;
import myPck.services.UserService;

import java.util.List;

public class ProfileController {


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
    }

    @FXML
    void initialize() {

//        this.user = new User();
//        this.user.setFirstName("Jan");
//        this.user.setLastName("Kowalski");
//        this.user.setEmail("sdg@gmail.com");
//        this.user.setLogin("JanK");
        this.user = this.getUser();
        convertUserToUserFx();
        setUpUser();


    }

    private UserService userService;
    private User user;
    private UserFx userFx;

    public ProfileController() {
        userService = new UserService();
    }

    public void convertUserToUserFx() {
       userFx = new UserFx(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getRole());
    }
    public void setUpUser() {

        firstNameField.textProperty().bindBidirectional(userFx.firstNameProperty());
        lastNameField.textProperty().bindBidirectional(userFx.lastNameProperty());
        loginField.textProperty().bindBidirectional(userFx.loginProperty());
        
    }

    public User getUser() {
        //tymczasowy kod do testów
        //---------------------------------------
        List<User> users = userService.findAll();
        User user = users.get(0);
        //---------------------------------------

        return userService.findById(user.getId());
    }
}
