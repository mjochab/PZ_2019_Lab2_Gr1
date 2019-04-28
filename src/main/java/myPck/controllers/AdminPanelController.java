package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import myPck.database.models.User;
import myPck.modelsFx.UserFx;
import myPck.services.UserService;

import java.io.IOException;
import java.util.List;

public class AdminPanelController extends Controller {


    private UserService userService;
    private List<User> usersList;
    private ObservableList<String> userNameList;

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
    public Button addUserButton;
    @FXML
    public Button cancelButton;


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
    public void addUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AdminPanelListUsers.fxml"));
        Pane pane = loader.load();
        AdminPanelController adminPanelController = loader.getController();
        adminPanelController.setMainStackPaneController(mainStackPaneController);
        mainStackPaneController.setScreen(pane);
    }
    public void editUser(ActionEvent actionEvent) throws IOException {
        if (usersList.isEmpty()) {
            int id = usersTableView.getSelectionModel().getSelectedIndex();
            User selected = usersList.get(id);
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/AdminPanelListUsers.fxml"));
            Pane pane = loader.load();
            AdminPanelController adminPanelController = loader.getController();
            adminPanelController.setMainStackPaneController(mainStackPaneController);
            mainStackPaneController.setScreen(pane);
            adminPanelController.setUser(selected);
        }
    }
    @FXML
     void deleteUser(ActionEvent event) throws IOException {
        if (userNameList.isEmpty()) {
            int id = usersTableView.getSelectionModel().getSelectedIndex();
            User selected = usersList.get(id);
            boolean isDelete = userService.delete(selected.getId());
            if(isDelete){
                System.out.println("Usunięto");
                userNameList.clear();
                loadUsers();
                appendUsertoUserFx();
            }else{
                System.out.println("Nie usnięto");
            }
    }
    }

    @FXML
    void Cancel(ActionEvent event) throws IOException {
        System.out.println("Anuluje tworzenie zlecenia");
        mainStackPaneController.loadMainWindow();
    }

    private void appendUsertoUserFx() {
        if(!usersList.isEmpty()){
            for(User user : usersList){
               userNameList.add(user.toString());
            }
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

        usersFxList = FXCollections.observableArrayList();
        usersTableView.setItems(this.usersFxList);
    }
    public void setUser(User user){
        this.user = user;
        this.userFx = convertUsersToUsersFx(user);
        setFileBildinds();
    }
    private UserFx convertUsersToUsersFx(User user){
       return new UserFx(user.getFirstName(),user.getLastName(),user.getEmail(),user.getLogin(),user.getRole());
    }
    private void setFileBildinds() {
        firstNameColumn.textProperty().bindBidirectional(userFx.firstNameProperty());
        lastNameColumn.textProperty().bindBidirectional(userFx.lastNameProperty());
        loginColumn.textProperty().bindBidirectional(userFx.loginProperty());
        roleColumn.textProperty().bindBidirectional(userFx.roleProperty());

    }
    private User user = null;
    private UserFx userFx ;


    @FXML
    void initialize() {
        this.loadUsers();
        this.setUpUsersList();
        this.convertUsersToUsersFx();

    }

    public void setUserNameList(ObservableList<String> userNameList) {
        this.userNameList = userNameList;
    }
}
