package myPck;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServiceFx {

    public static int amount;
    private StringProperty car = new SimpleStringProperty();
    private StringProperty client = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    public ServiceFx(String car, String client, String status) {
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
        return "ServiceFx{" +
                "car=" + car +
                ", client=" + client +
                ", status=" + status +
                '}';
    }
}

