package source.application;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import source.Global;
import source.database.ScoresDataBase;
import source.products.CartDataBase;
import source.products.Product;
import source.products.ProductDataBase;
import source.threads.RatePanelStopInterruption;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {
    private Product product;
    @FXML
    private AnchorPane ratePane;
    @FXML
    private ImageView displayProductPicture, h1, h2, h3, h4, h5;
    @FXML
    private Label displayProductName, quantityInStock;
    @FXML
    private Spinner<Integer> setQuantitySpinner;
    @FXML
    private ImageView displayScoreImage;

    @FXML
    private Label displayProductBrand;

    @FXML
    private Label displayProductDescription;

    @FXML
    private Label displayProductPrice;

    @FXML
    private Button addToCartButton, goToComments, rateToProduct, backButton, vendor;

    private boolean isRatePanelVisible = false;
    private boolean isAlertUp = false;
    public static boolean ratePaneIsMoving = false;
    private void highLightFade(ImageView image, boolean isFadeIn) {
        double startValue = 1;
        double endValue = 0;
        if (isFadeIn) {
            startValue = 0;
            endValue = 1;
        }
        FadeTransition fade = new FadeTransition();
        fade.setNode(image);
        fade.setDuration(Duration.millis(300));
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(startValue);
        fade.setToValue(endValue);
        fade.play();
    }
    @FXML
    private void m5Enter() {
        highLightFade(h5, true);
    }
    @FXML
    private void m4Enter() {
        highLightFade(h4, true);
    }
    @FXML
    private void m3Enter() {
        highLightFade(h3, true);
    }
    @FXML
    private void m2Enter() {
        highLightFade(h2, true);
    }
    @FXML
    private void m1Enter() {
        highLightFade(h1, true);
    }
    @FXML
    private void m5Exit() {
        if (!isAlertUp) {
            highLightFade(h5, false);
        }
    }
    @FXML
    private void m4Exit() {
        if (!isAlertUp){
            highLightFade(h4, false);
        }
    }
    @FXML
    private void m3Exit() {
        if (!isAlertUp){
            highLightFade(h3, false);
        }
    }
    @FXML
    private void m2Exit() {
        if (!isAlertUp){
            highLightFade(h2, false);
        }
    }
    @FXML
    private void m1Exit() {
        if (!isAlertUp){
            highLightFade(h1, false);
        }
    }
    private void setScoreImage() {
        if(product.getScore() <= 0.25){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star0.png"))));
        }else if(product.getScore() <= 0.75){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star5.png"))));
        }else if(product.getScore() <= 1.25){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star10.png"))));
        }else if(product.getScore() <= 1.75){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star15.png"))));
        }else if(product.getScore() <= 2.25){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star20.png"))));
        }else if(product.getScore() <= 2.75){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star25.png"))));
        }else if(product.getScore() <= 3.25){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star30.png"))));
        }else if(product.getScore() <= 3.75){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star35.png"))));
        }else if(product.getScore() <= 4.25){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star40.png"))));
        }else if(product.getScore() <= 4.75){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star45.png"))));
        }else if(product.getScore() > 4.75){
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star50.png"))));
        }else{
            displayScoreImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rating/star0.png"))));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product = ProductDataBase.getProduct(ProductPage.PRODUCT_ID);
        h1.setOpacity(0);
        h2.setOpacity(0);
        h3.setOpacity(0);
        h4.setOpacity(0);
        h5.setOpacity(0);
        displayProductPicture.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Products/" + product.getImageAddress() + ".jpg"))));
        displayProductName.setText(product.getName());
        displayProductBrand.setText(product.getBrand());
        displayProductDescription.setText(product.getDescription());
        if (product.getQuantity() > 0) {
            displayProductPrice.setText(product.getPrice() + "$");
        }
        else displayProductPrice.setText("Out of stock");
        quantityInStock.setText("Quantity in Stock: " + product.getQuantity());
        System.out.println(product.getScore());
        setScoreImage();
        backButton.setGraphic(new ImageView(new Image("prevSmall.png")));

        if (product.getQuantity() > 0) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, product.getQuantity());
            valueFactory.setValue(1);
            setQuantitySpinner.setValueFactory(valueFactory);
        }
        if (Global.getUser_type() != 2) {
            vendor.setVisible(false);
        }
    }
    @FXML
    private void updateStocks() {
        InsertProduct.changeStock = true;
        new InsertProduct(Global.getStage());
    }
    @FXML
    private void addToCart(){
        if (CartDataBase.doesUserHaveThisItemInTheirCart()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You already have this item in your cart");
            alert.showAndWait();
            return;
        }
        if (product.getQuantity() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("This product is out of stock");
            alert.setContentText("Comeback later");
            alert.showAndWait();
            return;
        }
        CartDataBase.insertCart(setQuantitySpinner.getValue());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Successfully added your request to your cart!");
        alert.showAndWait();
    }
    private void rateProduct(int in) {
        if (ratePaneIsMoving) {
            return;
        }
        isAlertUp = true;
        //TODO: First check if user has bought this product.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rate");
        alert.setHeaderText("You are about to rate this product with score " + in + "\nAction cannot be reverted");
        alert.setContentText("Are you Sure?");
        ButtonType confirm = new ButtonType("Confirm");
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(confirm, cancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == confirm) {
            product.recalculateScore(in);
            System.out.println("votes: " + product.getVoteCount());
            setScoreImage();
            ratePanelTransition(-50);
        }
        isAlertUp = false;
    }
    @FXML
    private void rate1() {
        //highLightFade(h1, true);
        rateProduct(1);
        highLightFade(h1, false);
    }
    @FXML
    private void rate2() {
        //highLightFade(h2, true);
        rateProduct(2);
        highLightFade(h2, false);
    }
    @FXML
    private void rate3() {
        //highLightFade(h3, true);
        rateProduct(3);
        highLightFade(h3, false);
    }
    @FXML
    private void rate4() {
        //highLightFade(h4, true);
        rateProduct(4);
        highLightFade(h4, false);
    }
    @FXML
    private void rate5() {
       // highLightFade(h5, true);
        rateProduct(5);
        highLightFade(h5, false);
    }
    @FXML
    private void goToComments(){
        new CommentsMenu(Global.getStage());
    }
    @FXML
    private void rate(){
        if (ratePaneIsMoving) {
            return;
        }
        if (ScoresDataBase.checkUserHasRatedAlready()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You can't rate this product");
            alert.setContentText("You have already rated the product");
            alert.showAndWait();
            return;
        }
        double change = 50;
        if (isRatePanelVisible) {
            change *= -1;
            isRatePanelVisible = false;
        }
        else isRatePanelVisible = true;
        ratePanelTransition(change);
    }
    private void ratePanelTransition(double change) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ratePane);
        transition.setDuration(Duration.millis(1500));
        transition.setByY(change);
        transition.play();
        Thread ratePanelStopInterruption = new RatePanelStopInterruption();
        ratePanelStopInterruption.start();
    }
    @FXML
    private void back() {
        try {
            new Menu(Global.getStage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}