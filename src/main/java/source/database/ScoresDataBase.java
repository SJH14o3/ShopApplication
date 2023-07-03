package source.database;

import source.Global;
import source.application.ProductPage;
import source.products.DatabaseConnection;

import java.sql.*;

public class ScoresDataBase extends DatabaseConnection {
    public static void insertScore(int score, double newScore, int voteCount) {
        updateProductScore(newScore, voteCount);
        String SQL = "INSERT INTO scores (user_id, product_id, value) VALUES (" + Global.getUser_id() + ", " + ProductPage.PRODUCT_ID + ", " + score + ");";
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void updateProductScore(double newScore, int voteCount) {
        String SQL = "UPDATE products SET score = " + newScore + ", vote_count = " + voteCount + " WHERE product_id = " + ProductPage.PRODUCT_ID + ";";
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean checkUserHasRatedAlready() {
        String SQL = "SELECT user_id FROM scores WHERE user_id = " + Global.getUser_id() + " AND product_id = " + ProductPage.PRODUCT_ID;
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
