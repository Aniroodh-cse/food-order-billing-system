package foodordersystem;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Bill {
    private int billId;
    private Order order;
    private double taxPercentage;
    private double discountPercentage;

    public Bill(int billId, Order order, double taxPercentage, double discountPercentage) {
        this.billId = billId;
        this.order = order;
        this.taxPercentage = taxPercentage;
        this.discountPercentage = discountPercentage;
    }

    public void createBill() {
        
        if (order == null) {
            System.out.println("Error: No order found.");
            return;
        }

        
        System.out.println("Bill created with ID: " + billId);
    }

    public void printReceipt() {
        
        if (order == null || order.getFoodItems().isEmpty()) {
            System.out.println("No items in the order.");
            return;
        }

        System.out.println("\nBill Details: ");
        System.out.println("Subtotal: $" + order.calculateSubtotal());
        System.out.println("Tax: " + taxPercentage + "%");
        System.out.println("Discount: " + discountPercentage + "%");

        double subtotal = order.calculateSubtotal();
        double taxAmount = (taxPercentage / 100) * subtotal;
        double discountAmount = (discountPercentage / 100) * subtotal;
        double total = subtotal + taxAmount - discountAmount;
        LocalDate currentDate = LocalDate.now();
        
        String url = "jdbc:mysql://localhost:3306/food_order_system";
        String uname = "root";
        String password = "ani@13";
        try 
        {
        	if (order.getOrderId() == 0) {
                System.out.println("Error: Invalid order ID.");
                return; 
            }
        	String query = "insert into billS (total_amount,tax,discount,final_amount,date) values(?,?,?,?,?)";
        	Connection conn = DriverManager.getConnection(url,uname,password);
        	PreparedStatement pst = conn.prepareStatement(query);
        	pst.setDouble(1, subtotal);
        	pst.setDouble(2,taxAmount);
        	pst.setDouble(3,discountAmount);
        	pst.setDouble(4,total);
        	pst.setDate(5, Date.valueOf(currentDate));
        	
        	int rows = pst.executeUpdate();
        	System.out.println("Rows affected:"+rows);
        }catch(Exception e)
        {
        	e.getMessage();        
        }

        System.out.println("Total: $" + total);
    }
}
