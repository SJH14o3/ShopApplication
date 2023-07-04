package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import source.Global;
import source.User;
import source.database.DiscountCodeDataBase;
import source.products.CartDataBase;
import source.products.CartItem;
import source.products.database;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {

    @FXML
    private TextField addressField, postalCodeField, phoneNumberField, discountCodeField;

    @FXML
    private Button goToBankPortalPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        goToBankPortalPage.setOnAction(event -> {

            if(isFormCompleted()){
                saveData();
                applyDiscountCode();
                //رفتن به درگاه بانک
            }else {
                showIncompleteAlert();
            }
        });

    }

    private boolean isFormCompleted(){
        if(addressField.getText().isEmpty() || postalCodeField.getText().isEmpty() || phoneNumberField.getText().isEmpty() || discountCodeField.getText().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    private void showIncompleteAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Incomplete field");
        alert.setHeaderText(null);
        alert.setContentText("Please complete all fields");
        alert.showAndWait();
    }

    private void saveData(){
        String address = addressField.getText();
        String postalCode = postalCodeField.getText();
        String phoneNumber = phoneNumberField.getText();
        String discountCode = discountCodeField.getText();
        User.setUser_address(address);
        User.setUser_postalCode(postalCode);
        User.setUser_phoneNumber(phoneNumber);
        database.setCompleteUserInfo();
    }
    private void applyDiscountCode(){

        ShoppingCart.totalShoppingCart = ShoppingCart.ShoppingCartItemsPrices * (100-DiscountCodeDataBase.checkCode(discountCodeField.getText()))/100;
    }

}
