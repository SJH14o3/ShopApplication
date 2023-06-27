package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import source.exceptions.PassedDeadLineException;
import source.products.Auction;
import source.products.AuctionDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static source.Global.getStage;

public class AuctionsController implements Initializable {
    @FXML
    private Button nextButton, previousButton, productsButton;
    @FXML
    private Label pageCounter;
    @FXML
    private ImageView image0, image1, image2, image3, image4, image5, image6, image7, image8;
    private ImageView[] images = new ImageView[9];
    @FXML
    private AnchorPane panel0, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
    private AnchorPane[] panels = new AnchorPane[9];
    @FXML
    private Label title0, title1, title2, title3, title4, title5, title6, title7, title8;
    private Label[] titles = new Label[9];
    @FXML
    private Label topBid0, topBid1, topBid2, topBid3, topBid4, topBid5, topBid6, topBid7, topBid8;
    private Label[] topBids = new Label[9];
    @FXML
    private Label timeLeft0, timeLeft1, timeLeft2, timeLeft3, timeLeft4, timeLeft5, timeLeft6, timeLeft7, timeLeft8;
    private Label[] timeLeft = new Label[9];

    private Auction[] auctions;

    private int count, first, last, page;
    @FXML
    private void switchToProducts() throws IOException {
        Stage stage = getStage();
        new Menu(stage);
    }
    private void updatePageCounter() {
        pageCounter.setText(String.valueOf(page));
    }
    @FXML
    private void next() {
        page++;
        updatePageCounter();
        if (previousButton.isDisable()) {
            previousButton.setDisable(false);
        }
        if (auctions.length <= page * 9) {
            nextButton.setDisable(true);
        }
        try {
            show();
        } catch (PassedDeadLineException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void previous() {
        page--;
        updatePageCounter();
        if (page == 1) {
            previousButton.setDisable(true);
        }
        if (nextButton.isDisable()) {
            nextButton.setDisable(false);
        }
        try {
            show();
        } catch (PassedDeadLineException e) {
            e.printStackTrace();
        }
    }
    private void toggleAnchors(int in) {
        int i;
        for (i = 0; i < in; i++) {
            if (!panels[i].isVisible()) {
                panels[i].setVisible(true);
            }
        }
        for (; i < 9; i++) {
            if (panels[i].isVisible()) {
                panels[i].setVisible(false);
            }
        }
    }
    private void show() throws PassedDeadLineException {
        first = (page - 1) * 9;
        last = first + 9;
        if (last > auctions.length) {
            last = auctions.length;
            nextButton.setDisable(true);
        }
        toggleAnchors(last - first);
        for (int i = 0; i < last - first; i++) {
            images[i].setImage(new Image(auctions[first + i].getImageAddress() + ".jpg"));
            titles[i].setText(auctions[first + i].getTitle());
            topBids[i].setText("Highest Bid: " + auctions[first + i].getHighestBid() + "$");
            timeLeft[i].setText("Time Left:" + auctions[first + i].calculateDeadLine());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateArrays();
        auctions = AuctionDataBase.getAuctions();
        count = auctions.length;
        first = page = 1;
        pageCounter.setText(String.valueOf(page));
        productsButton.setGraphic(new ImageView(new Image("productsButton.png")));
        if (count < 9) {
            last = count;
            nextButton.setDisable(true);
        }
        else {
            last = 9;
        }
        try {
            show();
        } catch (PassedDeadLineException e) {
            e.printStackTrace();
        }
    }
    private void initiateArrays() {
        images[0] = image0;
        image0 = null;
        panels[0] = panel0;
        panel0 = null;
        titles[0] = title0;
        title0 = null;
        topBids[0] = topBid0;
        topBid0 = null;
        timeLeft[0] = timeLeft0;
        timeLeft0 = null;

        images[1] = image1;
        image1 = null;
        panels[1] = panel1;
        panel1 = null;
        titles[1] = title1;
        title1 = null;
        topBids[1] = topBid1;
        topBid1 = null;
        timeLeft[1] = timeLeft1;
        timeLeft1 = null;

        images[2] = image2;
        image2 = null;
        panels[2] = panel2;
        panel2 = null;
        titles[2] = title2;
        title2 = null;
        topBids[2] = topBid2;
        topBid2 = null;
        timeLeft[2] = timeLeft2;
        timeLeft2 = null;

        images[3] = image3;
        image3 = null;
        panels[3] = panel3;
        panel3 = null;
        titles[3] = title3;
        title3 = null;
        topBids[3] = topBid3;
        topBid3 = null;
        timeLeft[3] = timeLeft3;
        timeLeft3 = null;

        images[4] = image4;
        image4 = null;
        panels[4] = panel4;
        panel4 = null;
        titles[4] = title4;
        title4 = null;
        topBids[4] = topBid4;
        topBid4 = null;
        timeLeft[4] = timeLeft4;
        timeLeft4 = null;

        images[5] = image5;
        image5 = null;
        panels[5] = panel5;
        panel5 = null;
        titles[5] = title5;
        title5 = null;
        topBids[5] = topBid5;
        topBid5 = null;
        timeLeft[5] = timeLeft5;
        timeLeft5 = null;

        images[6] = image6;
        image6 = null;
        panels[6] = panel6;
        panel6 = null;
        titles[6] = title6;
        title6 = null;
        topBids[6] = topBid6;
        topBid6 = null;
        timeLeft[6] = timeLeft6;
        timeLeft6 = null;

        images[7] = image7;
        image7 = null;
        panels[7] = panel7;
        panel7 = null;
        titles[7] = title7;
        title7 = null;
        topBids[7] = topBid7;
        topBid7 = null;
        timeLeft[7] = timeLeft7;
        timeLeft7 = null;

        images[8] = image8;
        image8 = null;
        panels[8] = panel8;
        panel8 = null;
        titles[8] = title8;
        title8 = null;
        topBids[8] = topBid8;
        topBid8 = null;
        timeLeft[8] = timeLeft8;
        timeLeft8 = null;
    }
}
