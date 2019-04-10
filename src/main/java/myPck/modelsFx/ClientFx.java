package myPck.modelsFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientFx {
    private StringProperty name = new SimpleStringProperty();

    public ClientFx(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
