package myPck.modelsFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DateFormat;

public class CarFx {
    private StringProperty Model = new SimpleStringProperty();
    private StringProperty Brand = new SimpleStringProperty();
    private StringProperty Type = new SimpleStringProperty();


    public CarFx (String Model, String Brand, String Type ){
        this.Model.set(Model);
        this.Brand.set(Brand);
        this.Type.set(Type);

    }
    public String getModel() {
        return Model.get();
    }

    public StringProperty ModelProperty() {
        return Model;
    }

    public String getBrand() {
        return Brand.get();
    }

    public StringProperty BrandProperty() {
        return Brand;
    }

    public String getType() {
        return Type.get();
    }

    public StringProperty TypeProperty() {
        return Type;
    }


}
