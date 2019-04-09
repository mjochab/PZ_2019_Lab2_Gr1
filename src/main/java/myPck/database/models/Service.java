package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "services")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Invoice invoice;
    @ManyToOne
    private ServiceReport serviceReport;
    @OneToMany
    private List<InvoicePosition> invoicePositions;
    @Column(name = "description", length = 500, nullable = true)
    private String description;

    @Column(name="start_date", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name="end_date", nullable = true, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "status", length = 40, nullable = false)
    private String status;
    @ManyToOne
    private User creater;
    @ManyToOne
    private User mechanic;

    public Service() {
    }

    /**
     *
     * @param client
     * @param car
     * @param status
     */
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
