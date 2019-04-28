package myPck.modelsFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServicePartFx {

    private StringProperty serviceName = new SimpleStringProperty();
    private StringProperty price = new SimpleStringProperty();

    public ServicePartFx(String serviceName, String price) {
        this.serviceName.set(serviceName);
        this.price.set(price);
    }

    public StringProperty priceProperty() {
        return price;
    }

    public StringProperty serviceNameProperty() {
        return serviceName;
    }

}
