package myPck.database.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private long id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name="first_name", length = 25)
    private String firstName;

    @Column(name="last_name", length = 40)
    private String lastName;

    @Column(name="login", length = 25)
    private String login;

    @Column(name="password", length = 40)
    private String password;

    @Column(name="role", length = 25)
    private String role;

    public User() {
    }

    public User(String email, String first_name, String last_name, String login, String password, String role) {
        this.email = email;
        this.firstName = first_name;
        this.lastName = last_name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

//    public String getId() {
//        return id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User: " + this.login;
    }
}
