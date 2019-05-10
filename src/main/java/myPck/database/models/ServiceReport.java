package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service_reports")
public class ServiceReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description", length = 500, nullable = true)
    private String description;

    @OneToOne
    private Service service;


    public ServiceReport() {
    }

    /**
     *
     * @param description
     */
    public ServiceReport(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return this.service;
    }
}
