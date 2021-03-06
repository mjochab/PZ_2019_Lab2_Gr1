package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_of_issue", length = 40, nullable = true, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_of_issue;
    @Column(name = "total_price", nullable = true)
    private double total_price;
    @OneToMany
    private List<InvoicePosition> invoicePositions;

    public Invoice() {
    }
    /**
     *
     * @param date_of_issue
     * @param total_price
     */
    public Invoice(Date date_of_issue, double total_price) {
        this.date_of_issue = date_of_issue;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public Date getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(Date date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public List<InvoicePosition> getInvoicePositions() {
        return invoicePositions;
    }

    public void setInvoicePositions(List<InvoicePosition> invoicePositions) {
        this.invoicePositions = invoicePositions;
    }
}
