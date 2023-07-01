package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import source.Global;
import source.products.Auction;
import source.products.AuctionDataBase;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class InsertAuctionController extends Insert implements Initializable {
    @FXML
    private TextField titleTextField, bidTextField, hourTextField, minuteTextField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button backButton;
    public static String createDeadline(String in, int hour, int minute) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(in);
        if (hour < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(hour);
        if (minute < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(minute);
        return stringBuilder.toString();
    }

    public static boolean checkDate(String in, int hour, int minute) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String now = dtf.format(LocalDateTime.now());
        return createDeadline(in, hour, minute).compareTo(now) > 0;
    }

    @FXML
    private void parseInformation() {
        String title = titleTextField.getText();
        if (title.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please provide a title");
            alert.showAndWait();
            return;
        }
        if (title.length() > 65) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character Limit for title. title's length: " + title.length());
            alert.setContentText("Make sure: title field has less than 65 characters");
            alert.showAndWait();
            return;
        }
        double startingBid;
        try {
            if (bidTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please provide a starting bid");
                alert.showAndWait();
                return;
            }
            startingBid = parsePrice(bidTextField.getText());
            if (!checkPrice(startingBid)) {
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong price input. input in price field: \"" + bidTextField.getText() + "\"");
            alert.setContentText("Make sure: Price is only a double number with maximum 2 decimal digits");
            alert.showAndWait();
            return;
        }
        String pictureAddress = imageAddress.getText();
        if (pictureAddress.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please Provide a image");
            alert.showAndWait();
            return;
        }
        if (!legitimateImageAddress(pictureAddress)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Picture with provided address found");
            alert.setContentText("Make sure:\nimage is in project file\nimage is .jpg\nyou have not entered \".jpg\" part");
            alert.showAndWait();
            return;
        }
        if (datePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please provide a date");
            alert.showAndWait();
            return;
        }
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int hour;
        int minute;
        try {
            if (hourTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please provide an hour");
                alert.showAndWait();
                return;
            }
            hour = Integer.parseInt(hourTextField.getText());
            if (hour < 0 || hour > 23) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Illegal hour number . input in hour field: " + hour);
            alert.setContentText("Make sure: Hour is between 0 and 23");
            alert.showAndWait();
            return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong hour input. input in hour field: \"" + hourTextField.getText() + "\"");
            alert.setContentText("Make sure: hour is an integer from 0 to 23");
            alert.showAndWait();
            return;
        }
        try {
            if (minuteTextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Please provide a minute");
                alert.showAndWait();
                return;
            }
            minute = Integer.parseInt(minuteTextField.getText());
            if (minute < 0 || minute > 59) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Illegal minute number . input in minute field: " + minute);
                alert.setContentText("Make sure: Hour is between 0 and 59");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong minute input. input in minute field: \"" + minuteTextField.getText() + "\"");
            alert.setContentText("Make sure: minute is an integer from 0 to 59");
            alert.showAndWait();
            return;
        }
        if (!checkDate(date, hour, minute)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Illegal time. your entered time has been passed!");
            alert.setContentText("Make sure: entered time is after now");
            alert.showAndWait();
            return;
        }
        Auction auction = new Auction(0, title, startingBid, startingBid, createDeadline(date, hour, minute), pictureAddress);
        AuctionDataBase.insertAuction(auction, Global.getUser_id());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Successfully added auction to the shop!");
        alert.showAndWait();
        try {
            new AuctionPage(Global.getStage(), auction.getId() + 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void back() {
        try {
            new AuctionsMenu(Global.getStage(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (InsertAuction.extendTime) {
            InsertAuction.extendTime = false;
            backButton.setDisable(true);
            Auction auction = AuctionDataBase.getAuction(AuctionPage.AUCTION_ID);
            AuctionDataBase.deleteAuction(AuctionPage.AUCTION_ID);
            titleTextField.setText(auction.getTitle());
            bidTextField.setText(String.valueOf(auction.getStartingBid()));
            imageAddress.setText(auction.getImageAddress());
            image.setImage(new Image(imageAddress.getText() + ".jpg"));
        }
    }
}
