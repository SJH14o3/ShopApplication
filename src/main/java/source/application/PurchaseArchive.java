package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PurchaseArchive {
    public PurchaseArchive(Stage stage) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("purchaseArchive.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
