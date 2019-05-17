package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "services")
public class Service implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Invoice invoice;

    @OneToOne(mappedBy = "service")
    private ServiceReport serviceReport;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private List<ServicePart> serviceParts;

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
    private User creator;

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

    public Client getClientInstance(){
        return this.client;
    };

    public String getStatus() {
        return status;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Invoice getInvoice() {
        return this.invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ServiceReport getServiceReport() {
        return serviceReport;
    }

    public void setServiceReport(ServiceReport serviceReport) {
        this.serviceReport = serviceReport;
    }

    public List<ServicePart> getServiceParts() {
        return this.serviceParts;
    }

    public void setServiceParts(List<ServicePart> serviceParts) {
        this.serviceParts = serviceParts;
    }

    public List<InvoicePosition> getInvoicePositions() {
        return invoicePositions;
    }

    public void setInvoicePositions(List<InvoicePosition> invoicePositions) {
        this.invoicePositions = invoicePositions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreator() {
        return creator;
    }

    public long getId() {
        return id;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getMechanic() {
        return mechanic;
    }

    public void setMechanic(User mechanic) {
        this.mechanic = mechanic;
    }
}
