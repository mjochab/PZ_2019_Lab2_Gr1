package myPck.database.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ServiceRaport {

    public ServiceRaport() {
    }

    public ServiceRaport(String description) {
        this.description = description;
    }


    @Id
    @GeneratedValue
    private int id;
    private String description;

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
