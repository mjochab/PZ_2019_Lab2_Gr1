package myPck.database.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Service {

    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Car car;
    @OneToOne
    private Client client;
    @OneToOne
    private Invoice invodice;
    @OneToOne
    private ServiceRaport serviceRaport;
    @OneToMany
    private List<InvoicePosition> invoicePositions;
    private String description;
    private String start_date;
    private String end_date;
    private String status;
    @OneToOne
    private User creater;
    @OneToOne
    private User mechanic;



}
