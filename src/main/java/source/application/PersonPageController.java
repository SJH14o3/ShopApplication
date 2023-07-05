package source.application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import source.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static source.Global.getStage;

public class PersonPageController implements Initializable {

    @FXML
    private Label P_Username ;
    @FXML
    private Button goTo_PaymentPage ;
    @FXML
    private Label P_Name ;
    @FXML
    private Label P_Email ;
    @FXML
    private Label P_UserType ;
    @FXML
    private Label P_Phone ;
    @FXML
    private Label P_Password ;
    @FXML
    private Label P_Password2 ;
    @FXML
    private Label P_Natinality ;
    @FXML
    private Label P_DiscountCode ;

    @FXML
    private Label  P_PocketMoney ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        P_PocketMoney.setText(User.getBalance() + "$");
        if (User.getUser_type() == 1) {
            P_UserType.setText("Vendor");
        } else {
            P_UserType.setText("Consumer");
        }
        goTo_PaymentPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = getStage();
                try {
                    new PaymentMenu(stage, false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
