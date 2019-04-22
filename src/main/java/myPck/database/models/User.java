package myPck.database.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private long id;

    @Column(name = "email", length = 40, nullable = false, unique = true)
    private String email;

    @Column(name="first_name", length = 25)
    private String firstName;

    @Column(name="last_name", length = 40)
    private String lastName;

    @Column(name="login", length = 25, nullable = false, unique = true)
    private String login;

    @Column(name="password", length = 60, nullable = false)
    private String password;

    @Column(name="role", length = 25, nullable = false)
    private String role;

    public User() {
    }
    /**
     *
     * @param email
     * @param first_name
     * @param last_name
     * @param login
     * @param password
     * @param role
     */
    public User(String email, String first_name, String last_name, String login, String password, String role) {
        this.email = email;
        this.firstName = first_name;
        this.lastName = last_name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

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
