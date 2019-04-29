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

public class AdminPanelController {

    private UserService userService;
    private List<User> usersList;

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
    public void addUser() {

    }
    @FXML
    void saveUser(ActionEvent event) {

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
