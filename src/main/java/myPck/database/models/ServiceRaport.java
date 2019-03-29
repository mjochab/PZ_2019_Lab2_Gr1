package myPck.database.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="service_raports")
public class ServiceRaport {
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name="incrementor", strategy = "increment")
    private int id;

    @Column(name="description")
    private String description;

    public ServiceRaport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
