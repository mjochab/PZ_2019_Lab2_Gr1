package myPck.modelsFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CarFx {
    private StringProperty name = new SimpleStringProperty();

    public CarFx(String name) {
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
