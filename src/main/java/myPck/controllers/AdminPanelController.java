package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import myPck.database.models.User;
import myPck.modelsFx.UserFx;
import myPck.services.UserService;

import java.util.List;

import static myPck.utils.Password.hashPassword;

public class AdminPanelController {

    private UserService userService;
    private List<User> usersList;
    private User selectedUser = null;

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
                selectedUser.setLogin(login);
                selectedUser.setFirstName(name);
                selectedUser.setLastName(surname);
                selectedUser.setPassword(hashPassword(pass1));
                selectedUser.setRole(role);
                selectedUser.setEmail(email);
                userService.update(selectedUser);
                selectedUser = null;
                addEditUserTab.setText("New User");
            } else {
                if (role != null) {
//                    User newUser = new User(email, name, surname);
//                    newUser.setLogin(login);
//                    newUser.setFirstName(name);
//                    newUser.setLastName(surname);
//                    newUser.setPassword(hashPassword(pass1));
//                    newUser.setEmail(email);
//                    newUser.setRole(role);
//                    userService.persist(newUser);
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
            selectedUser = usersList.get(id);
            addEditUserTab.setText("Edit");
            tabPane.getSelectionModel().selectLast();
            firstNameField.setText(selectedUser.getFirstName());
            lastNameField.setText(selectedUser.getLastName());
            loginField.setText(selectedUser.getLogin());
            emailfield.setText(selectedUser.getEmail());
        }
    }
}
