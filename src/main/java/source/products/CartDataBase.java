package source.products;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CartDataBase extends DatabaseConnection{
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
