package source.products;

import source.Global;
import source.exceptions.NoBidException;

import java.sql.*;

public class AuctionDataBase {
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", Global.PASSWORD);
    }
    public static Auction[] getAllAuctions() {
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
                    result[i] = new Auction(resultSet.getInt(1), resultSet.getString(2).trim(), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getString(6).trim());
                    //System.out.println(result[i] + "\n");
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
    public static double getHighestUserBid(int user_id, int auction_id) throws NoBidException {
        String SQL = "SELECT value from bids WHERE user_id = " + user_id + " AND auction_id = " + auction_id + " ORDER BY value DESC LIMIT 1";
        System.out.println(SQL);
        double result;
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                result = resultSet.getDouble(1);
                resultSet.close();
            }
            else {
                throw new NoBidException();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
    public static Auction getAuction(int id) {
        System.out.println(id);
        String SQL = "SELECT title, starting_bid, current_bid, dead_line, image_address FROM auctions WHERE id = " + id;
        System.out.println(SQL);
        Auction result = null;
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            //System.out.println(resultSet.getString("title").trim());
            result = new Auction(id, resultSet.getString(1).trim(), resultSet.getDouble(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getString(5).trim());
            resultSet.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        //System.out.println(result);
        return result;
    }
    public static synchronized void placeBid(int auction_id, double value, int user_id) {
        String SQL = "INSERT INTO bids (auction_id, value, user_id) VALUES (" + auction_id + ", " + value + ", " + user_id + ");";
        System.out.println(SQL);
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        SQL = "UPDATE `auctions` SET `current_bid` = " + value + " WHERE (`id` = " + auction_id + ");";
        try (Connection connection = connect(); PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        database.changeBalance(value * - 1, user_id);
    }
}
