package myPck.controllers.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;


public class Validator {
    public static void convertTextFieldToNumberField(TextField textField){
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
    public static void setMaxLengthOfTextField(TextField textField, int length){
        textField.setOnKeyTyped(event ->{
            if(textField.getText().length() >= length) event.consume();
        });
    }

}
