package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import source.products.Product;
import source.products.ProductDataBase;

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
    private Label displayProductDescription;
    @FXML
    private ImageView displayProductPicture;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Product product = ProductDataBase.getProduct(ProductPage.PRODUCT_ID);
        displayProductName.setText(product.getName());
        displayProductBrand.setText(product.getBrand());
        displayProductPrice.setText(String.valueOf(product.getPrice()));
        displayProductQuantity.setText(String.valueOf(product.getQuantity()));
        displayProductScore.setText(String.valueOf(product.getScore()));
        displayProductDescription.setText(product.getDescription());
        displayProductPicture.setImage(new Image(product.getImageAddress() + ".jpg"));
    }
}