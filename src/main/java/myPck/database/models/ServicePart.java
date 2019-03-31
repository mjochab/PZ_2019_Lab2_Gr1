package myPck.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServicePart {

    @Id
    @GeneratedValue
    private int id;
    private double price;
    private String name;
    private int service_id;
}