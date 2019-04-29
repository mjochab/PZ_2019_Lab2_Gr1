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
    private ComboBox<?> roleComboBox;

    @FXML
    private Button saveButton;



    /** Lista zawierająca użytkowników Fx*/
    private ObservableList<UserFx> usersFxList;

    /**
     * Konstruktor inicjalizujący serwis użytkownika
     */
    public AdminPanelController() {
        userService = new UserService();
    }

    /**
     * Metoda dodaje nowego użytkownika do listy.
     *
     * @param actionEvent
     */
    public void addNewUser() {
        String name = firstNameField.getText();
        String surname = lastNameField.getText();
        String pass1 = pass1Field.getText();
        String pass2 = pass2Field.getText();
        String login = loginField.getText();
        String role = "A";
        if(pass1.equals(pass2)){
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setFirstName(name);
            newUser.setLastName(surname);
            newUser.setPassword(hashPassword(pass1));
            newUser.setRole(role);
            newUser.setEmail("email@");
            userService.persist(newUser);
        }

    }
    public void editUser(){
        String name = firstNameField.getText();
        String surname = lastNameField.getText();
        String pass1 = pass1Field.getText();
        String pass2 = pass2Field.getText();
        String login = loginField.getText();
        String role = "A";
        if(pass1.equals(pass2)){
            selectedUser.setLogin(login);
            selectedUser.setFirstName(name);
            selectedUser.setLastName(surname);
            selectedUser.setPassword(hashPassword(pass1));
            selectedUser.setRole(role);
            selectedUser.setEmail("email@");
            userService.update(selectedUser);
            selectedUser = null;
        }
    }
    @FXML
    void saveUser(ActionEvent event) {
        addNewUser();
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

        usersFxList = FXCollections.observableArrayList();
        usersTableView.setItems(this.usersFxList);
    }

    @FXML
    void initialize() {
        this.loadUsers();
        this.setUpUsersList();
        this.convertUsersToUsersFx();
    }
}
