package source.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import source.Global;
import source.products.database;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Login implements Initializable {

    public int Logiin =0;
    public int a , b ;
    public Login(){

    }
    @FXML
    private Button LoginButton ;
    @FXML
    private Button SignupButton ;
    @FXML
    private TextField Enter_Captcha ;
    @FXML
    private Label WrongLogin;
    @FXML
    private TextField UsernameTextField ;
    @FXML
    private Label captchaField;

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
        String str = Integer.toString(a+b);
        if(Enter_Captcha.getText().equals(str)){
            Logiin=1;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Wrong Captcha");
            alert.show();
        }
        if(Logiin == 1) {
            database.LoginUser(null, UsernameTextField.getText(), PasswordField.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Random rand = new Random();
        a = rand.nextInt(99);
        b = rand.nextInt(99);

        try{
            captchaField.setText(a + "+" + b + "?");
            captchaField.textAlignmentProperty();
        }catch (NullPointerException e){
            e.getMessage();
        }

        System.out.println(a+b);

    }
}

