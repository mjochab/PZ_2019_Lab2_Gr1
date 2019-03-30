package myPck.database.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Client {

    public Client() {
    }

    public Client(String first_name, String last_name, int NIP_number, String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.NIP_number = NIP_number;
        this.address = address;
    }

    @Id
    @GeneratedValue
    private int id;
    private String first_name;
    private String last_name;
    private int NIP_number;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
}
