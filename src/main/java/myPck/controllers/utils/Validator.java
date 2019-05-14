package myPck.controllers.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static void convertTextFieldToNumberField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public static void setMaxLengthOfTextField(TextField textField, int length) {
        textField.setOnKeyTyped(event -> {
            if (textField.getText().length() >= length) event.consume();
        });
    }

    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[A-Z][a-zA-Z]*");
    }

    public static boolean validateLastName(String lastName) {
        return lastName.matches("[A-Z][a-zA-Z]*");
    }

    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})");
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        return email.matches("\\b^[a-z][.\\w]+@[a-z0-9]+\\.[a-z]{2,3}\\b(.[a-z]{2})?$");
    }
}
