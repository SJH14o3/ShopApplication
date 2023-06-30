package source.products;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import source.Global;
import source.application.Menu;

import java.io.IOException;
import java.sql.*;

public class database {


    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String usertype){
        Parent root = null;
        try {
            root = FXMLLoader.load(database.class.getResource("menu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = Global.getStage();
        /*if(username != null && usertype !=null){
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
        }*/
        stage.setTitle(title);

        stage.setScene(new Scene(root , 776 , 448));
        stage.show();
    }
    public static void SignUpUser(ActionEvent event, String username, String Email, String password, String usertype){
        Connection connection = null ;
        PreparedStatement PsInsert = null ;
        PreparedStatement PsCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_db2" , "root" , Global.PASSWORD);
            PsCheckUserExists = connection.prepareStatement("SELECT * FROM users  WHERE username = ?");
            PsCheckUserExists.setString(1,username);
            resultSet = PsCheckUserExists.executeQuery();
            if(resultSet.isBeforeFirst()){
                System.out.println("This Username Is Already Exist!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This Username Is Already Exist!");
                alert.show();
            }else {
                PsInsert = connection.prepareStatement("INSERT INTO users (Username , Email , Password , UserType, balance) VALUES (?,?,?,?, 0.00)");
                PsInsert.setString(1,username);
                PsInsert.setString(2,Email);
                PsInsert.setString(3,password);
                PsInsert.setString(4,usertype);
                PsInsert.executeUpdate();
                Statement statement = connection.createStatement();
                ResultSet resultSet1 = statement.executeQuery("SELECT user_id, UserType, balance FROM users WHERE Username = \"" + username +"\"");
                if (resultSet1.next()) {
                    Global.setUser_id(resultSet1.getInt(1));
                    String userType = resultSet1.getString(2).trim();
                    Global.setBalance(resultSet1.getDouble(3));
                    //System.out.println("initial Balance: " + Global.getBalance());
                    if (userType.equals("Consumer")) {
                        Global.setUser_type(1);
                    }
                    else {
                        Global.setUser_type(2);
                    }
                    //System.out.println("USER ID: " + Global.getUser_id());
                    //System.out.println("Type: " + Global.getUser_type());
                }

                Stage stage = Global.getStage();
                try {
                    new Menu(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
    public static void LoginUser(ActionEvent event, String Username, String Password){
        Connection connection = null;
        PreparedStatement preparedStatement =null ;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_db2" , "root" , Global.PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT Password , UserType, User_id, balance FROM users WHERE Username = ?");
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
                        Stage stage = Global.getStage();
                        if (retrivedUserType.equals("Consumer")) {
                            Global.setUser_type(1);
                        }
                        else {
                            Global.setUser_type(2);
                        }
                        Global.setUser_id(resultSet.getInt(3));
                        Global.setBalance(resultSet.getDouble(4));
                        System.out.println(Global.getBalance());
                        try {
                            new Menu(stage);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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
    public static String getUsername(int user_id) {
        String SQL = "SELECT username FROM users WHERE user_id = " + user_id + " LIMIT 1";
        String result = null;
        try (Connection connection = DatabaseConnection.establishConnection("login_db2"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                result = resultSet.getString(1).trim();
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
    public static synchronized void changeBalance(double change, int user_id) {
        double finalBalance = Global.getBalance() + change;
        finalBalance = Double.parseDouble(String.format("%.2f", finalBalance));
/*
        System.out.println("G: " + Global.getBalance());
        System.out.println("C: " + change);
        System.out.println("F: " + finalBalance);
*/
        Global.setBalance(finalBalance);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE `users` SET `Balance` = " + Global.getBalance() + " WHERE (`User_id` = " + user_id + ");";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_db2" , "root" , Global.PASSWORD);
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Balance changed: " + affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
