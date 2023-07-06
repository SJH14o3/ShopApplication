package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import source.Global;

import java.io.IOException;
import java.util.Objects;

public class WarehouseChange {
    static boolean ADD;
    static int ID;
    public WarehouseChange() {
        Stage stage = Global.getStage();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("warehouseChange.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
