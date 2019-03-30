package myPck.database.models;

import javax.persistence.*;

@Entity
public class Car {

    public Car() {
    }
    public Car(String model, String brand, String type, String productionDate){
        this.model=model;
        this.brand=brand;
        this.type=type;
        this.productionDate=productionDate;
    }

    @Id
    @GeneratedValue
    private int id;
    private String model;
    private String brand;
    private String type;
    private String productionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
}
