package source.products;

import java.sql.*;

public class CommentDataBase extends DatabaseConnection{
    public static void InsertComment(Comment comment) {
        String SQL = "INSERT INTO comments (user_id, product_id, buyer, comment) VALUES (" + comment.getUserID() + ", " + comment.getProductID() + ", " + comment.isBuyer() + ", " + "\" "+ comment.getText() +" \");";
        //System.out.println(SQL);
        try (Connection connection = establishConnection("shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Comment[] getProductComments(int productID) {
        String SQL = "SELECT count(comment_id) FROM comments WHERE product_id = " + productID;
        //System.out.println(SQL);
        Comment[] result = null;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                //System.out.println(count);
                result = new Comment[count];
                //System.out.println(count);
                SQL = "SELECT user_id, product_id, comment, buyer FROM comments WHERE product_id = " + productID;
                //System.out.println(SQL);
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < count; i++) {
                    resultSet.next();
                    int id = resultSet.getInt(1);
                    String username = database.getUsername(id);
                    result[i] = new Comment(id, resultSet.getInt(2), username, resultSet.getString(3).trim(), resultSet.getBoolean(4));
                }
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
