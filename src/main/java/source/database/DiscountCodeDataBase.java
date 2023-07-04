package source.database;

import source.Global;
import source.products.DatabaseConnection;

import java.sql.*;

public class DiscountCodeDataBase extends DatabaseConnection {
    public static double checkCode(String code) {
        String SQL = "SELECT discount_percentage FROM discount_codes WHERE user_id = " + Global.getUser_id() + " AND code = " + code;
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
