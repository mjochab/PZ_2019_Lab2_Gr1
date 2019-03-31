package myPck.database.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue
    private int id;
    private String date_of_issue;
    private double total_price;
    @OneToMany
    private List<InvoicePosition> invoicePositions;
}
