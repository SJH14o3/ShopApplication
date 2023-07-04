package source.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import source.Global;
import source.products.database;

import java.io.IOException;
import java.util.Objects;

public class Login {
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
    @FXML
    private void signUp() {

        Stage stage = Global.getStage();
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Signup.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(root , 776 , 448));
    }
    @FXML
    private void logIn() {
        database.LoginUser(null , UsernameTextField.getText() , PasswordField.getText() );
    }
}

