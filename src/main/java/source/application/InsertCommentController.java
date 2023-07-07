package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import source.Global;
import source.database.PurchaseArchiveDataBase;
import source.products.Comment;
import source.products.CommentDataBase;

import java.net.URL;
import java.util.ResourceBundle;

public class InsertCommentController implements Initializable {
    private boolean isBuyer;
    @FXML
    private Label username, buyer;
    @FXML
    private TextArea text;
    @FXML
    private Button submitButton, backButton;
    @FXML
    private void highlightBack() {
        backButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 2");
    }
    @FXML
    private void deHighlightBack() {
        backButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 2");
    }
    @FXML
    private void highlightSubmit() {
        submitButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 2");
    }
    @FXML
    private void deHighlightSubmit() {
        submitButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 2");
    }
    @FXML
    private void back() {
        new CommentsMenu(Global.getStage());
    }
    @FXML
    private void submit() {
        String commentText = text.getText();
        if (commentText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please provide a comment");
            alert.showAndWait();
            return;
        }
        if (commentText.length() >= 300) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character Limit. comment's length: " + commentText.length());
            alert.setContentText("Make sure: inputted comment has less than 300 characters");
            alert.showAndWait();
            return;
        }
        Comment comment = new Comment(Global.getUser_id(), ProductPage.PRODUCT_ID, Global.getUsername(), commentText, isBuyer);
        CommentDataBase.InsertComment(comment);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Successfully submitted comment!");
        alert.showAndWait();
        back();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isBuyer = PurchaseArchiveDataBase.checkIfBuyer();
        if (!isBuyer) {
            buyer.setVisible(false);
        }
        username.setText(Global.getUsername());
    }
}
