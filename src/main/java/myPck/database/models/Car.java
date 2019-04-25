package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="cars")
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


    public Car(String model, String brand, String type){
        this.model=model;
        this.brand=brand;
        this.type=type;

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


    @Override
    public String toString() {
        return brand + " " + model;
    }
}
