package myPck.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * typ enum potrzebny tylko do testowania widoku
 */
enum Type_of_account{
    M,  //mechanik
    R,  //recepcjonista
    A,  //admin
    K,  //kierownik
};
public class MainStackPaneController {

    //TU USTAW TYP KONTA !!!!!!!!!!!!!!!!!!!!!!
    public static Type_of_account ACCOUNT = Type_of_account.M;

    @FXML
    //instancja najbardziej zewnętrzenego okna (pojemnika) na wszystkie pozostałe okienka aplikacji
    private StackPane mainStackPane;

     /**
     * funkcja wymieniająca okienka w mainStackPane
     * @param pane
     */
    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

    /**
     * funkcja ładująca główne okno aplikacji (MainWindow.fxml)
     * @throws IOException
     */
    public void loadMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainWindow.fxml"));
        StackPane stackPane = loader.load();

        MainWindowController mainWindowController = loader.getController();
        mainWindowController.setMainStackPaneController(this);
        setScreen(stackPane);
    }
    @FXML
    void initialize() throws IOException {
        //pierwsze okno wyświetlane podczas uruchomienia aplikacji
        //w przyszłości będzie to prawdopodobnie okno logowania
        loadMainWindow();
    }
}
