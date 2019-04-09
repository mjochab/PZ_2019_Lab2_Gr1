package myPck.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "service_parts")
public class ServicePart implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private double price;
    private String name;
    private int service_id;


}
