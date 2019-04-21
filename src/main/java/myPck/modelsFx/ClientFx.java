package myPck.modelsFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientFx {

    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty NIP_number = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();

    public ClientFx(String firstName, String lastName, String NIP_number, String address) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.NIP_number.set(NIP_number);
        this.address.set(address);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getNIP_number() {
        return NIP_number.get();
    }

    public StringProperty NIP_numberProperty() {
        return NIP_number;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }
}
