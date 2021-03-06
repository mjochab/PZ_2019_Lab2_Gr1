package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import myPck.database.models.Company;
import javafx.scene.paint.Color;
import myPck.database.models.User;
import myPck.modelsFx.UserFx;
import myPck.services.UserService;
import myPck.utils.Validator;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static myPck.utils.Password.hashPassword;

public class AdminPanelController {

    private UserService userService;
    private List<User> usersList;
    public Label infoLabel;
    private User selectedUser;



    @FXML
    private TextField companyNameTextField;
    @FXML
    private TextField companyAddressTextField;
    @FXML
    private TextField companyNipTextField;
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

        HashMap<String, String> formData = new HashMap<String, String>();
        formData.put("login", login);
        formData.put("firstName", name);
        formData.put("lastName", surname);
        formData.put("email", email);
        formData.put("password", pass1);
        formData.put("password2",pass2);


        if (isInputValid(formData)) {
            selectedUser.setLogin(login);
            selectedUser.setFirstName(name);
            selectedUser.setLastName(surname);
            selectedUser.setEmail(email);
            selectedUser.setPassword(hashPassword(pass1));
            selectedUser.setPassword(hashPassword(pass2));
            userService.update(selectedUser);
            selectedUser = null;
            addEditUserTab.setText("New User");

            infoLabel.setText("Data has been changed");
            infoLabel.setTextFill(Color.web("#007600"));
            infoLabel.setVisible(true);
        }

            if (editMode) {
                selectedUser.setLogin(login);
                selectedUser.setFirstName(name);
                selectedUser.setLastName(surname);
                selectedUser.setEmail(email);
                selectedUser.setPassword(hashPassword(pass1));
                selectedUser.setPassword(hashPassword(pass2));
                userService.update(selectedUser);
                selectedUser = null;
                addEditUserTab.setText("New User");
            }
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
    void initialize() throws IOException, ClassNotFoundException {
        this.loadUsers();
        this.setUpUsersList();
        this.convertUsersToUsersFx();
        this.setCompanyData();
    }
    private void setCompanyData() throws IOException, ClassNotFoundException {
        Company company = Company.readObjectFromFile("company.txt");
        companyNameTextField.setText(company.getName());
        companyAddressTextField.setText(company.getAddress());
        companyNipTextField.setText(company.getNip());
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
            tabPane.getSelectionModel().select(1);
            firstNameField.setText(selectedUser.getFirstName());
            lastNameField.setText(selectedUser.getLastName());
            loginField.setText(selectedUser.getLogin());
            emailfield.setText(selectedUser.getEmail());
        }
    }
    @FXML
    void saveCompany(ActionEvent event) throws IOException {
        String name = companyNameTextField.getText();
        String address = companyAddressTextField.getText();
        String nip = companyNipTextField.getText();
        Company company = new Company(name,address,nip);
        company.writeToFile("company.txt");
    }
}
