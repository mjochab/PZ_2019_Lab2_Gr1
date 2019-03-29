package myPck.database.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {

    public Client() {
    }

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name="incrementor", strategy = "increment")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="NIP_number")
    private int NIP_number;

    @Column(name="address")
    private String address;

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
