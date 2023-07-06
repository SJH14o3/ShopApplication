package source.products;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import source.Global;
import source.User;
import source.application.Menu;
import source.threads.SendEmail;

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
                    Thread sendEmail = new SendEmail(Email, "Sign up was successful!", "Welcome to Shop Application, " + username + "!");
                    sendEmail.start();
                    Global.setUser_id(resultSet1.getInt(1));
                    String userType = resultSet1.getString(2).trim();
                    Global.setBalance(resultSet1.getDouble(3));
                    //System.out.println("initial Balance: " + Global.getBalance());
                    if (userType.equals("Consumer")) {
                        User.setUser_type(1);
                    }
                    else if(userType.equals("Vendor")){
                        User.setUser_type(2);
                    }
                    else if(userType.equals("Admin")){
                        User.setUser_type(3);
                    }
                }
                getUserInfo();
                Stage stage = Global.getStage();
                stage.setX(327);
                stage.setY(100);
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
            preparedStatement = connection.prepareStatement("SELECT Password , UserType,User_id, balance FROM users WHERE Username = ?");
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

                    //System.out.println("56 :" + retrivedPassword);

                    if(retrivedPassword.equals(Password)){
                        Stage stage = Global.getStage();
                        if (retrivedUserType.equals("Consumer")) {
                            User.setUser_type(1);
                        }
                        else if(retrivedUserType.equals("Vendor")){
                            User.setUser_type(2);
                        }
                        else if(retrivedUserType.equals("Admin")){
                            User.setUser_type(3);
                        }
                        Global.setUser_id(resultSet.getInt(3));
                        Global.setBalance(resultSet.getDouble(4));
                        //System.out.println("57 :" + User.getUser_type());
                        //System.out.println(Global.getBalance());
                        getUserInfo();
                        stage.setX(327);
                        stage.setY(100);
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
    private static String checkVendorCompany() {
        if (Global.getUser_type() == 2 && Global.getVendorCompany() != null) {
            return "\", vendor_company = \"" + Global.getVendorCompany();
        }
        return "";
    }
    public static void setCompleteUserInfo() {
        String SQL = "UPDATE users SET address = \"" + Global.getUser_address() + "\", first_name = \"" + Global.getFirstName() + "\", last_name = \"" +
            Global.getLastName() + "\", postal_code = \"" + Global.getUser_postalCode() + "\", phone_number = \"" + Global.getUser_phoneNumber() + checkVendorCompany() +
            "\" WHERE user_id = " + Global.getUser_id();
        try (Connection connection = DatabaseConnection.establishConnection("login_db2"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setExactUserInfo() {
        String SQL = "UPDATE users SET first_name = \"" + Global.getFirstName() + "\", last_name = \"" +
                Global.getLastName() + "\", phone_number = \"" + Global.getUser_phoneNumber() + checkVendorCompany() +
                "\" WHERE user_id = " + Global.getUser_id();
        try (Connection connection = DatabaseConnection.establishConnection("login_db2"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void changePassword() {
        String SQL = "UPDATE users SET Password = \"" + Global.getPassword() + "\" WHERE user_id = " + Global.getUser_id();
        try (Connection connection = DatabaseConnection.establishConnection("login_db2"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void changeEmail() {
        String SQL = "UPDATE users SET Email = \"" + Global.getEmail() + "\" WHERE user_id = " + Global.getUser_id();
        try (Connection connection = DatabaseConnection.establishConnection("login_db2"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getUserInfo() {
        String SQL = "SELECT Username, Email, Password, address, first_name, last_name, postal_code, phone_number, vendor_company FROM users WHERE User_id = " + Global.getUser_id();
        try (Connection connection = DatabaseConnection.establishConnection("login_db2"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Global.setUsername(resultSet.getString(1).trim());
            Global.setEmail(resultSet.getString(2).trim());
            Global.setPassword(resultSet.getString(3).trim());
            String address = resultSet.getString(4);
            if (address != null && !address.trim().equals("null")) {
                Global.setUser_address(address);
            }
            String first = resultSet.getString(5);
            if (first != null && !first.trim().equals("null")) {
                Global.setFirstName(first);
            }
            String last = resultSet.getString(6);
            if (last != null && !last.trim().equals("null")) {
                Global.setLastName(last);
            }
            String pc = resultSet.getString(7);
            if (pc != null && !pc.trim().equals("null")) {
                Global.setUser_postalCode(pc);
            }
            String phone = resultSet.getString(8);
            if (phone != null && !phone.trim().equals("null")) {
                Global.setUser_phoneNumber(phone);
            }
            String company =resultSet.getString(9);
            if (company != null && !company.trim().equals("null")) {
                Global.setVendorCompany(company);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
