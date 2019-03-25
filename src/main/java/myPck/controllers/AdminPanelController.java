package myPck.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import myPck.Service;

public class AdminPanelController {

    @FXML
    public TableView<Service> usersTableView;
    @FXML
    public TableColumn<Service, String> loginColumn;
    @FXML
    public TableColumn<Service, String> firstNameColumn;
    @FXML
    public TableColumn<Service, String> lastNameColumn;
    @FXML
    public TableColumn<Service, String> roleColumn;

    /**
     * Lista zawierająca użytkowników
     */
    private ObservableList<Service> usersList;

    /**
     * Metoda dodaje nowego użytkownika do listy.
     * @param actionEvent
     */
    public void addUser(ActionEvent actionEvent) {
        Service service = new Service("Marekx", "Kowalski ", "Administrator");
        usersList.add(service);
        System.out.println("Dodano do listy nowego uzytkownika");
    }

    @FXML
    void initialize() {

        usersList = FXCollections.observableArrayList();
        usersTableView.setItems(this.usersList);

        loginColumn.setCellValueFactory(cellData-> cellData.getValue().carProperty());
        firstNameColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        lastNameColumn.setCellValueFactory(cellData-> cellData.getValue().clientProperty());
        roleColumn.setCellValueFactory(cellData-> cellData.getValue().statusProperty());
    }
}
