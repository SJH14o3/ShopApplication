package source.products;

import java.sql.*;

public class productDataBase {
    //TODO make sure connection between all contributors works properly which won't right now
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "6X0db8y3L&&J");
    }
    public static void insertProduct(Product product) {
        String SQL = "INSERT INTO shop.products (name, brand, price, quantity, image_address, product_type, description) values (?, ?, ?, ?, ?, ?, ?);";
        int id;
        try (Connection connection = connect();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getImageAddress());
            preparedStatement.setInt(6, product.getType());
            preparedStatement.setString(7, product.getDescription());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                        product.setId(id-1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Product getProduct(int id) {
        String SQL = "SELECT * FROM products WHERE product_id = " + id;
        Product result = null;
        try(Connection connection = connect(); Statement statement = connection.createStatement()) {
            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                int temp = resultSet.getInt("product_id");
                String name = resultSet.getString("name").trim();
                String brand = resultSet.getString("brand").trim();
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                String imageAddress = resultSet.getString("image_address").trim();
                double score = resultSet.getDouble("score");
                int voteCount = resultSet.getInt("vote_count");
                int type = resultSet.getInt("product_type");
                String description = resultSet.getString("description").trim();
                result = new Product(id, name, brand, price, quantity, imageAddress, score, voteCount, type, description);
                resultSet.close();
                statement.close();
                connection.close();
            }
            else {
                //TODO: product with this id doesn't exist
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
