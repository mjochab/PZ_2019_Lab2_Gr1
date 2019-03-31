package myPck.database.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    public Client() {
    }

    public Client(String firstName, String lastName, int NIP_number, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.NIP_number = NIP_number;
        this.address = address;
    }

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private int NIP_number;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public int getNIP_number() {
        return NIP_number;
    }

    public void setNIP_number(int NIP_number) {
        this.NIP_number = NIP_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
