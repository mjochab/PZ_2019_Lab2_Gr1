package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service_parts")
public class ServicePart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Service service;

    public ServicePart() {
    }
    /**
     *
     * @param price
     * @param name
     */
    public ServicePart(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return this.service;
    }
}
