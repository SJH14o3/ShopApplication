package source.database;

import source.Global;
import source.products.DatabaseConnection;

import java.sql.*;

public class DiscountCodeDataBase extends DatabaseConnection {
    public static boolean checkCode(String code) {
        String SQL = "SELECT count(code_id) FROM discount_codes WHERE user_id = " + Global.getUser_id() + " AND code = " + code;
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
