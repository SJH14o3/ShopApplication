package source.products;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class database {


    public static void changeScene(ActionEvent event , String fxmlFile , String title , String username , String usertype){

        Parent root = null;

        if(username != null && usertype !=null){
            try {
                FXMLLoader loader = new FXMLLoader(database.class.getResource(fxmlFile));
                root = loader.load();
                //WellComeController wellComeController = loader.getController();
               // wellComeController.setInformation(username , usertype);
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            try {
                root= FXMLLoader.load(database.class.getResource(fxmlFile));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);

        stage.setScene(new Scene(root , 776 , 448));
        stage.show();
    }
    public static void SignUpUser(ActionEvent event, String username , String Email , String password , String usertype ){
        Connection connection = null ;
        PreparedStatement PsInsert = null ;
        PreparedStatement PsCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_db2" , "root" , "12345678");
            PsCheckUserExists = connection.prepareStatement("SELECT * FROM users  WHERE username = ?");
            PsCheckUserExists.setString(1,username);
            resultSet = PsCheckUserExists.executeQuery();
            if(resultSet.isBeforeFirst()){
                System.out.println("This Username Is Already Exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This Username Is Already Exist!");
                alert.show();
            }else {
                PsInsert = connection.prepareStatement("INSERT INTO users (Username , Email , Password , UserType) VALUES (?,?,?,?)");
                PsInsert.setString(1,username);
                PsInsert.setString(2,Email);
                PsInsert.setString(3,password);
                PsInsert.setString(4,usertype);
                PsInsert.executeUpdate();

                changeScene(event , "Wellcome.fxml" , "Wellcome" , username , usertype);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(PsCheckUserExists != null){
                try {
                    PsCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(PsInsert != null){
                try {
                    PsInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        }

    }
    public static void LoginUser(ActionEvent event, String Username , String Password ){
        Connection connection = null;
        PreparedStatement preparedStatement =null ;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_db2" , "root" , "12345678");
            preparedStatement = connection.prepareStatement("SELECT Password , UserType FROM users WHERE Username = ?");
            preparedStatement.setString(1 , Username);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("User Not Found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User Not Found");
                alert.show();
            }else {
                while (resultSet.next()){
                    String retrivedPassword = resultSet.getString("Password");
                    String retrivedUserType = resultSet.getString("UserType");
                    if(retrivedPassword.equals(Password)){
                        changeScene(event , "menu.fxml" , "Wellcome!" , Username , retrivedUserType);
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Password is incorect");
                        alert.show();
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }


    }

}
