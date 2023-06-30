package source.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import source.User;

public class PersonPageController {

    @FXML
    private Label P_Username ;
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
    private Label P_Natinality ;
    @FXML
    private Label P_DiscountCode ;

    @FXML
    private Label  P_PocketMoney ;




    public PersonPageController() {

        P_PocketMoney.setText(String.valueOf(User.getBalance()));
        if (User.getUser_type() == 1) {
            P_UserType.setText("Vendor");
        } else {
            P_UserType.setText("Consumer");
        }
    }

}
