import java.sql.*;

public class DbHelper {

    private final String url = "jdbc:mysql://localhost:3306/test";
    private final String user = "root";
    private final String password = "";
    private final Connection connection;

    public DbHelper() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connection established");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String email, String password) {
        String insertQuery = "INSERT INTO users (name, email, password) VALUES(?,?,?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(insertQuery);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, email);
            preparedStmt.setString(3, password);
            preparedStmt.executeUpdate();
            System.out.println("✅ Data saved");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet fetchAllData() {
        String fetchQuery = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(fetchQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("✅ Deleted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String name, String id) {
        String query = "UPDATE users SET name = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, id);
            stmt.executeUpdate();
            System.out.println("✅ Updated successfully.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
