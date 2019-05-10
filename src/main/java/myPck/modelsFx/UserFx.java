package myPck.modelsFx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserFx {

    private StringProperty email = new SimpleStringProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty login = new SimpleStringProperty();
    private StringProperty role = new SimpleStringProperty();

    public UserFx(String email, String firstName, String lastName, String login, String role) {
        this.email.set(email);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.login.set(login);
        this.role.set(role);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty loginProperty() {
        return login;
    }


    public StringProperty roleProperty() {
        return role;
    }
}
