package source.products;

import source.Global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection establishConnection(String schema) throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + schema, "root", Global.PASSWORD);
    }
}
