package source.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import source.Global;
import source.User;
import source.database.DiscountCodeDataBase;
import source.products.database;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrePaymentPageController implements Initializable {

    @FXML
    private TextField addressField, postalCodeField, phoneNumberField, discountCodeField;

    @FXML
    private Button goToBankPortalPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Global.getUser_address() != null) {
            addressField.setText(Global.getUser_address());
        }
        if (Global.getUser_postalCode() != null) {
            postalCodeField.setText(Global.getUser_postalCode());
        }
        if (Global.getUser_phoneNumber() != null) {
            phoneNumberField.setText(Global.getUser_phoneNumber());
        }
        goToBankPortalPage.setOnAction(event -> {
            if(isFormCompleted()){
                if (!checkCharacterLimit()) {
                    return;
                }
                if (!(discountCodeField.getText().isEmpty())) {
                    applyDiscountCode();
                }
                saveData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("You are about to transform to bank paying page");
                if (discountCodeField.getText().isEmpty()) {
                    alert.setContentText("Total price without discount : " + String.format("%.2f", ShoppingCart.ShoppingCartItemsPrices) + "$");
                }
                else {
                    alert.setContentText("Total price without discount : " + String.format("%.2f", ShoppingCart.ShoppingCartItemsPrices) + "$\nTotal price with discount: " + ShoppingCart.totalShoppingCart + "$");
                }
                ButtonType confirm = new ButtonType("Confirm");
                ButtonType cancel = new ButtonType("Cancel");
                alert.getButtonTypes().setAll(confirm, cancel);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == confirm) {
                    try {
                        PaymentMenu.beforePaymentPage = 1;
                        new PaymentMenu(Global.getStage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                showIncompleteAlert();
            }
        });
    }
    private boolean checkCharacterLimit() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (addressField.getText().length() > 200) {
            alert.setHeaderText("Passed character limit for address, input length: " + addressField.getText().length());
        }
        else if (postalCodeField.getText().length() > 20) {
            alert.setHeaderText("Passed character limit for postal code, input length: " + postalCodeField.getText().length());
        }
        else if (phoneNumberField.getText().length() > 20) {
            alert.setHeaderText("Passed character limit for postal code, input length: " + phoneNumberField.getText().length());
        }
        else return true;
        alert.setTitle("Error");
        alert.setContentText("Make sure you have not passed character limit");
        alert.showAndWait();
        return false;
    }

    private boolean isFormCompleted(){
        if(addressField.getText().isEmpty() || postalCodeField.getText().isEmpty() || phoneNumberField.getText().isEmpty()){
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
        double discount_percentage = DiscountCodeDataBase.checkCode(discountCodeField.getText());
        if (discount_percentage == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("This Discount Code Is Invalid");
            alert.setContentText("Make sure you have entered your code correctly\nYou can check your discount codes in user page");
            alert.showAndWait();
            ShoppingCart.totalShoppingCart = Double.parseDouble(String.format("%.2f", ShoppingCart.ShoppingCartItemsPrices));
            return;
        }
        ShoppingCart.totalShoppingCart = Double.parseDouble(String.format("%.2f", ShoppingCart.ShoppingCartItemsPrices * ((100-discount_percentage)/100)));
    }
}
