package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import source.Global;
import source.products.Comment;
import source.products.CommentDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentsMenuController implements Initializable {
    @FXML
    private Button backButton, previousButton, nextButton, submitButton;
    @FXML
    private Label username0, username1, username2, username3, username4, username5;
    private final Label[] usernames = new Label[6];
    @FXML
    private Label buyer0, buyer1, buyer2, buyer3, buyer4, buyer5;
    private final Label[] buyers = new Label[6];
    @FXML
    private Label comment0, comment1, comment2, comment3, comment4, comment5;
    private final Label[] commentFields = new Label[6];
    @FXML
    private AnchorPane noComment, pane0, pane1, pane2, pane3, pane4, pane5;
    private final AnchorPane[] panes = new AnchorPane[6];
    @FXML
    private Label pageCounter;
    private Comment[] comments;

    private int first, last, page;
    @FXML
    private void highlightBack() {
        backButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 2");
    }
    @FXML
    private void deHighlightBack() {
        backButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 2");
    }
    @FXML
    private void highlightNext() {
        if (!nextButton.isDisable()) {
            nextButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 1");
        }
    }
    @FXML
    private void deHighlightNext() {
        if (!nextButton.isDisable()) {
            nextButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 1");
        }
    }
    @FXML
    private void highlightPrevious() {
        if (!nextButton.isDisable()) {
            previousButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 1");
        }
    }
    @FXML
    private void deHighlightPrevious() {
        if (!nextButton.isDisable()) {
            previousButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 1");
        }
    }
    @FXML
    private void highlightSubmit() {
        submitButton.setStyle("-fx-background-color: #FFFF8C; -fx-border-color: #969600; -fx-border-width: 1");
    }
    @FXML
    private void deHighlightSubmit() {
        submitButton.setStyle("-fx-background-color: #FFFF33; -fx-border-color: #A0A000; -fx-border-width: 1");
    }
    private void update() {
        pageCounter.setText(String.valueOf(page));
        first = (page - 1) * 6;
        System.out.println("First: " + first);
        last = first + 6;
        if (last > comments.length) {
            last = comments.length;
        }
        System.out.println("Last: " + last);
        int i;
        for (i = 0; i < last - first; i++) {
            if (!panes[i].isVisible()) {
                panes[i].setVisible(true);
            }
            usernames[i].setText(comments[first + i].getUserName());
            if (comments[first + i].isBuyer() && !buyers[i].isVisible()) {
                buyers[i].setVisible(true);
            }
            else if (!comments[first + i].isBuyer() && buyers[i].isVisible()) {
                buyers[i].setVisible(false);
            }
            commentFields[i].setText(comments[first + i].getText());
        }
        for (; i < 6; i++) {
            if (panes[i].isVisible()) {
                panes[i].setVisible(false);
            }
        }
    }
    @FXML
    private void next() {
        page++;
        update();
        if (previousButton.isDisable()) {
            previousButton.setDisable(false);
        }
        if (!(last < comments.length)) {
            nextButton.setDisable(true);
        }
    }
    @FXML
    private void previous() {
        page--;
        update();
        if (nextButton.isDisable()) {
            nextButton.setDisable(false);
        }
        if (page == 1) {
            previousButton.setDisable(true);
        }
    }
    @FXML
    private void submit() {
        new InsertComment(Global.getStage());
    }
    @FXML
    private void back() {
        try {
            new ProductPage(Global.getStage(), ProductPage.PRODUCT_ID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setGraphic(new ImageView(new Image("back_arrow.png")));
        comments = CommentDataBase.getProductComments(ProductPage.PRODUCT_ID);
        initiateArrays();
        first = 0;
        if (comments.length > 0) {
            noComment.setVisible(false);
        }
        if (comments.length < 6) {
            nextButton.setDisable(true);
            last = comments.length;
        }
        else {
            last = 5;
        }
        page = 1;
        update();
    }

    private void initiateArrays() {
        usernames[0] = username0;
        username0 = null;
        buyers[0] = buyer0;
        buyer0 = null;
        commentFields[0] = comment0;
        comment0 = null;
        panes[0] = pane0;
        pane0 = null;

        usernames[1] = username1;
        username1 = null;
        buyers[1] = buyer1;
        buyer1 = null;
        commentFields[1] = comment1;
        comment1 = null;
        panes[1] = pane1;
        pane1 = null;

        usernames[2] = username2;
        username2 = null;
        buyers[2] = buyer2;
        buyer2 = null;
        commentFields[2] = comment2;
        comment2 = null;
        panes[2] = pane2;
        pane2 = null;

        usernames[3] = username3;
        username3 = null;
        buyers[3] = buyer3;
        buyer3 = null;
        commentFields[3] = comment3;
        comment3 = null;
        panes[3] = pane3;
        pane3 = null;

        usernames[4] = username4;
        username4 = null;
        buyers[4] = buyer4;
        buyer4 = null;
        commentFields[4] = comment4;
        comment4 = null;
        panes[4] = pane4;
        pane4 = null;

        usernames[5] = username5;
        username5 = null;
        buyers[5] = buyer5;
        buyer5 = null;
        commentFields[5] = comment5;
        comment5 = null;
        panes[5] = pane5;
        pane5 = null;

    }
}
