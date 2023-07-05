package source.products;

import javafx.scene.control.Alert;
import source.Global;
import source.application.ProductPage;

import java.sql.*;

public class CartDataBase extends DatabaseConnection{
    public static boolean increaseQuantity(int id, int currentCount, int productQuantity) {
        currentCount++;
        if (currentCount > productQuantity) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("The amount of requested item has passed the available item's quantity in stock");
            alert.setContentText("Your request cannot be done");
            alert.showAndWait();
            return false;
        }
        String SQL = "UPDATE carts SET quantity = " + currentCount + " WHERE cart_id = " + id + ";";
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
    public static boolean decreaseQuantity(int id, int currentCount) {
        currentCount--;
        if(currentCount==0){
            return false;
        }
        String SQL = "UPDATE carts SET quantity = " + currentCount + " WHERE cart_id = " + id + ";";
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
    public static void removeCartItem(int id) {
        String SQL = "DELETE FROM carts WHERE cart_id = " + id + ";";
        try (Connection connection = establishConnection("shop"); PreparedStatement st = connection.prepareStatement(SQL)) {
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean doesUserHaveThisItemInTheirCart() {
        String SQL = "SELECT user_id FROM carts WHERE user_id = " + Global.getUser_id() + " AND product_id = " + ProductPage.PRODUCT_ID;
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                return true;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public static void insertCart(int quantity) {
        String SQL = "INSERT INTO carts (user_id, product_id, quantity) VALUES (" + Global.getUser_id() + ", " + ProductPage.PRODUCT_ID + ", " + quantity + ");";
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static CartItem[] getCartItems(int user_id) {
        String SQL = "SELECT count(cart_id) FROM carts WHERE user_id = " + user_id;
        //System.out.println(SQL);
        CartItem[] result;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                result = new CartItem[count];
                SQL = "SELECT product_id ,quantity, cart_id FROM carts WHERE user_id = " + user_id;
                //System.out.println(SQL);
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < count; i++) {
                    resultSet.next();
                    Product product = ProductDataBase.getProduct(resultSet.getInt(1));
                    result[i] = new CartItem(product, resultSet.getInt(3), resultSet.getInt(2));
                }
                resultSet.close();
                statement.close();
                connection.close();
                return result;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
