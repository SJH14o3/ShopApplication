package source.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import source.products.database;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private Button su_SignupButton;
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
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                if (!su_UsernameField.getText().trim().isEmpty() && !su_EmailField.getText().trim().isEmpty() && !su_PasswordField.getText().trim().isEmpty() &&
                        !su_ConfirmField.getText().trim().isEmpty()) {
                    database.SignUpUser(event, su_UsernameField.getText(), su_EmailField.getText(), su_PasswordField.getText(), toggleName);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Fill all information");
                    alert.show();
                }
            }
        });
        su_LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.changeScene(event , "login.fxml" , "Log in!" , null , null);
            }
        });
    }

}

