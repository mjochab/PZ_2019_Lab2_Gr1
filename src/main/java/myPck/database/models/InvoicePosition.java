package myPck.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InvoicePosition {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double price;
}
