package myPck.database.models;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    public Client() {
    }
    /**
     *
     * @param String firstName
     * @param String lastName
     * @param int NipNumber
     * @param String address
     */
    public Client(String firstName, String lastName, int NipNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.NipNumber = NipNumber;
        this.address = address;
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="NIP_number")
    private int NipNumber;

    @Column(name="address", nullable = false)
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

    public int getNipNumber() {
        return NipNumber;
    }

    public void setNipNumber(int NipNumber) {
        this.NipNumber = NipNumber;
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
