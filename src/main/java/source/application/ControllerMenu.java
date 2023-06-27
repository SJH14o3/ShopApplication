package source.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import source.products.Product;

import static source.products.ProductDataBase.*;
import static source.Global.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class ControllerMenu implements Initializable {
    Product[] products;
    private int first;
    private int last;
    private int page = 1;

    private String brandsStr = "", price = "", categoryStr = "";
    @FXML
    private Label name0, name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12, name13, name14, name15, name16, name17, name18, name19, name20, name21, name22, name23, name24, name25, name26, name27, name28, name29, name30, name31, name32, name33, name34, name35, name36, name37, name38, name39, name40, name41, name42, name43, name44, name45, name46, name47, name48, name49;
    private final Label[] names = new Label[50];
    @FXML
    private Label score0, score1, score2, score3, score4, score5, score6, score7, score8, score9, score10, score11, score12, score13, score14, score15, score16, score17, score18, score19, score20, score21, score22, score23, score24, score25, score26, score27, score28, score29, score30, score31, score32, score33, score34, score35, score36, score37, score38, score39, score40, score41, score42, score43, score44, score45, score46, score47, score48, score49;
    private final Label[] scores = new Label[50];
    @FXML
    private Label price0, price1, price2, price3, price4, price5, price6, price7, price8, price9, price10, price11, price12, price13, price14, price15, price16, price17, price18, price19, price20, price21, price22, price23, price24, price25, price26, price27, price28, price29, price30, price31, price32, price33, price34, price35, price36, price37, price38, price39, price40, price41, price42, price43, price44, price45, price46, price47, price48, price49;
    private final Label[] prices = new Label[50];
    @FXML
    private ImageView img0, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24, img25, img26, img27, img28, img29, img30, img31, img32, img33, img34, img35, img36, img37, img38, img39, img40, img41, img42, img43, img44, img45, img46, img47, img48, img49;
    private final ImageView[] imageViews = new ImageView[50];
    @FXML
    private AnchorPane anchor0, anchor1, anchor2, anchor3, anchor4, anchor5, anchor6, anchor7, anchor8, anchor9, anchor10, anchor11, anchor12, anchor13, anchor14, anchor15, anchor16, anchor17, anchor18, anchor19, anchor20, anchor21, anchor22, anchor23, anchor24, anchor25, anchor26, anchor27, anchor28, anchor29, anchor30, anchor31, anchor32, anchor33, anchor34, anchor35, anchor36, anchor37, anchor38, anchor39, anchor40, anchor41, anchor42, anchor43, anchor44, anchor45, anchor46, anchor47, anchor48, anchor49;
    private final AnchorPane[] anchorPanes = new AnchorPane[50];
    @FXML
    CheckBox brand0, brand1, brand2, brand3, brand4, brand5, brand6, brand7, brand8, brand9, brand10, brand11, brand12, brand13, brand14, brand15, brand16, brand17, brand18, brand19, brand20, brand21, brand22, brand23, brand24, brand25, brand26, brand27, brand28, brand29, brand30, brand31, brand32, brand33, brand34, brand35, brand36, brand37, brand38, brand39, brand40, brand41, brand42, brand43, brand44, brand45, brand46, brand47, brand48, brand49;
    private final CheckBox[] brands = new CheckBox[50];
    @FXML
    private ChoiceBox<String> sortBox, categoryBox;
    @FXML
    private CheckBox all;
    @FXML
    private AnchorPane mainAnchorPane, productScroll, filterScrollPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label pageCounter, minPrice, maxPrice, brandLabel, brandLabel1;
    @FXML
    private Button nextButton, previousButton, auctionButton;
    @FXML
    private Slider minSlider, maxSlider;
    @FXML
    private ImageView noProduct;

    private final String[] sortOptions = {"Newest", "Oldest", "Cheapest", "Most Expensive", "Rating"};
    private final String[] categories = {"All", "Vegetables", "Fruits", "Dried Fruits", "Proteins", "Sweets", "Pantry", "Dairy", "Beverages", "Snacks", "Breakfast"};

    private void hideAnchors(int in) {
        for (int j = 50; j > in; j--) {
            if (anchorPanes[j-1].isVisible()) {
                anchorPanes[j-1].setVisible(false);
            }
        }
    }
    private void setupArrays(int page) {
        first = (page - 1) * 50;
        last = first + 50;
        if (products.length < last) {
            last = products.length;
        }
        //System.out.println(last-first);
        for (int i = first; true; i++) {
            if (i < last) {
                if (!anchorPanes[i - first].isVisible()) {
                    anchorPanes[i - first].setVisible(true);
                }
            }
            else {
                //System.out.println(i);
                hideAnchors(i - first);
                break;
            }
        }
    }
    public void allSelected() {
        System.out.println("all");
        for (CheckBox brand : brands) {
            if (brand.isSelected()) {
                brand.setSelected(false);
            }
        }
        if (!all.isSelected()) {
            all.setSelected(true);
        }
    }
    public void brandSelected(){
        boolean atLeastOneSelected = false;
        for (int i = 0; !atLeastOneSelected && i < brands.length; i++) {
            if (brands[i].isSelected()) {
                atLeastOneSelected = true;
            }
        }
        if (all.isSelected()) {
            all.setSelected(false);
        }
        else if (!atLeastOneSelected) {
            all.setSelected(true);
        }
    }

    public void filter(){
        if (!(minPrice.getText().equals("0.00$") && maxPrice.getText().equals("100.00$"))) {
            if (minSlider.getValue() > maxSlider.getValue()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong price input");
                alert.setHeaderText("Minimum price cannot be more than maximum price");
                alert.setContentText("Make sure minimum price is lower than maximum price");
                alert.showAndWait();
                return;
            }
            price = "(price BETWEEN " + String.format("%.2f", minSlider.getValue()) + " AND " + String.format("%.2f", maxSlider.getValue()) + ") ";
        }
        else if (!price.isEmpty()) {
            price = "";
        }
        if (!all.isSelected()) {
            boolean multiple = false;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 50; i++) {
                if (brands[i].isSelected()) {
                    if (multiple) {
                        stringBuilder.append(" OR brand = \"").append(brands[i].getText()).append("\"");
                    }
                    else {
                        stringBuilder.append("brand = \"").append(brands[i].getText()).append("\"");
                        multiple = true;
                    }
                }
            }
            brandsStr = stringBuilder.toString();
            System.out.println(brandsStr);
        }
        else brandsStr = "";
        sort(null);
    }
    private void showProducts() {
        for (int i = first; i < last; i++) {
            names[i-first].setText(products[i].getName());
            prices[i-first].setText(products[i].getPrice() + "$");
            scores[i-first].setText(String.valueOf(products[i].getScore()));
            //System.out.println(products[i].getImageAddress() + ".jpg");
            imageViews[i-first].setImage(new Image(products[i].getImageAddress() + ".jpg"));
        }
    }
    private void setProductScrollHeight() {
        int countToShow = (last - first) % 50;
        if (last != first && countToShow == 0) {
            countToShow = 50;
        }
        if (countToShow < 11) {
            productScroll.setPrefHeight(568);
        }
        else {
            productScroll.setPrefHeight((((countToShow - 1) / 5) + 1) * 253 + 7);
        }
    }
    private void updatePageCounter() {
        pageCounter.setText(String.valueOf(page));
    }
    private void reset() {
        if (!previousButton.isDisable()) {
            previousButton.setDisable(true);
        }
        //System.out.println(products.length);
        if (products.length < 50 && !nextButton.isDisable()) {
            nextButton.setDisable(true);
        }
        else if (products.length > 50 && nextButton.isDisable()) {
            nextButton.setDisable(false);
        }
        page = 1;
    }
    private void category(ActionEvent actionEvent) {
        if (!Objects.equals(categoryBox.getValue(), "All")) {
            categoryStr = String.valueOf(Product.stringToType(categoryBox.getValue()));
        }
        else {
            categoryStr = "";
        }
        price = "";
        brandsStr = "";
        sortBox.setValue("Newest");
        minSlider.setValue(0);
        maxSlider.setValue(100);
        checkBoxesSetup();
        sort(actionEvent);
    }
    private void sort(ActionEvent actionEvent) {
        StringBuilder extra = new StringBuilder();
        if (!categoryStr.isEmpty() || !price.isEmpty() || !brandsStr.isEmpty()) {
            extra.append("WHERE ");
            if (!brandsStr.isEmpty()) {
                    extra.append("(").append(brandsStr).append(")");
                if (!price.isEmpty() || !categoryStr.isEmpty()) {
                    extra.append(" AND ");
                }
            }
            if (!price.isEmpty()) {
                extra.append(price);
                if (!categoryStr.isEmpty()) {
                    extra.append("AND ");
                }
            }
            if (!categoryStr.isEmpty()) {
                extra.append("product_type = ").append(categoryStr).append(" ");
            }
        }
        switch (sortBox.getValue()) {
            case "Newest" -> extra.append("ORDER BY product_id DESC");
            case "Cheapest" -> extra.append("ORDER BY price");
            case "Most Expensive" -> extra.append("ORDER BY price DESC");
            case "Rating" -> extra.append("ORDER BY score DESC");
            default -> {
            }
        }
        //System.out.println(extra);
        products = getSelectedProductsMainInfo(extra.toString());
        assert products != null;
        System.out.println(products.length);
        if (products.length == 0) {
            if (!noProduct.isVisible()) {
                noProduct.setVisible(true);
            }
        }
        else {
            if (noProduct.isVisible()) {
                noProduct.setVisible(false);
            }
        }
        //System.out.println(products.length);
        reset();
        setupPage();
    }
    private void setFilterScrollHeight(int count) {
        System.out.println(count);
        if (count < 31) {
            filterScrollPane.setPrefHeight(568);
        }
        else {
            filterScrollPane.setPrefHeight(((count / 2) + 1) * 24 + 138);
        }
    }
    private void checkBoxesSetup() {
        if (categoryStr.isEmpty()) {
            setFilterScrollHeight(0);
            brandLabel.setVisible(false);
            brandLabel1.setVisible(true);
            if (all.isVisible()) {
                all.setVisible(false);
            }
            for (CheckBox brand : brands) {
                if (brand.isVisible()) {
                    brand.setVisible(false);
                }
            }
        }
        else {
            brandLabel.setVisible(true);
            brandLabel1.setVisible(false);
            if (!all.isVisible()) {
                all.setVisible(true);
            }
            String[] brandNames = getUniqueBrands("WHERE product_type = " + categoryStr);
            assert brandNames != null;
            if (brandNames.length > 50) {
                System.out.println("brands are more than 50!");
            }
            else {
                int i = 0;
                for (; i < brandNames.length; i++) {
                    if (!brands[i].isVisible()) {
                        brands[i].setVisible(true);
                    }
                    brands[i].setText(brandNames[i]);
                }
                for (; i < brands.length; i++) {
                    brands[i].setText("");
                    if (brands[i].isVisible()) {
                        brands[i].setVisible(false);
                    }
                }
            }
            setFilterScrollHeight(brandNames.length);
            all.setSelected(true);
            allSelected();
        }
    }
    private void setupPage() {
        updatePageCounter();
        setupArrays(page);
        setProductScrollHeight();
        showProducts();
        scrollPane.setVvalue(0);
    }
    public void next() {
        page++;
        if (products.length < page * 50) {
            nextButton.setDisable(true);
        }
        if (previousButton.isDisable() && products.length > 50) {
            previousButton.setDisable(false);
        }
        setupPage();
    }
    public void previous() {
        page--;
        if (page == 1) {
            previousButton.setDisable(true);
        }
        if (nextButton.isDisable() && products.length > 50) {
            nextButton.setDisable(false);
        }
        setupPage();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainAnchorPane.setBackground(new Background(new BackgroundFill(hexToColor(COLOR1, 0.75), null, Insets.EMPTY)));
        productScroll.setBackground(new Background(new BackgroundFill(hexToColor(COLOR1, 1.0), null, Insets.EMPTY)));

        all.setSelected(true);

        sortBox.getItems().addAll(sortOptions);
        sortBox.setValue("Newest");
        sortBox.setOnAction(this::sort);

        categoryBox.getItems().addAll(categories);
        categoryBox.setValue("All");
        categoryBox.setOnAction(this::category);

        ImageView auctionIcon = new ImageView(new Image("auctions.png"));
        auctionButton.setGraphic(auctionIcon);

        minSlider.valueProperty().addListener((observableValue, number, t1) -> minPrice.setText(String.format(("%.2f"), minSlider.getValue()) + "$"));
        maxSlider.valueProperty().addListener((observableValue, number, t1) -> maxPrice.setText(String.format(("%.2f"), maxSlider.getValue()) + "$"));
        initiateArrays();
        checkBoxesSetup();
        sort(null);
        //getUniqueBrands("");
    }
    @FXML
    private void switchToAuctions() throws IOException {
        Stage stage = getStage();
        new AuctionsMenu(stage);
    }
    private void productSelected(int in) throws IOException {
        Stage stage = getStage();
        new ProductPage(stage, in);
    }
    /*since I did not find a way to inject nodes in an array I had to give all of needed node a unique fx:id and inject all of them individually.
    * but still I needed them to be in an array, so I call this method once and when program switch to menu scene, and it will manually
    * make a reference to that node. after that the injected node will be null since they are not needed anymore.*/
    private void initiateArrays(){
        names[0] = name0;
        name0 = null;
        scores[0] = score0;
        score0 = null;
        prices[0] = price0;
        price0 = null;
        imageViews[0] = img0;
        img0 = null;
        anchorPanes[0] = anchor0;
        anchor0 = null;

        names[1] = name1;
        name1 = null;
        scores[1] = score1;
        score1 = null;
        prices[1] = price1;
        price1 = null;
        imageViews[1] = img1;
        img1 = null;
        anchorPanes[1] = anchor1;
        anchor1 = null;

        names[2] = name2;
        name2 = null;
        scores[2] = score2;
        score2 = null;
        prices[2] = price2;
        price2 = null;
        imageViews[2] = img2;
        img2 = null;
        anchorPanes[2] = anchor2;
        anchor2 = null;

        names[3] = name3;
        name3 = null;
        scores[3] = score3;
        score3 = null;
        prices[3] = price3;
        price3 = null;
        imageViews[3] = img3;
        img3 = null;
        anchorPanes[3] = anchor3;
        anchor3 = null;

        names[4] = name4;
        name4 = null;
        scores[4] = score4;
        score4 = null;
        prices[4] = price4;
        price4 = null;
        imageViews[4] = img4;
        img4 = null;
        anchorPanes[4] = anchor4;
        anchor4 = null;

        names[5] = name5;
        name5 = null;
        scores[5] = score5;
        score5 = null;
        prices[5] = price5;
        price5 = null;
        imageViews[5] = img5;
        img5 = null;
        anchorPanes[5] = anchor5;
        anchor5 = null;

        names[6] = name6;
        name6 = null;
        scores[6] = score6;
        score6 = null;
        prices[6] = price6;
        price6 = null;
        imageViews[6] = img6;
        img6 = null;
        anchorPanes[6] = anchor6;
        anchor6 = null;

        names[7] = name7;
        name7 = null;
        scores[7] = score7;
        score7 = null;
        prices[7] = price7;
        price7 = null;
        imageViews[7] = img7;
        img7 = null;
        anchorPanes[7] = anchor7;
        anchor7 = null;

        names[8] = name8;
        name8 = null;
        scores[8] = score8;
        score8 = null;
        prices[8] = price8;
        price8 = null;
        imageViews[8] = img8;
        img8 = null;
        anchorPanes[8] = anchor8;
        anchor8 = null;

        names[9] = name9;
        name9 = null;
        scores[9] = score9;
        score9 = null;
        prices[9] = price9;
        price9 = null;
        imageViews[9] = img9;
        img9 = null;
        anchorPanes[9] = anchor9;
        anchor9 = null;

        names[10] = name10;
        name10 = null;
        scores[10] = score10;
        score10 = null;
        prices[10] = price10;
        price10 = null;
        imageViews[10] = img10;
        img10 = null;
        anchorPanes[10] = anchor10;
        anchor10 = null;

        names[11] = name11;
        name11 = null;
        scores[11] = score11;
        score11 = null;
        prices[11] = price11;
        price11 = null;
        imageViews[11] = img11;
        img11 = null;
        anchorPanes[11] = anchor11;
        anchor11 = null;

        names[12] = name12;
        name12 = null;
        scores[12] = score12;
        score12 = null;
        prices[12] = price12;
        price12 = null;
        imageViews[12] = img12;
        img12 = null;
        anchorPanes[12] = anchor12;
        anchor12 = null;

        names[13] = name13;
        name13 = null;
        scores[13] = score13;
        score13 = null;
        prices[13] = price13;
        price13 = null;
        imageViews[13] = img13;
        img13 = null;
        anchorPanes[13] = anchor13;
        anchor13 = null;

        names[14] = name14;
        name14 = null;
        scores[14] = score14;
        score14 = null;
        prices[14] = price14;
        price14 = null;
        imageViews[14] = img14;
        img14 = null;
        anchorPanes[14] = anchor14;
        anchor14 = null;

        names[15] = name15;
        name15 = null;
        scores[15] = score15;
        score15 = null;
        prices[15] = price15;
        price15 = null;
        imageViews[15] = img15;
        img15 = null;
        anchorPanes[15] = anchor15;
        anchor15 = null;

        names[16] = name16;
        name16 = null;
        scores[16] = score16;
        score16 = null;
        prices[16] = price16;
        price16 = null;
        imageViews[16] = img16;
        img16 = null;
        anchorPanes[16] = anchor16;
        anchor16 = null;

        names[17] = name17;
        name17 = null;
        scores[17] = score17;
        score17 = null;
        prices[17] = price17;
        price17 = null;
        imageViews[17] = img17;
        img17 = null;
        anchorPanes[17] = anchor17;
        anchor17 = null;

        names[18] = name18;
        name18 = null;
        scores[18] = score18;
        score18 = null;
        prices[18] = price18;
        price18 = null;
        imageViews[18] = img18;
        img18 = null;
        anchorPanes[18] = anchor18;
        anchor18 = null;

        names[19] = name19;
        name19 = null;
        scores[19] = score19;
        score19 = null;
        prices[19] = price19;
        price19 = null;
        imageViews[19] = img19;
        img19 = null;
        anchorPanes[19] = anchor19;
        anchor19 = null;

        names[20] = name20;
        name20 = null;
        scores[20] = score20;
        score20 = null;
        prices[20] = price20;
        price20 = null;
        imageViews[20] = img20;
        img20 = null;
        anchorPanes[20] = anchor20;
        anchor20 = null;

        names[21] = name21;
        name21 = null;
        scores[21] = score21;
        score21 = null;
        prices[21] = price21;
        price21 = null;
        imageViews[21] = img21;
        img21 = null;
        anchorPanes[21] = anchor21;
        anchor21 = null;

        names[22] = name22;
        name22 = null;
        scores[22] = score22;
        score22 = null;
        prices[22] = price22;
        price22 = null;
        imageViews[22] = img22;
        img22 = null;
        anchorPanes[22] = anchor22;
        anchor22 = null;

        names[23] = name23;
        name23 = null;
        scores[23] = score23;
        score23 = null;
        prices[23] = price23;
        price23 = null;
        imageViews[23] = img23;
        img23 = null;
        anchorPanes[23] = anchor23;
        anchor23 = null;

        names[24] = name24;
        name24 = null;
        scores[24] = score24;
        score24 = null;
        prices[24] = price24;
        price24 = null;
        imageViews[24] = img24;
        img24 = null;
        anchorPanes[24] = anchor24;
        anchor24 = null;

        names[25] = name25;
        name25 = null;
        scores[25] = score25;
        score25 = null;
        prices[25] = price25;
        price25 = null;
        imageViews[25] = img25;
        img25 = null;
        anchorPanes[25] = anchor25;
        anchor25 = null;

        names[26] = name26;
        name26 = null;
        scores[26] = score26;
        score26 = null;
        prices[26] = price26;
        price26 = null;
        imageViews[26] = img26;
        img26 = null;
        anchorPanes[26] = anchor26;
        anchor26 = null;

        names[27] = name27;
        name27 = null;
        scores[27] = score27;
        score27 = null;
        prices[27] = price27;
        price27 = null;
        imageViews[27] = img27;
        img27 = null;
        anchorPanes[27] = anchor27;
        anchor27 = null;

        names[28] = name28;
        name28 = null;
        scores[28] = score28;
        score28 = null;
        prices[28] = price28;
        price28 = null;
        imageViews[28] = img28;
        img28 = null;
        anchorPanes[28] = anchor28;
        anchor28 = null;

        names[29] = name29;
        name29 = null;
        scores[29] = score29;
        score29 = null;
        prices[29] = price29;
        price29 = null;
        imageViews[29] = img29;
        img29 = null;
        anchorPanes[29] = anchor29;
        anchor29 = null;

        names[30] = name30;
        name30 = null;
        scores[30] = score30;
        score30 = null;
        prices[30] = price30;
        price30 = null;
        imageViews[30] = img30;
        img30 = null;
        anchorPanes[30] = anchor30;
        anchor30 = null;

        names[31] = name31;
        name31 = null;
        scores[31] = score31;
        score31 = null;
        prices[31] = price31;
        price31 = null;
        imageViews[31] = img31;
        img31 = null;
        anchorPanes[31] = anchor31;
        anchor31 = null;

        names[32] = name32;
        name32 = null;
        scores[32] = score32;
        score32 = null;
        prices[32] = price32;
        price32 = null;
        imageViews[32] = img32;
        img32 = null;
        anchorPanes[32] = anchor32;
        anchor32 = null;

        names[33] = name33;
        name33 = null;
        scores[33] = score33;
        score33 = null;
        prices[33] = price33;
        price33 = null;
        imageViews[33] = img33;
        img33 = null;
        anchorPanes[33] = anchor33;
        anchor33 = null;

        names[34] = name34;
        name34 = null;
        scores[34] = score34;
        score34 = null;
        prices[34] = price34;
        price34 = null;
        imageViews[34] = img34;
        img34 = null;
        anchorPanes[34] = anchor34;
        anchor34 = null;

        names[35] = name35;
        name35 = null;
        scores[35] = score35;
        score35 = null;
        prices[35] = price35;
        price35 = null;
        imageViews[35] = img35;
        img35 = null;
        anchorPanes[35] = anchor35;
        anchor35 = null;

        names[36] = name36;
        name36 = null;
        scores[36] = score36;
        score36 = null;
        prices[36] = price36;
        price36 = null;
        imageViews[36] = img36;
        img36 = null;
        anchorPanes[36] = anchor36;
        anchor36 = null;

        names[37] = name37;
        name37 = null;
        scores[37] = score37;
        score37 = null;
        prices[37] = price37;
        price37 = null;
        imageViews[37] = img37;
        img37 = null;
        anchorPanes[37] = anchor37;
        anchor37 = null;

        names[38] = name38;
        name38 = null;
        scores[38] = score38;
        score38 = null;
        prices[38] = price38;
        price38 = null;
        imageViews[38] = img38;
        img38 = null;
        anchorPanes[38] = anchor38;
        anchor38 = null;

        names[39] = name39;
        name39 = null;
        scores[39] = score39;
        score39 = null;
        prices[39] = price39;
        price39 = null;
        imageViews[39] = img39;
        img39 = null;
        anchorPanes[39] = anchor39;
        anchor39 = null;

        names[40] = name40;
        name40 = null;
        scores[40] = score40;
        score40 = null;
        prices[40] = price40;
        price40 = null;
        imageViews[40] = img40;
        img40 = null;
        anchorPanes[40] = anchor40;
        anchor40 = null;

        names[41] = name41;
        name41 = null;
        scores[41] = score41;
        score41 = null;
        prices[41] = price41;
        price41 = null;
        imageViews[41] = img41;
        img41 = null;
        anchorPanes[41] = anchor41;
        anchor41 = null;

        names[42] = name42;
        name42 = null;
        scores[42] = score42;
        score42 = null;
        prices[42] = price42;
        price42 = null;
        imageViews[42] = img42;
        img42 = null;
        anchorPanes[42] = anchor42;
        anchor42 = null;

        names[43] = name43;
        name43 = null;
        scores[43] = score43;
        score43 = null;
        prices[43] = price43;
        price43 = null;
        imageViews[43] = img43;
        img43 = null;
        anchorPanes[43] = anchor43;
        anchor43 = null;

        names[44] = name44;
        name44 = null;
        scores[44] = score44;
        score44 = null;
        prices[44] = price44;
        price44 = null;
        imageViews[44] = img44;
        img44 = null;
        anchorPanes[44] = anchor44;
        anchor44 = null;

        names[45] = name45;
        name45 = null;
        scores[45] = score45;
        score45 = null;
        prices[45] = price45;
        price45 = null;
        imageViews[45] = img45;
        img45 = null;
        anchorPanes[45] = anchor45;
        anchor45 = null;

        names[46] = name46;
        name46 = null;
        scores[46] = score46;
        score46 = null;
        prices[46] = price46;
        price46 = null;
        imageViews[46] = img46;
        img46 = null;
        anchorPanes[46] = anchor46;
        anchor46 = null;

        names[47] = name47;
        name47 = null;
        scores[47] = score47;
        score47 = null;
        prices[47] = price47;
        price47 = null;
        imageViews[47] = img47;
        img47 = null;
        anchorPanes[47] = anchor47;
        anchor47 = null;

        names[48] = name48;
        name48 = null;
        scores[48] = score48;
        score48 = null;
        prices[48] = price48;
        price48 = null;
        imageViews[48] = img48;
        img48 = null;
        anchorPanes[48] = anchor48;
        anchor48 = null;

        names[49] = name49;
        name49 = null;
        scores[49] = score49;
        score49 = null;
        prices[49] = price49;
        price49 = null;
        imageViews[49] = img49;
        img49 = null;
        anchorPanes[49] = anchor49;
        anchor49 = null;

        brands[0] = brand0;
        brand0 = null;
        brands[1] = brand1;
        brand1 = null;
        brands[2] = brand2;
        brand2 = null;
        brands[3] = brand3;
        brand3 = null;
        brands[4] = brand4;
        brand4 = null;
        brands[5] = brand5;
        brand5 = null;
        brands[6] = brand6;
        brand6 = null;
        brands[7] = brand7;
        brand7 = null;
        brands[8] = brand8;
        brand8 = null;
        brands[9] = brand9;
        brand9 = null;
        brands[10] = brand10;
        brand10 = null;
        brands[11] = brand11;
        brand11 = null;
        brands[12] = brand12;
        brand12 = null;
        brands[13] = brand13;
        brand13 = null;
        brands[14] = brand14;
        brand14 = null;
        brands[15] = brand15;
        brand15 = null;
        brands[16] = brand16;
        brand16 = null;
        brands[17] = brand17;
        brand17 = null;
        brands[18] = brand18;
        brand18 = null;
        brands[19] = brand19;
        brand19 = null;
        brands[20] = brand20;
        brand20 = null;
        brands[21] = brand21;
        brand21 = null;
        brands[22] = brand22;
        brand22 = null;
        brands[23] = brand23;
        brand23 = null;
        brands[24] = brand24;
        brand24 = null;
        brands[25] = brand25;
        brand25 = null;
        brands[26] = brand26;
        brand26 = null;
        brands[27] = brand27;
        brand27 = null;
        brands[28] = brand28;
        brand28 = null;
        brands[29] = brand29;
        brand29 = null;
        brands[30] = brand30;
        brand30 = null;
        brands[31] = brand31;
        brand31 = null;
        brands[32] = brand32;
        brand32 = null;
        brands[33] = brand33;
        brand33 = null;
        brands[34] = brand34;
        brand34 = null;
        brands[35] = brand35;
        brand35 = null;
        brands[36] = brand36;
        brand36 = null;
        brands[37] = brand37;
        brand37 = null;
        brands[38] = brand38;
        brand38 = null;
        brands[39] = brand39;
        brand39 = null;
        brands[40] = brand40;
        brand40 = null;
        brands[41] = brand41;
        brand41 = null;
        brands[42] = brand42;
        brand42 = null;
        brands[43] = brand43;
        brand43 = null;
        brands[44] = brand44;
        brand44 = null;
        brands[45] = brand45;
        brand45 = null;
        brands[46] = brand46;
        brand46 = null;
        brands[47] = brand47;
        brand47 = null;
        brands[48] = brand48;
        brand48 = null;
        brands[49] = brand49;
        brand49 = null;
    }
    @FXML
    private void select0() throws IOException {
        productSelected(products[first].getId());
    }
    @FXML
    private void select1() throws IOException {
        productSelected(products[first + 1].getId());
    }
    @FXML
    private void select2() throws IOException {
        productSelected(products[first + 2].getId());
    }
    @FXML
    private void select3() throws IOException {
        productSelected(products[first + 3].getId());
    }
    @FXML
    private void select4() throws IOException {
        productSelected(products[first + 4].getId());
    }
    @FXML
    private void select5() throws IOException {
        productSelected(products[first + 5].getId());
    }
    @FXML
    private void select6() throws IOException {
        productSelected(products[first + 6].getId());
    }
    @FXML
    private void select7() throws IOException {
        productSelected(products[first + 7].getId());
    }
    @FXML
    private void select8() throws IOException {
        productSelected(products[first + 8].getId());
    }
    @FXML
    private void select9() throws IOException {
        productSelected(products[first + 9].getId());
    }
    @FXML
    private void select10() throws IOException {
        productSelected(products[first + 10].getId());
    }
    @FXML
    private void select11() throws IOException {
        productSelected(products[first + 11].getId());
    }
    @FXML
    private void select12() throws IOException {
        productSelected(products[first + 12].getId());
    }
    @FXML
    private void select13() throws IOException {
        productSelected(products[first + 13].getId());
    }
    @FXML
    private void select14() throws IOException {
        productSelected(products[first + 14].getId());
    }
    @FXML
    private void select15() throws IOException {
        productSelected(products[first + 15].getId());
    }
    @FXML
    private void select16() throws IOException {
        productSelected(products[first + 16].getId());
    }
    @FXML
    private void select17() throws IOException {
        productSelected(products[first + 17].getId());
    }
    @FXML
    private void select18() throws IOException {
        productSelected(products[first + 18].getId());
    }
    @FXML
    private void select19() throws IOException {
        productSelected(products[first + 19].getId());
    }
    @FXML
    private void select20() throws IOException {
        productSelected(products[first + 20].getId());
    }
    @FXML
    private void select21() throws IOException {
        productSelected(products[first + 21].getId());
    }
    @FXML
    private void select22() throws IOException {
        productSelected(products[first + 22].getId());
    }
    @FXML
    private void select23() throws IOException {
        productSelected(products[first + 23].getId());
    }
    @FXML
    private void select24() throws IOException {
        productSelected(products[first + 24].getId());
    }
    @FXML
    private void select25() throws IOException {
        productSelected(products[first + 25].getId());
    }
    @FXML
    private void select26() throws IOException {
        productSelected(products[first + 26].getId());
    }
    @FXML
    private void select27() throws IOException {
        productSelected(products[first + 27].getId());
    }
    @FXML
    private void select28() throws IOException {
        productSelected(products[first + 28].getId());
    }
    @FXML
    private void select29() throws IOException {
        productSelected(products[first + 29].getId());
    }
    @FXML
    private void select30() throws IOException {
        productSelected(products[first + 30].getId());
    }
    @FXML
    private void select31() throws IOException {
        productSelected(products[first + 31].getId());
    }
    @FXML
    private void select32() throws IOException {
        productSelected(products[first + 32].getId());
    }
    @FXML
    private void select33() throws IOException {
        productSelected(products[first + 33].getId());
    }
    @FXML
    private void select34() throws IOException {
        productSelected(products[first + 34].getId());
    }
    @FXML
    private void select35() throws IOException {
        productSelected(products[first + 35].getId());
    }
    @FXML
    private void select36() throws IOException {
        productSelected(products[first + 36].getId());
    }
    @FXML
    private void select37() throws IOException {
        productSelected(products[first + 37].getId());
    }
    @FXML
    private void select38() throws IOException {
        productSelected(products[first + 38].getId());
    }
    @FXML
    private void select39() throws IOException {
        productSelected(products[first + 39].getId());
    }
    @FXML
    private void select40() throws IOException {
        productSelected(products[first + 40].getId());
    }
    @FXML
    private void select41() throws IOException {
        productSelected(products[first + 41].getId());
    }
    @FXML
    private void select42() throws IOException {
        productSelected(products[first + 42].getId());
    }
    @FXML
    private void select43() throws IOException {
        productSelected(products[first + 43].getId());
    }
    @FXML
    private void select44() throws IOException {
        productSelected(products[first + 44].getId());
    }
    @FXML
    private void select45() throws IOException {
        productSelected(products[first + 45].getId());
    }
    @FXML
    private void select46() throws IOException {
        productSelected(products[first + 46].getId());
    }
    @FXML
    private void select47() throws IOException {
        productSelected(products[first + 47].getId());
    }
    @FXML
    private void select48() throws IOException {
        productSelected(products[first + 48].getId());
    }
    @FXML
    private void select49() throws IOException {
        productSelected(products[first + 49].getId());
    }
}