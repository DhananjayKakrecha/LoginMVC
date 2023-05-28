import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    
    public LoginDAO() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Create a connection to the database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean validate(Login login) {
        boolean isValid = false;
        try {
            // Prepare the SQL query
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();
            // Check if a record was found
            isValid = resultSet.next();
            // Close the result set
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the prepared statement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isValid;
    }
}
