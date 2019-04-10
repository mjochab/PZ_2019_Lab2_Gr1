package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "invoice_positions")
public class InvoicePosition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 40, nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;

    public InvoicePosition(){
    }
    /**
     *
     * @param name
     * @param price
     */
    public InvoicePosition(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
