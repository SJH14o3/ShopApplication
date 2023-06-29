package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import source.Global;
import source.products.placeAuctionThread;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceBidController implements Initializable {
    @FXML
    private Label userBalance, highestBid;
    @FXML
    private TextField enterBid;
    @FXML
    private Button submitButton, backButton;
    @FXML
    private void highlightSubmit() {
        submitButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 2");
    }
    @FXML
    private void deHighlightSubmit() {
        submitButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 2");
    }
    @FXML
    private void highlightBack() {
        backButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 2");
    }
    @FXML
    private void deHighlightBack() {
        backButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 2");
    }
    @FXML
    private void back() {
        Stage stage = Global.getStage();
        try {
            new AuctionPage(stage, AuctionPage.AUCTION_ID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void submit() {
            try {
                double bid = Double.parseDouble(enterBid.getText());
                bid = Double.parseDouble(String.format("%.2f", bid));
                System.out.println(bid);
                if (bid > Global.getBalance()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Insufficient Fund");
                    alert.setHeaderText("You have less balance than your input.");
                    alert.setContentText("Make sure input is less than your balance");
                    alert.showAndWait();
                }
                else if (bid < AuctionPage.highestBid) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Wrong price input");
                    alert.setHeaderText("Your input most be more than current bid");
                    alert.setContentText("Please Enter only digits and maximum one dot");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm?");
                    alert.setHeaderText("You are about to place bid with " + bid + "$.");
                    alert.setContentText("Are you Sure?");
                    ButtonType confirm = new ButtonType("Confirm");
                    ButtonType cancel = new ButtonType("Cancel");
                    alert.getButtonTypes().setAll(confirm, cancel);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == confirm) {
                        Thread placeBid = new placeAuctionThread(AuctionPage.AUCTION_ID , bid);
                        placeBid.start();
                        Alert done = new Alert(Alert.AlertType.INFORMATION);
                        done.setTitle("Success!");
                        done.setHeaderText("");
                        done.setContentText("Your Bid has been placed successfully!");
                        done.showAndWait();
                    }
                }
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong price input");
                alert.setHeaderText("Input is not a double");
                alert.setContentText("Please Enter only digits and maximum one dot");
                alert.showAndWait();
                return;
            }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userBalance.setText(Global.getBalance() + "$");
        highestBid.setText(AuctionPage.highestBid + "$");
        backButton.setGraphic(new ImageView(new Image("back_arrow.png")));
    }
}
