package source.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import source.Global;
import source.User;
import source.database.DiscountCodeDataBase;
import source.products.DiscountCode;
import source.products.database;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import static source.Global.getStage;

public class PersonPageController implements Initializable {

    @FXML
    private TextField P_Username ;
    @FXML
    private TextField P_First_Name , P_Last_Name;
    @FXML
    private TextField P_Email ;
    @FXML
    private TextField P_User_Type ;
    @FXML
    private TextField P_Phone ;
    @FXML
    private TextField P_Password ;
    @FXML
    private TextField  P_PocketMoney ;
    @FXML
    private TextField P_Company;
    @FXML
    private Label companyText;
    @FXML
    private TextField increaseBalanceText;
    @FXML
    private Button backButton;

    @FXML
    private void apply() {
        String phone = P_Phone.getText();
        if (phone == null) {
            phone = "";
        }
        String firstName = P_First_Name.getText();
        if (firstName == null) {
            firstName = "";
        }
        String lastName = P_Last_Name.getText();
        if (lastName == null) {
            lastName = "";
        }
        String email = P_Email.getText();
        if (email == null) {
            email = "";
        }
        String password = P_Password.getText();
        if (password == null) {
            password = "";
        }
        String company = P_Company.getText();
        if (company == null) {
            company = "";
        }
        if (phone.length() > 20 || firstName.length() > 50 || lastName.length() > 50 || email.length() > 45 || password.length() > 45 || company.length() > 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Passed Character limit");
            StringBuilder stringBuilder = new StringBuilder();
            if (phone.length() > 20) stringBuilder.append("phone input length : ").append(phone.length()).append("\n");
            if (firstName.length() > 50) stringBuilder.append("first name input length : ").append(firstName.length()).append("\n");
            if (lastName.length() > 50) stringBuilder.append("last name input length : ").append(lastName.length()).append("\n");
            if (password.length() > 50) stringBuilder.append("password input length : ").append(password.length()).append("\n");
            if (email.length() > 45) stringBuilder.append("email input length : ").append(email.length()).append("\n");
            if (company.length() > 50) stringBuilder.append("company input length : ").append(company.length()).append("\n");
            alert.setContentText(stringBuilder.toString());
            alert.showAndWait();
            return;
        }
        if (phone.equals("null") || firstName.equals("null") || lastName.equals("null") || email.equals("null") || password.equals("null") || company.equals("null")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Illegal word has been used");
            alert.setContentText("You can't use letter \"null\"");
            alert.showAndWait();
            return;
        }
        if (!phone.isEmpty()) {
            Global.setUser_phoneNumber(phone);
        }
        if (!firstName.isEmpty()) {
            Global.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            Global.setLastName(lastName);
        }
        if (!email.isEmpty()) {
            Global.setEmail(email);
            database.changeEmail();
        }
        if (!password.isEmpty()) {
            Global.setPassword(password);
            database.changePassword();
        }
        if (!company.isEmpty()) {
            Global.setVendorCompany(company);
        }
        database.setExactUserInfo();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Changes has been applied");
        alert.showAndWait();
    }
    @FXML
    private void goBack() {
        Stage stage = Global.getStage();
        try {
            if (PersonMenu.lastLocation == 1) {
                new Menu(stage);
            }
            if (PersonMenu.lastLocation == 2) {
                ProductPage.previousScene = 1;
                new ProductPage(stage, ProductPage.PRODUCT_ID);
            }
            if (PersonMenu.lastLocation == 3) {
                new AuctionsMenu(stage, true);
            }
            if (PersonMenu.lastLocation == 4) {
                new AuctionPage(stage, AuctionPage.AUCTION_ID);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void openCart() {
        try {
            new ShoppingCart(Global.getStage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void changeBalance() {
        try {
            double value = Double.parseDouble(increaseBalanceText.getText());
            if (value <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input cannot be negative");
                alert.showAndWait();
                return;
            }
            System.out.println("BANK!");
            //TODO: go to bank page.
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong price input");
            alert.setHeaderText("Input is not a double");
            alert.setContentText("Please Enter only digits and maximum one dot");
            alert.showAndWait();
        }
    }
    @FXML
    private void openHistory() {
        //TODO implement shopping history
    }
    @FXML
    private void showCodes() {
        DiscountCode[] discountCodes = DiscountCodeDataBase.getAllUserDiscountCodes();
        if (discountCodes.length == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You don't have a discount code");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Discount code");
        alert.setHeaderText("Your discount codes: ");
        StringBuilder stringBuilder = new StringBuilder();
        for (DiscountCode discountCode : discountCodes) {
            stringBuilder.append(discountCode.toString()).append("\n");
        }
        alert.setContentText(stringBuilder.toString());
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backButton.setGraphic(new ImageView(new Image("prevSmall.png")));
        P_PocketMoney.setText(User.getBalance() + "$");
        if (User.getUser_type() == 1) {
            P_User_Type.setText("Vendor");
        }
        else if (User.getUser_type() == 3) {
            P_User_Type.setText("Admin");
        }
        else {
            P_User_Type.setText("Consumer");
        }
        P_Username.setText(Global.getUsername());
        if (Global.getUser_phoneNumber() != null) {
            P_Phone.setText(Global.getUser_phoneNumber());
        }
        if (Global.getFirstName() != null) {
            P_First_Name.setText(Global.getFirstName());
        }
        if (Global.getLastName() != null) {
            P_Last_Name.setText(Global.getLastName());
        }
        if (Global.getEmail() != null) {
            P_Email.setText(Global.getEmail());
        }
        P_Password.setText(Global.getPassword());
        if (Global.getUser_type() != 2) {
            companyText.setVisible(false);
            P_Company.setVisible(false);
        }
        else {
            P_Company.setText(Global.getVendorCompany());
        }
        P_PocketMoney.setText(String.valueOf(Global.getBalance()));
    }
}
