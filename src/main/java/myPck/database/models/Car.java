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
    private String Model;

    @Column(name = "brand", length = 40, nullable = false)
    private String Brand;

    @Column(name = "type", length = 40, nullable = false)
    private String Type;

    @Column(name = "ProductionsDate", length = 40, nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.java.util.Date)
    private String  ProductionsDate;



    public Car(String Model, String Brand, String Type, String ProductionsDate){
        this.Model=Model;
        this.Brand=Brand;
        this.Type=Type;
        this.ProductionsDate=ProductionsDate;
    }



    public long getId() {
        return id;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getProductionsDate() {
        return ProductionsDate;
    }

    public void setProductionsDate(String ProductionsDate) {
        this.ProductionsDate = ProductionsDate;
    }

    @Override
    public String toString() {
        return Brand + " " + Model;
    }
}
