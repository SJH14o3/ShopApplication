package source.application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    private Button CancelButton;
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
                if(cardfield1.find() && cardfield2.find() && cardfield3.find() && cardfield4.find() && Cvv2.find()
                        && Expirey1.find() && Expirey2.find() && pass2.find()){

                    System.out.println("lool");
                }
                 System.out.println("test");
            }
        });





    }
}