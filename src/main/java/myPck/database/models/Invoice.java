package myPck.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private int id;
    private String date_of_issue;
    private double total_price;
    @OneToMany
    private List<InvoicePosition> invoicePositions;
}
