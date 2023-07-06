package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import source.Global;
import source.database.PurchaseArchiveDataBase;
import source.products.ArchiveItem;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PurchaseArchiveController implements Initializable {
    private int page, first, last;
    private ArchiveItem[] archiveItems;
    @FXML
    private Button prevButton, nextButton;
    @FXML
    private AnchorPane pane0, pane1, pane2, pane3, pane4, pane5;
    private AnchorPane[] anchorPanes = new AnchorPane[6];
    @FXML
    private ImageView img0, img1, img2, img3, img4, img5;
    private ImageView[] imageViews = new ImageView[6];
    @FXML
    private Label title0, title1, title2, title3, title4, title5;
    private Label[] titles = new Label[6];
    @FXML
    private Label quantity0, quantity1, quantity2, quantity3, quantity4, quantity5;
    private Label[] quantities = new Label[6];
    @FXML
    private Label date0, date1, date2, date3, date4, date5;
    private Label[] dates = new Label[6];

    @FXML
    private void previous() {
        if (nextButton.isDisable()) {
            nextButton.setDisable(false);
        }
        page--;
        if (page == 1) {
            prevButton.setDisable(true);
        }
        first = (page - 1) * 6;
        last = first + 6;
        update();
    }
    @FXML
    private void next() {
        if (prevButton.isDisable()) {
            prevButton.setDisable(false);
        }
        page++;
        first = (page - 1) * 6;
        last = first + 6;
        if (last > archiveItems.length) {
            last = archiveItems.length;
            nextButton.setDisable(true);
        }
        update();
    }
    @FXML
    private void back() {
        try {
            new PersonMenu(Global.getStage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void update() {
        int i;
        for (i = 0; i < last - first; i++) {
            System.out.println(i+first);
            if (!anchorPanes[i].isVisible()) {
                anchorPanes[i].setVisible(true);
            }
            imageViews[i].setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Products/" + archiveItems[first+i].product.getImageAddress() + ".jpg"))));
            titles[i].setText(archiveItems[first+i].product.getName());
            quantities[i].setText("Quantity: " + archiveItems[first+i].quantity);
            StringBuilder stringBuilder = new StringBuilder();
            String time = archiveItems[i].getDate();
            stringBuilder.append(Global.getMonthName(Integer.parseInt(time.substring(4, 6)))).append(" ");
            int day = Integer.parseInt(time.substring(6, 8));
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
            stringBuilder.append(" ").append(time, 0, 4);
            dates[i].setText(stringBuilder.toString());
        }
        for (; i < 6 ; i++) {
            if (anchorPanes[i].isVisible()) {
                anchorPanes[i].setVisible(false);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateArrays();
        archiveItems = PurchaseArchiveDataBase.getAllArchives(Global.getUser_id());
        page = 1;
        first = 0;
        last = 6;
        prevButton.setDisable(true);
        if (archiveItems.length < last) {
            last = archiveItems.length;
            nextButton.setDisable(true);
        }
        update();
    }
    private void initiateArrays() {
        anchorPanes[0] = pane0;
        pane0 = null;
        imageViews[0] = img0;
        img0 = null;
        titles[0] = title0;
        title0 = null;
        quantities[0] = quantity0;
        quantity0 = null;
        dates[0] = date0;
        date0 = null;

        anchorPanes[1] = pane1;
        pane1 = null;
        imageViews[1] = img1;
        img1 = null;
        titles[1] = title1;
        title1 = null;
        quantities[1] = quantity1;
        quantity1 = null;
        dates[1] = date1;
        date1 = null;

        anchorPanes[2] = pane2;
        pane2 = null;
        imageViews[2] = img2;
        img2 = null;
        titles[2] = title2;
        title2 = null;
        quantities[2] = quantity2;
        quantity2 = null;
        dates[2] = date2;
        date2 = null;

        anchorPanes[3] = pane3;
        pane3 = null;
        imageViews[3] = img3;
        img3 = null;
        titles[3] = title3;
        title3 = null;
        quantities[3] = quantity3;
        quantity3 = null;
        dates[3] = date3;
        date3 = null;

        anchorPanes[4] = pane4;
        pane4 = null;
        imageViews[4] = img4;
        img4 = null;
        titles[4] = title4;
        title4 = null;
        quantities[4] = quantity4;
        quantity4 = null;
        dates[4] = date4;
        date4 = null;

        anchorPanes[5] = pane5;
        pane0 = null;
        imageViews[5] = img5;
        img5 = null;
        titles[5] = title5;
        title5 = null;
        quantities[5] = quantity5;
        quantity5 = null;
        dates[5] = date5;
        date5 = null;
    }

}
