package foodordersystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int customerId;
    private String username;
    private String password;
    private String name;
    private String phone;

    
    public Customer(int customerId, String username, String password, String name, String phone) {
        this.setCustomerId(customerId);
        this.setUsername(username);
        this.setPassword(password);
        this.setName(name);
        this.setPhone(phone);
    }

    

    public Customer(int i, String string, String string2) {
		
	}

	public static Customer login(String username, String password) {
        String query = "SELECT * FROM customers WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getInt("customer_id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("name"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  
    }

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		
		return 0;
	}
}
