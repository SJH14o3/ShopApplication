package source.products;

import source.Global;

import java.sql.*;

public class AuctionDataBase {
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", Global.PASSWORD);
    }
    public static Auction[] getAuctions() {
        String SQL = "SELECT count(id) FROM auctions";
        Auction[] result;
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                //System.out.println(count);
                result = new Auction[count];
                //System.out.println(count);
                SQL = "SELECT id ,title, starting_bid, current_bid, dead_line, image_address FROM auctions";
                System.out.println(SQL);
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < count; i++) {
                    resultSet.next();
                    result[i] = new Auction(resultSet.getInt("id"), resultSet.getString("title").trim(), resultSet.getDouble("starting_bid"), resultSet.getDouble("current_bid"), resultSet.getString("dead_line"), resultSet.getString("image_address").trim());
                    //System.out.println(result[i] + "\n");
                }
                resultSet.close();
                statement.close();
                connection.close();
                return result;
            }
            else {
                //TODO: no product exist.
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
