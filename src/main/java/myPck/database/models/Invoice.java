package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String date_of_issue;
    private double total_price;
    @OneToMany
    private List<InvoicePosition> invoicePositions;
}
