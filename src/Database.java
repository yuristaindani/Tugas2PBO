import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Database {
    private static final String DB_URL = "jdbc:sqlite:db_ecommerce.db";

    static {
        try {
            // Membuat tabel jika belum ada
            Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS products (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "price REAL NOT NULL" +
                    ");";
            statement.executeUpdate(createTableQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addProduct(Product product) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String insertQuery = "INSERT INTO products (name, price) VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int productId = generatedKeys.getInt(1);
                product.setId(productId);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String selectQuery = "SELECT * FROM products;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name, price);
                products.add(product);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    static Product getProductById(int id) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String selectQuery = "SELECT * FROM products WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name, price);
                resultSet.close();
                statement.close();
                connection.close();
                return product;
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void updateProduct(Product product) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String updateQuery = "UPDATE products SET name = ?, price = ? WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void removeProduct(Product product) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            String deleteQuery = "DELETE FROM products WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, product.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
