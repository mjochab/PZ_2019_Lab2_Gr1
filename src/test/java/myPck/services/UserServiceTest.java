package myPck.services;

import myPck.database.models.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static myPck.utils.Password.hashPassword;

public class UserServiceTest {

    @Test
    public void canGetAllByRole() {
        UserService userService = new UserService();
        List<User> mechanics;

        mechanics = userService.findAllByRole("M");

        for (User mechanic : mechanics) {
            Assert.assertEquals("M", mechanic.getRole());
        }
    }


    @Test(expected = NullPointerException.class)
    public void canSaveRetrieveAndDeleteUser() {
        UserService userService = new UserService();
        User testUser = new User(
                "testemail@gmail.com",
                "first_name",
                "last_name",
                "uniqueLogin",
                hashPassword("password"),
                "M"
        );

        userService.persist(testUser);
        User expectedUser = userService.findByLogin(testUser.getLogin());

        Assert.assertEquals("uniqueLogin", expectedUser.getLogin());
        userService.delete(expectedUser.getId());

        User deletedUser = userService.findByLogin(testUser.getLogin());
        Assert.assertEquals(null, deletedUser.getLogin());
    }
}