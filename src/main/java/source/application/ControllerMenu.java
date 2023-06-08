package source.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import source.products.Product;
import static source.products.productDataBase.*;
import static source.Global.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {
    Product[] products;
    @FXML
    Label name0, name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12, name13, name14, name15, name16, name17, name18, name19, name20, name21, name22, name23, name24, name25, name26, name27, name28, name29, name30, name31, name32, name33, name34, name35, name36, name37, name38, name39, name40, name41, name42, name43, name44, name45, name46, name47, name48, name49;
    private Label[] names;
    @FXML
    Label score0, score1, score2, score3, score4, score5, score6, score7, score8, score9, score10, score11, score12, score13, score14, score15, score16, score17, score18, score19, score20, score21, score22, score23, score24, score25, score26, score27, score28, score29, score30, score31, score32, score33, score34, score35, score36, score37, score38, score39, score40, score41, score42, score43, score44, score45, score46, score47, score48, score49;
    private Label[] scores;
    @FXML
    Label price0, price1, price2, price3, price4, price5, price6, price7, price8, price9, price10, price11, price12, price13, price14, price15, price16, price17, price18, price19, price20, price21, price22, price23, price24, price25, price26, price27, price28, price29, price30, price31, price32, price33, price34, price35, price36, price37, price38, price39, price40, price41, price42, price43, price44, price45, price46, price47, price48, price49;
    private Label[] prices;
    @FXML
    ImageView img0, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31, img32, img33, img34, img35, img36, img37, img38, img39, img40, img41, img42, img43, img44, img45, img46, img47, img48, img49;
    private ImageView[] imageViews;
    @FXML
    AnchorPane anchor0, anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9, anchor10, anchor11, anchor12, anchor13, anchor14, anchor15, anchor16, anchor17, anchor18, anchor19, anchor20, anchor21, anchor22, anchor23, anchor24, anchor25, anchor26, anchor27, anchor28, anchor29, anchor30, anchor31, anchor32, anchor33, anchor34, anchor35, anchor36, anchor37, anchor38, anchor39, anchor40, anchor41, anchor42, anchor43, anchor44, anchor45, anchor46, anchor47, anchor48, anchor49;
    private AnchorPane[] anchorPanes;
    @FXML
    ChoiceBox<String> sortBox;
    @FXML
    AnchorPane mainAnchorPane, productScroll;
    private final String[] sortOptions = {"Oldest", "Newest", "Cheapest", "Most Expensive", "Rating"};
    private void hideAnchors(int in) {
        int i = 50;
        anchor49.setVisible(false);
        i--;
        if (i > in) {
            anchor48.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor47.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor46.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor45.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor44.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor43.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor42.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor41.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor40.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor39.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor38.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor37.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor36.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor35.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor34.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor33.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor32.setVisible(false);
            i--;
        }

        else {
            return;
        }
        if (i > in) {
            anchor31.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor30.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor29.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor28.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor27.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor26.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor25.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor24.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor23.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor22.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor21.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor20.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor19.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor18.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor17.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor16.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor15.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor14.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor13.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor12.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor11.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor10.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor9.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor8.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor7.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor6.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor5.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor4.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            anchor3.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            System.out.println("i - in: " + i + " " + in);
            anchor2.setVisible(false);
            i--;
        }
        else {
            return;
        }
        if (i > in) {
            System.out.println("i - in: " + i + " " + in);
            anchor1.setVisible(false);
        }
    }
    private void setupArrays(int page) {
        int first = (page-1) * 50;
        int last = first + 50;
        if (products.length < last) {
            last = products.length;
        }
        names = new Label[last-first];
        scores = new Label[last-first];
        prices = new Label[last-first];
        imageViews = new ImageView[last-first];
        anchorPanes = new AnchorPane[last-first];
        //System.out.println(last-first);
        int i = first;

        names[0] = name0;
        scores[0] = score0;
        prices[0] = price0;
        imageViews[0] = img0;
        anchorPanes[0] = anchor0;
        i++;
        if (i < last) {
            names[1] = name1;
            scores[1] = score1;
            prices[1] = price1;
            imageViews[1] = img1;
            anchorPanes[1] = anchor1;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[2] = name2;
            scores[2] = score2;
            prices[2] = price2;
            imageViews[2] = img2;
            anchorPanes[2] = anchor2;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[3] = name3;
            scores[3] = score3;
            prices[3] = price3;
            imageViews[3] = img3;
            anchorPanes[3] = anchor3;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[4] = name4;
            scores[4] = score4;
            prices[4] = price4;
            imageViews[4] = img4;
            anchorPanes[4] = anchor4;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[5] = name5;
            scores[5] = score5;
            prices[5] = price5;
            imageViews[5] = img5;
            anchorPanes[5] = anchor5;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[6] = name6;
            scores[6] = score6;
            prices[6] = price6;
            imageViews[6] = img6;
            anchorPanes[6] = anchor6;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[7] = name7;
            scores[7] = score7;
            prices[7] = price7;
            imageViews[7] = img7;
            anchorPanes[7] = anchor7;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[8] = name8;
            scores[8] = score8;
            prices[8] = price8;
            imageViews[8] = img8;
            anchorPanes[8] = anchor8;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[9] = name9;
            scores[9] = score9;
            prices[9] = price9;
            imageViews[9] = img9;
            anchorPanes[9] = anchor9;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[10] = name10;
            scores[10] = score10;
            prices[10] = price10;
            imageViews[10] = img10;
            anchorPanes[10] = anchor10;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[11] = name11;
            scores[11] = score11;
            prices[11] = price11;
            imageViews[11] = img11;
            anchorPanes[11] = anchor11;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[12] = name12;
            scores[12] = score12;
            prices[12] = price12;
            imageViews[12] = img12;
            anchorPanes[12] = anchor12;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[13] = name13;
            scores[13] = score13;
            prices[13] = price13;
            imageViews[13] = img13;
            anchorPanes[13] = anchor13;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[14] = name14;
            scores[14] = score14;
            prices[14] = price14;
            imageViews[14] = img14;
            anchorPanes[14] = anchor14;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[15] = name15;
            scores[15] = score15;
            prices[15] = price15;
            imageViews[15] = img15;
            anchorPanes[15] = anchor15;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[16] = name16;
            scores[16] = score16;
            prices[16] = price16;
            imageViews[16] = img16;
            anchorPanes[16] = anchor16;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[17] = name17;
            scores[17] = score17;
            prices[17] = price17;
            imageViews[17] = img17;
            anchorPanes[17] = anchor17;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[18] = name18;
            scores[18] = score18;
            prices[18] = price18;
            imageViews[18] = img18;
            anchorPanes[18] = anchor18;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[19] = name19;
            scores[19] = score19;
            prices[19] = price19;
            imageViews[19] = img19;
            anchorPanes[19] = anchor19;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[20] = name20;
            scores[20] = score20;
            prices[20] = price20;
            imageViews[20] = img20;
            anchorPanes[20] = anchor20;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[21] = name21;
            scores[21] = score21;
            prices[21] = price21;
            imageViews[21] = img21;
            anchorPanes[21] = anchor21;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[22] = name22;
            scores[22] = score22;
            prices[22] = price22;
            imageViews[22] = img22;
            anchorPanes[22] = anchor22;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[23] = name23;
            scores[23] = score23;
            prices[23] = price23;
            imageViews[23] = img23;
            anchorPanes[23] = anchor23;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[24] = name24;
            scores[24] = score24;
            prices[24] = price24;
            imageViews[24] = img24;
            anchorPanes[24] = anchor24;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[25] = name25;
            scores[25] = score25;
            prices[25] = price25;
            imageViews[25] = img25;
            anchorPanes[25] = anchor25;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[26] = name26;
            scores[26] = score26;
            prices[26] = price26;
            imageViews[26] = img26;
            anchorPanes[26] = anchor26;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[27] = name27;
            scores[27] = score27;
            prices[27] = price27;
            imageViews[27] = img27;
            anchorPanes[27] = anchor27;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[28] = name28;
            scores[28] = score28;
            prices[28] = price28;
            imageViews[28] = img28;
            anchorPanes[28] = anchor28;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[29] = name29;
            scores[29] = score29;
            prices[29] = price29;
            imageViews[29] = img29;
            anchorPanes[29] = anchor29;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[30] = name30;
            scores[30] = score30;
            prices[30] = price30;
            imageViews[30] = img30;
            anchorPanes[30] = anchor30;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[31] = name31;
            scores[31] = score31;
            prices[31] = price31;
            imageViews[31] = img31;
            anchorPanes[31] = anchor31;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[32] = name32;
            scores[32] = score32;
            prices[32] = price32;
            imageViews[32] = img32;
            anchorPanes[32] = anchor32;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[33] = name33;
            scores[33] = score33;
            prices[33] = price33;
            imageViews[33] = img33;
            anchorPanes[33] = anchor33;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[34] = name34;
            scores[34] = score34;
            prices[34] = price34;
            imageViews[34] = img34;
            anchorPanes[34] = anchor34;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[35] = name35;
            scores[35] = score35;
            prices[35] = price35;
            imageViews[35] = img35;
            anchorPanes[35] = anchor35;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[36] = name36;
            scores[36] = score36;
            prices[36] = price36;
            imageViews[36] = img36;
            anchorPanes[36] = anchor36;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[37] = name37;
            scores[37] = score37;
            prices[37] = price37;
            imageViews[37] = img37;
            anchorPanes[37] = anchor37;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[38] = name38;
            scores[38] = score38;
            prices[38] = price38;
            imageViews[38] = img38;
            anchorPanes[38] = anchor38;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[39] = name39;
            scores[39] = score39;
            prices[39] = price39;
            imageViews[39] = img39;
            anchorPanes[39] = anchor39;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[40] = name40;
            scores[40] = score40;
            prices[40] = price40;
            imageViews[40] = img40;
            anchorPanes[40] = anchor40;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[41] = name41;
            scores[41] = score41;
            prices[41] = price41;
            imageViews[41] = img41;
            anchorPanes[41] = anchor41;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[42] = name42;
            scores[42] = score42;
            prices[42] = price42;
            imageViews[42] = img42;
            anchorPanes[42] = anchor42;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[43] = name43;
            scores[43] = score43;
            prices[43] = price43;
            imageViews[43] = img43;
            anchorPanes[43] = anchor43;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[44] = name44;
            scores[44] = score44;
            prices[44] = price44;
            imageViews[44] = img44;
            anchorPanes[44] = anchor44;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[45] = name45;
            scores[45] = score45;
            prices[45] = price45;
            imageViews[45] = img45;
            anchorPanes[45] = anchor45;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[46] = name46;
            scores[46] = score46;
            prices[46] = price46;
            imageViews[46] = img46;
            anchorPanes[46] = anchor46;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[47] = name47;
            scores[47] = score47;
            prices[47] = price47;
            imageViews[47] = img47;
            anchorPanes[47] = anchor47;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[48] = name48;
            scores[48] = score48;
            prices[48] = price48;
            imageViews[48] = img48;
            anchorPanes[48] = anchor48;
            i++;
        }
        else {
            hideAnchors(i - first);
            return;
        }
        if (i < last) {
            names[49] = name49;
            scores[49] = score49;
            prices[49] = price49;
            imageViews[49] = img49;
            anchorPanes[49] = anchor49;
        }
        else {
            hideAnchors(i - first);
        }
    }
    private void showProducts() {
        for (int i = 0; i < products.length; i++) {
            names[i].setText(products[i].getName());
            prices[i].setText(products[i].getPrice() + "$");
            scores[i].setText(String.valueOf(products[i].getScore()));
            imageViews[i].setImage(new Image(products[i].getImageAddress()));
        }
    }
    private void setProductScrollHeight() {
        int countToShow = products.length % 50;
        if (countToShow < 11) {
            productScroll.setPrefHeight(608);
        }
        else {
            productScroll.setPrefHeight((((countToShow - 1) / 5) + 1) * 253 + 7);
        }
    }
    private void sort(ActionEvent actionEvent) {
        String extra = "";
        switch (sortBox.getValue()) {
            case "Newest" -> extra = "ORDER BY product_id DESC";
            case "Cheapest" -> extra = "ORDER BY price";
            case "Most Expensive" -> extra = "ORDER BY price DESC";
            case "Rating" -> extra = "ORDER BY score DESC";
            default -> {
            }
        }
        products = getAllProductsMainInfo(extra);
        setupArrays(1);
        setProductScrollHeight();
        showProducts();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainAnchorPane.setBackground(new Background(new BackgroundFill(hexToColor(COLOR1, 0.75), null, Insets.EMPTY)));
        productScroll.setBackground(new Background(new BackgroundFill(hexToColor(COLOR1, 1.0), null, Insets.EMPTY)));

        sortBox.getItems().addAll(sortOptions);
        sortBox.setValue("Oldest");
        sortBox.setOnAction(this::sort);
        //TODO implement the part when there are more than 50 products
        //this commented codes are used to
        /*int j = 48;
        products = new Product[j];
        for (int i = 10; i < j; i++) {
            products[i] = new Product(null, null, 0, 0, null, 0, null);
        }*/
        sort(null);
    }
}
