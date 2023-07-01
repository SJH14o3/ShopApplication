package source.notifications;

import source.products.DatabaseConnection;

import java.sql.*;

public class NotificationDataBase extends DatabaseConnection {
    public static Notification[] getAll(int user_id) {
        String SQL = "SELECT count(id) FROM notifications WHERE user_id = " + user_id;
        //System.out.println(SQL);
        Notification[] result;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                result = new Notification[count];
                if (count == 0) return result;
                SQL = "SELECT type ,sub_id FROM notifications WHERE user_id = " + user_id;
                //System.out.println(SQL);
                resultSet = statement.executeQuery(SQL);
                System.out.println(count);
                for (int i = 0; i < count; i++) {
                    System.out.println("i: " + i);
                    resultSet.next();
                    int subID = resultSet.getInt(2);
                    String SQL2;
                    switch (resultSet.getInt(1)) {
                        case 1 -> {
                            SQL2 = "SELECT a.title, a.id, n.id FROM auctions a JOIN nobid_notification n ON n.auction_id = a.id WHERE n.id = " + subID;
                            System.out.println(SQL2);
                            ResultSet resultSet1 = statement.executeQuery(SQL2);
                            resultSet1.next();
                            result[i] = new NoBidNotification(resultSet1.getString(1).trim(), resultSet1.getInt(2), resultSet1.getInt(3));
                        }
                        case 2 -> {
                            SQL2 = "SELECT a.title, a.id, n.id FROM auctions a JOIN winner_notification n ON n.auction_id = a.id WHERE n.id = " + subID;
                            System.out.println(SQL2);
                            ResultSet resultSet1 = statement.executeQuery(SQL2);
                            resultSet1.next();
                            result[i] = new AuctionWinnerNotification(resultSet1.getString(1).trim(), resultSet1.getInt(3));
                        }
                    }
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
    public static void insertNotification(int type, int sub_id, int user_id) {
        String SQL = "INSERT INTO notifications (type, sub_id, user_id) VALUES ("+ type + ", " + sub_id + ", " + user_id + ");";
        System.out.println(SQL);
        try (Connection connection = establishConnection("shop"); PreparedStatement st = connection.prepareStatement(SQL)) {
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private static void duplicate(String SQL, int user_id, int type) {
        try (Connection connection = establishConnection("shop"); PreparedStatement st = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    insertNotification(type, id, user_id);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void insertAuctionWinnerNotification(int auction_id, int user_id) {
        String SQL = "INSERT INTO winner_notification (auction_id) VALUES ("+ auction_id +");";
        duplicate(SQL, user_id, 2);
    }
    public static void insertNoBidNotification(int auction_id, int user_id) {
        String SQL = "INSERT INTO nobid_notification (auction_id) VALUES ("+ auction_id +");";
        duplicate(SQL, user_id, 1);
    }
    public static void deleteNotification(String SQL) {
        try (Connection connection = establishConnection("shop"); PreparedStatement st = connection.prepareStatement(SQL)) {
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteNoBidNotification(int id) {
        String SQL = "DELETE FROM nobid_notification WHERE id = " + id + " LIMIT 1;";
        try (Connection connection = establishConnection("shop"); PreparedStatement st = connection.prepareStatement(SQL)) {
            st.execute();
            deleteNotification("DELETE FROM notifications WHERE sub_id = " + id + " AND type = 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteAuctionWinnerNotification(int id) {
        String SQL = "DELETE FROM winner_notification WHERE id = " + id + " LIMIT 1;";
        try (Connection connection = establishConnection("shop"); PreparedStatement st = connection.prepareStatement(SQL)) {
            st.execute();
            deleteNotification("DELETE FROM notifications WHERE sub_id = " + id + " AND type = 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
