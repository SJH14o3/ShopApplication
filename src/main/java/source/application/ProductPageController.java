package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import source.Global;
import source.products.Product;
import source.products.ProductDataBase;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageController implements Initializable {

    @FXML
    private ImageView displayProductPicture;

    @FXML
    private Label displayProductName;

    @FXML
    private ImageView displayScoreImage;

    @FXML
    private Label displayProductBrand;

    @FXML
    private Label displayProductDescription;

    @FXML
    private Label displayProductPrice;

    @FXML
    private Button addToCartButton, goToComments, rateToProduct, backButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Product product = ProductDataBase.getProduct(ProductPage.PRODUCT_ID);

        displayProductPicture.setImage(new Image(product.getImageAddress() + ".jpg"));
        displayProductName.setText(product.getName());
        displayProductBrand.setText(product.getBrand());
        displayProductDescription.setText(product.getDescription());
        displayProductPrice.setText(String.valueOf(product.getPrice()));

        if(product.getScore() == 0){
            displayScoreImage.setImage(new Image("star0.jpg"));
        }else if(product.getScore() == 0.5){
            displayScoreImage.setImage(new Image("star5.jpg"));
        }else if(product.getScore() == 1){
            displayScoreImage.setImage(new Image("star10.jpg"));
        }else if(product.getScore() == 1.5){
            displayScoreImage.setImage(new Image("star15.jpg"));
        }else if(product.getScore() == 2){
            displayScoreImage.setImage(new Image("star20.jpg"));
        }else if(product.getScore() == 2.5){
            displayScoreImage.setImage(new Image("star25.jpg"));
        }else if(product.getScore() == 3){
            displayScoreImage.setImage(new Image("star30.jpg"));
        }else if(product.getScore() == 3.5){
            displayScoreImage.setImage(new Image("star35.jpg"));
        }else if(product.getScore() == 4){
            displayScoreImage.setImage(new Image("star40.jpg"));
        }else if(product.getScore() == 4.5){
            displayScoreImage.setImage(new Image("star45.jpg"));
        }else if(product.getScore() == 5){
            displayScoreImage.setImage(new Image("star50.jpg"));
        }else{
            displayScoreImage.setImage(new Image("star0.jpg"));
        }
        backButton.setGraphic(new ImageView(new Image("prev.png")));
    }

    @FXML
    private void addToCart(){

    }

    @FXML
    private void goToComments(){
        new CommentsMenu(Global.getStage());
    }
    @FXML
    private void rate(){

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