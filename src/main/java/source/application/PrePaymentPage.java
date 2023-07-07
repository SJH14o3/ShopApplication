package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PrePaymentPage {
    public PrePaymentPage(Stage stage) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("prePaymentPage.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);

    }
}
