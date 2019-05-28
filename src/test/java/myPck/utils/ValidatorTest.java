package myPck.utils;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void validateFirstNameWillPass() {
        Assert.assertTrue(Validator.validateFirstName("John"));
    }

    @Test
    public void validateFirstNameWillFailWhenStartWithSmallLetter() {
        Assert.assertFalse(Validator.validateFirstName("john"));
    }

    @Test
    public void validateLastNameWillPass() {
        Assert.assertTrue(Validator.validateLastName("Doe"));
    }

    @Test
    public void validateLastNameWillFailWhenStartWithSmallLetter() {
        Assert.assertFalse(Validator.validateFirstName("john"));
    }

    @Test
    public void validatePasswordWillPass() {
        Assert.assertTrue(Validator.validatePassword("STR0ngP@ssword"));
    }

    @Test
    public void validatePasswordWillFail() {
        Assert.assertFalse(Validator.validatePassword("weakPassword"));
    }

    @Test
    public void validateEmailWillPass() {
        Assert.assertTrue(Validator.validateEmail("valid.email_2019@gmail.com"));
    }

    @Test
    public void validateEmailWillFailWhenProvidingDotAtTheBegin() {
        Assert.assertFalse(Validator.validateEmail(".email@gmail.com"));
    }

    @Test
    public void validateEmailWillFailWhenProvidingDotAtTheEnd() {
        Assert.assertFalse(Validator.validateEmail("email@gmail.com."));
    }

    @Test
    public void validateEmailWillFailWhenProvidingSpecialCharacters() {
        Assert.assertFalse(Validator.validateEmail("em#ail@gmail.com."));
    }
}