
package source.application;

        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.util.Objects;

public class PaymentMenu {
    public static int beforePaymentPage = 1; //1:cart, 2:person page.
    public static double changedBalance;
    public PaymentMenu(Stage stage) throws IOException {
        stage.setWidth(958);
        stage.setHeight(589);
        stage.setX(481);
        stage.setY(245);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PaymentPage.fxml")));
        Scene scene = new Scene(root, Color.WHITE);
        stage.setScene(scene);
    }
}
