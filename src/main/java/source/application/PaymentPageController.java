package source.application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import source.Global;
import source.products.CartDataBase;
import source.products.database;
import source.threads.SendEmail;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.*;

import java.net.URL;
import java.util.ResourceBundle;


public class PaymentPageController implements Initializable {

    public PaymentPageController(){}

    @FXML
    private TextField cardField_1;
    @FXML
    private TextField cardField_2;
    @FXML
    private TextField cardField_3;
    @FXML
    private TextField cardField_4;
    @FXML
    private TextField CVV2Field;
    @FXML
    private TextField ExField_1;
    @FXML
    private TextField ExField_2;
    @FXML
    private PasswordField Passfield;
    @FXML
    private TextField EmailField;
    @FXML
    private Button PayButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pattern pattern = Pattern.compile("^\\d{4}$");
                Matcher cardfield1 = pattern.matcher(cardField_1.getText());
                Matcher cardfield2 = pattern.matcher(cardField_2.getText());
                Matcher cardfield3 = pattern.matcher(cardField_3.getText());
                Matcher cardfield4 = pattern.matcher(cardField_4.getText());
                //********************
                Pattern CVVPattern = Pattern.compile("^\\d{3,4}$");
                Matcher Cvv2 = CVVPattern.matcher(CVV2Field.getText());
                //********************
                Pattern ExpireyPattern = Pattern.compile("^\\d{2}$");
                Matcher Expirey1 = ExpireyPattern.matcher(ExField_1.getText());
                Matcher Expirey2 = ExpireyPattern.matcher(ExField_2.getText());
                //********************
                Pattern PassPattern = Pattern.compile("^\\d{5,}$");
                Matcher pass2 = PassPattern.matcher(Passfield.getText());
                //********************
//                Pattern EmailPattern = Pattern.compile("^\\w+@\\w$");
//                Matcher Email = EmailPattern.matcher(EmailField.getText());
                /*System.out.println(cardfield1.find());
                System.out.println(cardfield2.find());
                System.out.println(cardfield3.find());
                System.out.println(cardfield4.find());
                System.out.println(Cvv2.find());
                System.out.println(Expirey2.find());
                System.out.println(Expirey1.find());
                System.out.println(pass2.find());
                System.out.println(emailValidate(EmailField.getText()));
                System.out.println();*/
                if (cardfield1.find() && cardfield2.find() && cardfield3.find() && cardfield4.find() && Cvv2.find() && Expirey1.find() && Expirey2.find() && pass2.find() && emailValidate(EmailField.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("Your payment is done!");
                    if (PaymentMenu.beforePaymentPage == 1) {
                        alert.setContentText("Your order will be shipped to you soon!");
                        CartDataBase.archiveCart();
                        Thread sendEmail = new SendEmail(EmailField.getText(), "Your purchase was successful!", "Make sure to rate your purchases!\nAnd also comment!");
                        sendEmail.start();
                        alert.showAndWait();
                    }
                    else {
                        alert.setContentText("Your account has been charged successfully");
                        database.changeBalance(PaymentMenu.changedBalance , Global.getUser_id());
                        Thread sendEmail = new SendEmail(EmailField.getText(), "Your purchase was successful!", "Your account has been charged up!");
                        sendEmail.start();
                        alert.showAndWait();
                    }
                    try {
                        new Menu(Global.getStage());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    System.out.println("failed");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Something went wrong");
                    alert.setContentText("Make sure you have entered details correctly");
                    alert.showAndWait();
                }
            }
        });





    }
    @FXML
    private void cancel() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Are you want to abort the transaction?");
        alert.setContentText("Select action");
        ButtonType abort = new ButtonType("abort");
        ButtonType continueButton = new ButtonType("continue");
        alert.getButtonTypes().setAll(abort, continueButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == abort) {
            Stage stage = Global.getStage();
            stage.setX(327);
            stage.setY(100);
            stage.setHeight(720);
            stage.setWidth(1280);
            try {
                if (PaymentMenu.beforePaymentPage == 1) {
                    new ShoppingCart(stage);
                } else {
                    new PersonMenu(stage);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static boolean emailValidate(String email) {
        Matcher matcher = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}(.[a-z]{2,3})+$|^$", Pattern.CASE_INSENSITIVE).matcher(email);

        return matcher.find();
    }
}