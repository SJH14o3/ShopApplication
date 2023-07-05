package source.database;

import source.Global;
import source.products.DatabaseConnection;
import source.products.DiscountCode;

import java.sql.*;

public class DiscountCodeDataBase extends DatabaseConnection {
    public static double checkCode(String code) {
        String SQL = "SELECT discount_percentage FROM discount_codes WHERE user_id = " + Global.getUser_id() + " AND code = " + code;
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                return resultSet.getDouble(1);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public static DiscountCode[] getAllUserDiscountCodes() {
        String SQL = "SELECT count(code_id) FROM discount_codes WHERE user_id = " + Global.getUser_id();
        DiscountCode[] result = new DiscountCode[0];
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            result = new DiscountCode[count];
            SQL = "SELECT code, discount_percentage FROM discount_codes WHERE user_id = " + Global.getUser_id();
            resultSet = preparedStatement.executeQuery(SQL);
            for (int i = 0; i < count; i++) {
                resultSet.next();
                result[i] = new DiscountCode(resultSet.getString(1).trim(), resultSet.getDouble(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
