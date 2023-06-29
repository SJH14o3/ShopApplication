package source.application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Insert {
    @FXML
    protected TextField imageAddress;
    @FXML
    protected ImageView image;
    @FXML
    private void testImage() {
        try {
            image.setImage(new Image(imageAddress.getText()  + ".jpg"));
        }
        catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Picture with provided address found");
            alert.setContentText("Make sure:\nimage is in project file\nimage is .jpg\nyou have not entered \".jpg\" part");
            alert.showAndWait();
        }
    }
    protected boolean legitimateImageAddress(String address) {
        try {
            image.setImage(new Image(address + ".jpg"));
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
    protected double parsePrice(String in) {
        return Double.parseDouble(String.format("%.2f",Double.parseDouble(in)));
    }
    protected boolean checkPrice(double in) {
        if (in < 0.01 || in > 100.00) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Illegal price number . input in price field: " + in);
            alert.setContentText("Make sure: Price is between 0.01 and 100.00");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
