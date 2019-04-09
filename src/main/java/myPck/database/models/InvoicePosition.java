package myPck.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "invoice_positions")
public class InvoicePosition implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double price;

    public InvoicePosition(){

    }

    public InvoicePosition(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
