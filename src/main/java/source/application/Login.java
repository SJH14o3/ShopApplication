package source.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import source.products.database;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public Login(){

    }
    @FXML
    private Button LoginButton ;
    @FXML
    private Button SignupButton ;
    @FXML
    private Label WrongLogin;
    @FXML
    private TextField UsernameTextField ;
    @FXML
    private javafx.scene.control.PasswordField PasswordField;
    @FXML
    private ImageView BrandigImageView;

//    public void userLogin(ActionEvent event) throws IOException {
//
//        WrongLogin.setText("You Try To Login");
//        if(UsernameTextField.getText().isBlank() == false && PasswordField.getText().isBlank() == false){
//            validateLogin();
//        }else {
//            WrongLogin.setText("Plz enter Username & Password");
//        }
//    }
//    public void up(ActionEvent event){
//        System.out.println("74");
//    }
//    public void validateLogin(){
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.LoginUser(event , UsernameTextField.getText() , PasswordField.getText() );
            }
        });
        SignupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //  System.out.println("56");
                database.changeScene(event , "Signup.fxml" , "Sign up!" , null , null );
            }
        });
    }
}

