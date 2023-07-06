package source.database;

import source.Global;
import source.application.ProductPage;
import source.products.*;

import java.sql.*;

public class PurchaseArchiveDataBase extends DatabaseConnection {
    public static boolean checkIfBuyer() {
        String SQL = "SELECT archive_id FROM purchases_archive WHERE user_id = " + Global.getUser_id() + " AND product_id = " + ProductPage.PRODUCT_ID + " LIMIT 1";
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
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
    public static ArchiveItem[] getAllArchives(int user_id) {
        String SQL = "SELECT count(archive_id) FROM purchases_archive WHERE user_id = " + user_id;
        //System.out.println(SQL);
        ArchiveItem[] result;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                result = new ArchiveItem[count];
                SQL = "SELECT product_id ,quantity, archive_id, date FROM purchases_archive WHERE user_id = " + user_id;
                //System.out.println(SQL);
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < count; i++) {
                    resultSet.next();
                    Product product = ProductDataBase.getProduct(resultSet.getInt(1));
                    result[i] = new ArchiveItem(product, resultSet.getInt(3), resultSet.getInt(2), resultSet.getString(4).trim());
                }
                resultSet.close();
                return result;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
