package foodordersystem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<FoodItem> foodItems;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.foodItems = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        foodItems.add(item);
        String url = "jdbc:mysql://localhost:3306/food_order_system";
        String uname = "root";
        String password = "ani@13";
        try 
        {
        	String query = "insert into fooditem (name,type,price) values(?,?,?)";
        	Connection conn = DriverManager.getConnection(url,uname,password);
        	PreparedStatement pst = conn.prepareStatement(query);
        	pst.setString(1, item.getName());
        	pst.setString(2, "Appetizer");
        	pst.setDouble(3,item.getPrice());
        	int rows = pst.executeUpdate();
        	System.out.println("Rows affection"+rows);
        }catch(Exception e)
        {
        	e.printStackTrace();        }
        
    }

    public void removeItem(FoodItem item) throws ItemNotFoundException {
        if (!foodItems.remove(item)) {
            throw new ItemNotFoundException("Item not found in the order.");
        }
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public double calculateSubtotal() {
        double subtotal = 0;
        for (FoodItem item : foodItems) {
            subtotal += item.getPrice();
        }
        return subtotal;
    }


    public int getOrderId() {
        return orderId;
    }
}
