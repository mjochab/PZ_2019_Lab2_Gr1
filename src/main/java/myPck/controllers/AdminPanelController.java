package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import myPck.database.models.User;
import myPck.modelsFx.UserFx;
import myPck.services.UserService;
import myPck.utils.Validator;

import java.util.HashMap;
import java.util.List;

import static myPck.utils.Password.hashPassword;

public class AdminPanelController {

    private UserService userService;
    private List<User> usersList;
    public Label infoLabel;
    private User selectedUser1;
    private User user;


    @FXML
    private TabPane tabPane;
    @FXML
    public TableView<UserFx> usersTableView;
    @FXML
    public TableColumn<UserFx, String> loginColumn;
    @FXML
    public TableColumn<UserFx, String> firstNameColumn;
    @FXML
    public TableColumn<UserFx, String> lastNameColumn;
    @FXML
    public TableColumn<UserFx, String> roleColumn;
    @FXML
    public TableColumn<UserFx, String> emailColumn;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Tab addEditUserTab;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField pass1Field;

    @FXML
    private PasswordField pass2Field;

    @FXML
    private TextField emailfield;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button saveButton;

    private boolean editMode = false;
    private ObservableList<String> roles = FXCollections.observableArrayList("M", "K", "ALL", "A");

    /**
     * Lista zawierająca użytkowników Fx
     */
    private ObservableList<UserFx> usersFxList;

    /**
     * Konstruktor inicjalizujący serwis użytkownika
     */
    public AdminPanelController() {
        userService = new UserService();
    }

    @FXML
    /**
     * Metoda dodaje użytkownika lub edytuje jego dane
     */
    void saveUser(ActionEvent event) {
        String name = firstNameField.getText();
        String surname = lastNameField.getText();
        String pass1 = pass1Field.getText();
        String pass2 = pass2Field.getText();
        String login = loginField.getText();
        String email = emailfield.getText();
        String role = roleComboBox.getValue();
        if (pass1.equals(pass2)) {
            if (editMode) {
                selectedUser1.setLogin(login);
                selectedUser1.setFirstName(name);
                selectedUser1.setLastName(surname);
                selectedUser1.setPassword(hashPassword(pass1));
                selectedUser1.setRole(role);
                selectedUser1.setEmail(email);
                userService.update(selectedUser1);
                selectedUser1 = null;
                addEditUserTab.setText("New User");
            } else {
                if (role != null) {
                    User newUser = new User();
                    newUser.setLogin(login);
                    newUser.setFirstName(name);
                    newUser.setLastName(surname);
                    newUser.setPassword(hashPassword(pass1));
                    newUser.setEmail(email);
                    newUser.setRole(role);
                    userService.persist(newUser);
                }
            }
            this.loadUsers();
            this.setUpUsersList();
            this.convertUsersToUsersFx();

            firstNameField.setText("");
            lastNameField.setText("");
            pass1Field.setText("");
            pass2Field.setText("");
            loginField.setText("");
            emailfield.setText("");
        }
        HashMap<String, String> formData = new HashMap<String,String>();
        formData.put("login", loginField.getText());
        formData.put("firstName",firstNameField.getText());
        formData.put("lastName", lastNameField.getText());
        formData.put("email", emailfield.getText());
        formData.put("password", pass1Field.getText());

        if (isInputValid(formData)){
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

    private boolean isInputValid(HashMap<String,String> data){
        infoLabel.setVisible(false);
        infoLabel.setTextFill(Color.web("#ff0000"));

        if (!Validator.validateFirstName(data.get("firstName"))){
            infoLabel.setText("First name is incorrect");
            infoLabel.setVisible(true);

            return false;
        }else if (!Validator.validateLastName(data.get("lastName"))){
            infoLabel.setText("Last name is incorrect");
            infoLabel.setVisible(true);

            return false;
        }else if (!Validator.validateEmail(data.get("email"))){
            infoLabel.setText("Email address is incorrect");
            infoLabel.setVisible(true);

            return false;
        }else if (!Validator.validatePassword(pass1Field.getText())){
            infoLabel.setText("Password is incorrect");
            infoLabel.setVisible(true);

            return false;
        }else if (!pass1Field.getText().equals(pass2Field.getText())){
            infoLabel.setText("Passwords does not match");
            infoLabel.setVisible(true);

            return false;
        }
        return true;
    }
    /**
     * Metoda pobiera użytkowników z bazy danych.
     */
    public void loadUsers() {
        usersList = userService.findAll();
    }

    /**
     * Metoda zamienia użytkwonika na użytkownika Fx i dodaje go do tablicy usersFxList
     */
    public void convertUsersToUsersFx() {
        if (!usersList.isEmpty()) {
            for (User user : usersList) {
                UserFx userFx = new UserFx(user.getEmail(), user.getFirstName(), user.getLastName(), user.getLogin(), user.getRole());
                usersFxList.add(userFx);
            }
        }
    }

    /**
     * Metoda przygotowuje tablice do przechowywania i wyświetlania danych.
     */
    public void setUpUsersList() {
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        this.roleComboBox.setItems(roles);
        usersFxList = FXCollections.observableArrayList();
        usersTableView.setItems(this.usersFxList);
    }

    @FXML
    void initialize() {
        this.loadUsers();
        this.setUpUsersList();
        this.convertUsersToUsersFx();
    }

    @FXML
    void deleteUser(ActionEvent event) {
        if (!usersFxList.isEmpty()) {
            int id = usersTableView.getSelectionModel().getSelectedIndex();
            User selected = usersList.get(id);

            boolean isDelete = userService.delete(selected.getId());

            if (isDelete) {
                usersList.clear();
                loadUsers();
                usersFxList.clear();
                convertUsersToUsersFx();
            }
        }
    }

    @FXML
    void editUser(ActionEvent event) {
        if (!usersFxList.isEmpty()) {
            this.editMode = true;
            int id = usersTableView.getSelectionModel().getSelectedIndex();
            selectedUser1 = usersList.get(id);
            addEditUserTab.setText("Edit");
            tabPane.getSelectionModel().selectLast();
            firstNameField.setText(selectedUser1.getFirstName());
            lastNameField.setText(selectedUser1.getLastName());
            loginField.setText(selectedUser1.getLogin());
            emailfield.setText(selectedUser1.getEmail());
        }
    }
}
