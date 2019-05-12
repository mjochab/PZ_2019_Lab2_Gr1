package myPck.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import myPck.database.models.User;
import java.io.IOException;

enum accountTypes {
    M,  //mechanik
    R,  //recepcjonista
    A,  //admin
    K,  //kierownik
    ALL, //wszystkie opcje
};

public class MainStackPaneController {

    /**
     * Zmienna odpowiadająca za typ konta.
     */
    public static accountTypes accountType;

    /**
     * Instancja najbardziej zewnętrzenego okna (pojemnika) na wszystkie pozostałe okienka aplikacji
     */
    @FXML
    private StackPane mainStackPane;
    public User user;

    /**
     * Funkcja wymieniająca okienka w mainStackPane
     * @param pane
     */
    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

    /**
     * Funkcja ładująca główne okno aplikacji (MainWindow.fxml)
     * @throws IOException
     */
    public void loadMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MainWindow.fxml"));
        StackPane stackPane = loader.load();

        MainWindowController mainWindowController = loader.getController();
        mainWindowController.setMainStackPaneController(this);
        this.setAccountType();
        mainWindowController.setUser(this.user);
        setScreen(stackPane);
    }

    /**
     * Pierwsze okno wyświetlane podczas uruchomienia aplikacji
     * w przyszłości będzie to prawdopodobnie okno logowania
     *
     * @throws IOException
     */
    @FXML
    void initialize() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LogPanel.fxml"));
        Pane pane = loader.load();
        LogPanelController logPanelController = loader.getController();
        logPanelController.setMainStackPaneController(this);
//        accountType = accountTypes.ALL; //TODO Usunąć tą linie przed publikacja aplikacji
        setScreen(pane);
    }

    /**
     * Metoda ustawiająca użytkownika po zalogowaniu
     * @param user
     */
    protected void setUser(User user) {
        this.user = user;
    }

    protected User getUser() {
        return this.user;
    }

    /**
     * Metoda ustawiająza typ konta
     */
    protected void setAccountType() {
        accountType = accountType.valueOf(this.user.getRole());
        /** TODO: odkomentować linie wyżej przed publikacja aplikacji*/
    }
}
