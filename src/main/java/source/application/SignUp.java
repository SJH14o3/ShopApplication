package source.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import source.Global;
import source.products.database;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp implements Initializable {
    @FXML
    private Button su_SignupButton;
    private int confirm = 1;
    @FXML
    private Button su_LoginButton;
    @FXML
    private RadioButton su_ConsumerButton;
    @FXML
    private RadioButton su_VendorButton;
    @FXML
    private TextField su_UsernameField;
    @FXML
    private TextField su_EmailField;
    @FXML
    private PasswordField su_PasswordField;
    @FXML
    private PasswordField su_ConfirmField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ToggleGroup toggleGroup = new ToggleGroup();
        su_ConsumerButton.setToggleGroup(toggleGroup);
        su_VendorButton.setToggleGroup(toggleGroup);
        su_VendorButton.setSelected(true);

        su_SignupButton.setOnAction(new EventHandler<ActionEvent>() {
            //************************
            
            //*************************
            @Override
            public void handle(ActionEvent event) {
                //**************************
                Pattern pattern = Pattern.compile("^\\w*\\d$");
                Matcher cardfield1 = pattern.matcher(su_UsernameField.getText());
                Matcher cardfield2 = pattern.matcher(su_EmailField.getText());
                Matcher cardfield3 = pattern.matcher(su_PasswordField.getText());
                if(su_PasswordField.getText() == su_ConfirmField.getText()){
                    confirm=0;
                }

                if(cardfield1.find() && cardfield2.find() && cardfield3.find() && confirm == 0){

                    String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                    if (!su_UsernameField.getText().trim().isEmpty() && !su_EmailField.getText().trim().isEmpty() && !su_PasswordField.getText().trim().isEmpty() &&
                            !su_ConfirmField.getText().trim().isEmpty()) {
                        database.SignUpUser(event, su_UsernameField.getText(), su_EmailField.getText(), su_PasswordField.getText(), toggleName);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Fill all information");
                        alert.show();
                    }
                    
                } else if (!cardfield1.find()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong Username");
                    alert.show();
                }
                else if (!cardfield2.find()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong Email");
                    alert.show();
                }
                else if (!cardfield3.find()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Wrong Password");
                    alert.show();
                }
                else if (confirm == 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Not Confirm");
                    alert.show();
                }

            }
        });
        su_LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = Global.getStage();
                stage.setX(571);
                stage.setY(200);
                Parent root = null;
                try {
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
        });
    }

}

