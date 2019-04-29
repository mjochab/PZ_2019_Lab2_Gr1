package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "model", length = 40, nullable = false)
    private String model;

    @Column(name = "brand", length = 40, nullable = false)
    private String brand;

    @Column(name = "type", length = 40, nullable = false)
    private String type;
    @Column(name = "productionsDate", length = 40, nullable = false)
    private Date prductionsDate;


    public Car(String model, String brand, String type, String productionsDate) {
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.prductionsDate = new Date(Integer.parseInt(productionsDate), 1, 1);

    }


    public Car() {
    }

    public long getId() {
        return id;
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

    public String getPrductionsDate() {
        return prductionsDate.getYear()+"";
    }

    public void setPrductionsDate(String prductionsDate) {
        this.prductionsDate = new Date(Integer.parseInt(prductionsDate), 1, 1);
    }

    @Override
    public String toString() {
        return brand + " " + model;
    }
}
