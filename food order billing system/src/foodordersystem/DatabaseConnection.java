package foodordersystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/food_order_system";
    private static final String USER = "root";  
    private static final String PASSWORD = "ani@13";  

    // Method to establish connection to the database
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }public static void insertCustomerData(Customer customer) {
        String query = "INSERT INTO customers (id, name, phone) VALUES (?, ?, ?)";
        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getPhone());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer data inserted successfully.");
            } else {
                System.out.println("Failed to insert customer data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	private static Connection connectToDatabase() {
		
		return null;
	}

}
