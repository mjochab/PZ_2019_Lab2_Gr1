package myPck.modelsFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServiceItemFx {

    private IntegerProperty price = new SimpleIntegerProperty();
    private StringProperty serviceName = new SimpleStringProperty();

    public ServiceItemFx(String serviceName, Integer price) {
        this.serviceName.set(serviceName);
        this.price.set(price);
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public StringProperty serviceNameProperty() {
        return serviceName;
    }
}
