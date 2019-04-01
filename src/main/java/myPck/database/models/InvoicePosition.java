package myPck.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_positions")
public class InvoicePosition {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double price;
}
