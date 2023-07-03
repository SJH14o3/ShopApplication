package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import source.Global;
import source.exceptions.NoBidException;
import source.exceptions.PassedDeadLineException;
import source.products.Auction;
import source.products.AuctionDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuctionPageController implements Initializable {
    Auction auction;
    @FXML
    private Label title, highestBid, userHighestBid, timeLeft, deadline, startingBid;
    @FXML
    private ImageView image;
    @FXML
    private Button backButton, bid;
    @FXML
    private void back() {
        Stage stage = Global.getStage();
        try {
            new AuctionsMenu(stage, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    private void highlightBid() {
        bid.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 2");
    }
    @FXML
    private void deHighlightBid() {
        bid.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 2");
    }
    @FXML
    private void placeBid() {
        Stage stage = Global.getStage();
        new PlaceBid(stage);
        //AuctionDataBase.placeBid(auction.getId(), 99.99, Global.getUser_id());
    }
    private void setDeadlineText() {
        StringBuilder stringBuilder = new StringBuilder();
        String deadlineStr = auction.getDeadline();
        stringBuilder.append(Global.getMonthName(Integer.parseInt(deadlineStr.substring(4, 6)))).append(" ");
        int day = Integer.parseInt(deadlineStr.substring(6, 8));
        stringBuilder.append(day);
        String appendix;
        if (day > 3 && day < 21) {
            appendix = "th";
        }
        else {
            switch (day % 10) {
                case 1 -> appendix = "st";
                case 2 -> appendix = "nd";
                case 3 -> appendix = "rd";
                default -> appendix = "th";
            }
        }
        stringBuilder.append(appendix);
        stringBuilder.append(" ").append(deadlineStr, 0, 4).append(", ");
        stringBuilder.append(deadlineStr, 8, 10).append(":").append(deadlineStr, 10, 12);
        deadline.setText("Auction's Deadline: " + stringBuilder);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        auction = AuctionDataBase.getAuction(AuctionPage.AUCTION_ID);
        AuctionPage.highestBid = auction.getHighestBid();
        backButton.setGraphic(new ImageView(new Image("back_arrow.png")));
        image.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Products/" + auction.getImageAddress() + ".jpg"))));
        title.setText(auction.getTitle());
        highestBid.setText(auction.getHighestBid() + "$");
        startingBid.setText(auction.getStartingBid() + "$");
        try {
            double userBid = (AuctionDataBase.getHighestUserBid(Global.getUser_id(), AuctionPage.AUCTION_ID));
            userHighestBid.setText(userBid + "$");
        } catch (NoBidException e) {
            userHighestBid.setText("-");
        }
        setDeadlineText();
        try {
            timeLeft.setText("Time Left: " + auction.calculateDeadLine());
        } catch (PassedDeadLineException e) {
            e.printStackTrace();
        }
    }
}
