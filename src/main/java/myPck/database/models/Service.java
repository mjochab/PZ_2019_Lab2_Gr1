package myPck.database.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// klasa służąca jedynie do testowania działania widoku MainWindow
// w przysłości ta klasa będzie wymieniona na obiekt reprezentujący zlecenie
public class Service {

    public static int amount;
    private StringProperty car = new SimpleStringProperty();
    private StringProperty client = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    public Service(String car, String client, String status) {
        amount++;
        this.car.set(car);
        this.client.set(client);
        this.status.set(status);
    }

    public String getCar() {
        return car.get();
    }

    public StringProperty carProperty() {
        return car;
    }

    public void setCar(String car) {
        this.car.set(car);
    }

    public String getClient() {
        return client.get();
    }

    public StringProperty clientProperty() {
        return client;
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    @Override
    public String toString() {
        return "Service{" +
                "car=" + car +
                ", client=" + client +
                ", status=" + status +
                '}';
    }
}

