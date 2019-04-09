package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "services")
public class Service implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Car car;
    @ManyToOne
    private Client client;
    @OneToOne
    private Invoice invoice;
    @OneToOne
    private ServiceReport serviceReport;
    @OneToMany
    private List<InvoicePosition> invoicePositions;
    private String description;

    @Column(name="start_date", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name="end_date", nullable = true, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    private String status;
    @OneToOne
    private User creater;
    @OneToOne
    private User mechanic;

    public Service() {
    }

    public Service(Client client, Car car, String status) {
        this.client = client;
        this.car = car;
        this.status = status;
        this.startDate = new Date();
    }

    public String getCar() {
        return car.getBrand() + " " + car .getModel();
    }

    public String getClient() {
        return client.getFirstName() + " " + client.getLastName();
    }

    public String getStatus() {
        return status;
    }
}
