package myPck.database.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "model", length = 40, nullable = false)
    private String model;

    @Column(name = "brand", length = 40, nullable = false)
    private String brand;

    @Column(name = "type", length = 40, nullable = false)
    private String type;

    @Column(name = "production_date", length = 40, nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date productionDate;

    public Car() {
    }

    public Car(String model, String brand, String type, Date productionDate){
        this.model=model;
        this.brand=brand;
        this.type=type;
        this.productionDate=productionDate;
    }

    public long getId() {
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

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
