package myPck.utils;

import org.junit.Assert;
import org.junit.Test;

import static myPck.utils.Password.checkPassword;
import static myPck.utils.Password.hashPassword;

public class PasswordTest {

    @Test
    public void hashPasswordIsBCrypt() {
        String newPassword = hashPassword("password");
        Assert.assertEquals(true, newPassword.startsWith("$2a$"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkPasswordWillThrowException() {
        checkPassword("weakPassword", "wrongHash");
    }
}