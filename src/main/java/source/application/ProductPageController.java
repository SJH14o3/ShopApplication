package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import source.products.Product;
import source.products.productDataBase;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {

    @FXML
    private Label displayProductName;

    @FXML
    private Label displayProductScore;

    @FXML
    private Label displayProductBrand;

    @FXML
    private Label displayProductQuantity;

    @FXML
    private Label displayProductPrice;

    @FXML
    private Button addToBasketButton;

    @FXML
    private Button seeReviewsButton;

    @FXML
    private Button writeAReviewButton;

    @FXML
    private TextField displayProductDescription;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayProductName.setText(productDataBase.getProduct(ProductPage.PRODUCT_ID).getName());
        displayProductBrand.setText(productDataBase.getProduct(ProductPage.PRODUCT_ID).getBrand());
        displayProductPrice.setText(String.valueOf(productDataBase.getProduct(ProductPage.PRODUCT_ID).getPrice()));
        displayProductQuantity.setText(String.valueOf(productDataBase.getProduct(ProductPage.PRODUCT_ID).getQuantity()));
        displayProductScore.setText(String.valueOf(productDataBase.getProduct(ProductPage.PRODUCT_ID).getScore()));
        displayProductDescription.setText(productDataBase.getProduct(ProductPage.PRODUCT_ID).getDescription());

    }



}
