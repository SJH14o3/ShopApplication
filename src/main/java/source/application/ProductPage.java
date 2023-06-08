package source.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProductPage {

    public static int PRODUCT_ID;
    public ProductPage(Stage stage , int productID) throws IOException {

        PRODUCT_ID = productID;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("productPage.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);

    }
}
