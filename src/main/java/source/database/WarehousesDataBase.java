package source.database;

import source.products.DatabaseConnection;

import java.sql.*;

public class WarehousesDataBase extends DatabaseConnection {
    public static Warehouse[] getAll() {
        String SQL = "SELECT COUNT(id) FROM warehouses";
        try (Connection connection = establishConnection("Shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            Warehouse[] ans = new Warehouse[count];
            SQL = "SELECT id, name, address, manager FROM warehouses";
            resultSet = preparedStatement.executeQuery(SQL);
            for (int i = 0; i < ans.length; i++) {
                resultSet.next();
                ans[i] = new Warehouse(resultSet.getString(2).trim(), resultSet.getString(4).trim(), resultSet.getString(3).trim(), resultSet.getInt(1));
            }
            resultSet.close();
            return ans;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Warehouse get(int id) {
        String SQL = "SELECT name, address, manager FROM warehouses WHERE id = " + id;
        System.out.println(SQL);
        try (Connection connection = establishConnection("Shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Warehouse(resultSet.getString(1).trim(), resultSet.getString(3).trim(), resultSet.getString(2).trim(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(int id) {
        String SQL = "DELETE FROM warehouses WHERE id = " + id;
        try (Connection connection = establishConnection("Shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void modify(String name, String address, String manager, int id) {
        String SQL = "UPDATE warehouses SET name = \"" + name + "\", address = \"" + address + "\", manager = \"" + manager + "\" WHERE id = " + id;
        try (Connection connection = establishConnection("Shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void add(String name, String address, String manager) {
        String SQL = "INSERT INTO warehouses (name, address, manager) VALUES (\"" + name + "\", \"" + address + "\", \"" + manager + "\")";
        try (Connection connection = establishConnection("Shop"); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int[] getCategoriesCount(int warehouse_id) {
        int[] counts = {0,0,0,0,0,0,0,0,0,0};
        ResultSet resultSet = null;
        try (Connection connection = establishConnection("Shop"); Statement statement = connection.createStatement()) {
            for (int i = 1; i < 11; i++) {
                String SQL = "SELECT DISTINCT(a.product_id), a.quantity FROM products a JOIN products_in_stock b ON a.product_id = b.product_id WHERE b.warehouse_id = " + warehouse_id + " AND a.product_type = " + i;
                resultSet = statement.executeQuery(SQL);
                while (resultSet.next()) {
                    counts[i-1] += resultSet.getInt(2);
                }
            }
            resultSet.close();
            System.out.println(counts[2]);
            return counts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
