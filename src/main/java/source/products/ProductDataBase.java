package source.products;

import source.Global;
import source.application.ProductPage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ProductDataBase extends DatabaseConnection{

    public static void insertProduct(Product product) {
        String SQL = "INSERT INTO shop.products (name, brand, price, quantity, image_address, product_type, description) values (?, ?, ?, ?, ?, ?, ?);";
        int id;
        try (Connection connection = establishConnection("shop");
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getImageAddress());
            preparedStatement.setInt(6, product.getType());
            preparedStatement.setString(7, product.getDescription());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                        product.setId(id);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        insertStock(product.getId(), product.getQuantity(), false, 0);
    }
    private static void insertStock(int product_id, int quantity, boolean mandatoryWarehouse, int warehouseIn) {
        String SQL = "SELECT count(id) FROM warehouses";
        int count;
        int warehouse;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            if (!mandatoryWarehouse) {
                ResultSet resultSet;
                resultSet = statement.executeQuery(SQL);
                resultSet.next();
                count = resultSet.getInt(1);
                resultSet.close();
                int[] IDs = new int[count];
                SQL = "SELECT id FROM warehouses";
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < IDs.length; i++) {
                    resultSet.next();
                    IDs[i] = resultSet.getInt(1);
                    System.out.println(IDs[i]);
                }
                Random random = new Random();
                warehouse = IDs[random.nextInt(count)];
                resultSet.close();
            }
            else {
                warehouse = warehouseIn;
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            String now = dtf.format(LocalDateTime.now());
            SQL = "INSERT INTO products_in_stock (product_id, quantity, vendor_id, warehouse_id, date) VALUES (" + product_id + ", " + quantity + ", " + Global.getUser_id() + ", " + warehouse + ", " + now + ");";
            statement.execute(SQL);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateQuantity(int in) {
        Product product = getProduct(ProductPage.PRODUCT_ID);
        String SQL = "UPDATE products SET quantity = " + in + " WHERE product_id = " + product.getId() + ";";
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            statement.execute(SQL);
            SQL = "SELECT warehouse_id FROM products_in_stock WHERE product_id = " + product.getId() + " LIMIT 1";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            int warehouse = resultSet.getInt(1);
            int addedQuantity = in - product.getQuantity();
            insertStock(product.getId(), addedQuantity, true, warehouse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Product getProduct(int id) {
        //System.out.println(id);
        String SQL = "SELECT * FROM products WHERE product_id = " + id;
        Product result = null;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                result = new Product(id, resultSet.getString("name").trim(), resultSet.getString("brand").trim(), resultSet.getDouble("price"), resultSet.getInt("quantity"), resultSet.getString("image_address").trim(), resultSet.getDouble("score"), resultSet.getInt("vote_count"), resultSet.getInt("product_type"), resultSet.getString("description").trim());
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        //System.out.println(result);
        return result;
    }

    public static Product[] getSelectedProductsMainInfo(String extra) {
        String SQL = "SELECT count(product_id) FROM products" + " " + extra;
        //System.out.println(SQL);
        Product[] result;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                //System.out.println(count);
                result = new Product[count];
                //System.out.println(count);
                SQL = "SELECT product_id ,name, price, score, image_address, quantity FROM products" + " " + extra;
                //System.out.println(SQL);
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < count; i++) {
                    resultSet.next();
                    result[i] = new Product(resultSet.getInt("product_id"), resultSet.getString("name").trim(), null, resultSet.getDouble("price"), resultSet.getInt("quantity"), resultSet.getString("image_address").trim(), resultSet.getDouble("score"), 0, 0, null);
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
    public static Product[] getVendorProducts() {
        String SQL = "SELECT count(DISTINCT(p.product_id)) FROM products p JOIN products_in_stock s ON s.product_id = p.product_id WHERE s.vendor_id = "+ Global.getUser_id() + " ORDER BY p.product_id DESC;";
        Product[] result;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            //System.out.println(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                //System.out.println("count: " + count);
                result = new Product[count];
                SQL = "SELECT DISTINCT(p.product_id) ,name, price, score, image_address, p.quantity FROM products p JOIN products_in_stock s ON s.product_id = p.product_id WHERE s.vendor_id = " + Global.getUser_id() + " ORDER BY p.product_id DESC;";
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < count; i++) {
                    resultSet.next();
                    result[i] = new Product(resultSet.getInt(1), resultSet.getString(2).trim(), null, resultSet.getDouble(3), resultSet.getInt(6), resultSet.getString(5).trim(), resultSet.getDouble(4), 0, 0, null);
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

    public static String[] getUniqueBrands(String extra) {
        String SQL = "SELECT count(DISTINCT brand) FROM products " + extra;
        //System.out.println(SQL);
        String[] result;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                //System.out.println(count);
                result = new String[count];
                SQL = "SELECT DISTINCT brand FROM products " + extra + " ORDER BY brand";
                //System.out.println(SQL);
                resultSet = statement.executeQuery(SQL);
                for (int i = 0; i < count; i++) {
                    resultSet.next();
                    result[i] = resultSet.getString("brand").trim();
                    //System.out.println(result[i]);
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
    public static void decreaseQuantity(int product_id, int amount) {
        String SQL = "SELECT quantity FROM products WHERE product_id = " + product_id;
        try (Connection connection = establishConnection("shop"); Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SQL);
                resultSet.next();
                int quantity = resultSet.getInt(1);
                resultSet.close();
                quantity -= amount;
                SQL = "UPDATE products SET quantity = " + quantity +  " WHERE (`product_id` = " + product_id + ");";
                statement.execute(SQL);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}