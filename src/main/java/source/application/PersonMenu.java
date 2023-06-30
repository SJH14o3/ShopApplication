package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PersonMenu {
    private static int page = 1;

    public static void setPage(int page) {
        PersonMenu.page = page;
    }
    public static void incrementPage() {
        page++;
    }
    public static void decrementPage() {
        page--;
    }
    public static int getPage() {
        return page;
    }
    public PersonMenu(Stage stage, boolean rememberPage) throws IOException {
        if (!rememberPage) {
            setPage(1);
        }
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PersonPage.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
    }
}
