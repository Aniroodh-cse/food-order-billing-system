package foodordersystem;

import java.util.Scanner;

public class FoodOrderBillingSystem {
    private static Customer loggedInCustomer = null;
    private static Order currentOrder = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Food Ordering Billing System");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Check Order");
            System.out.println("3. Add Item");
            System.out.println("4. Remove Item");
            System.out.println("5. Generate Bill");
            System.out.println("6. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    checkOrder();
                    break;
                case 3:
                    addItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    generateBill();
                    break;
                case 6:
                    logOut();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void login() {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (username.equals("john") && password.equals("password123")) {
            loggedInCustomer = new Customer(1, "John Doe", "123-456-7890");
            System.out.println("Login successful! Welcome " + loggedInCustomer.getName());

            
            currentOrder = new Order(1, loggedInCustomer);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    public static void checkOrder() {
        if (loggedInCustomer == null) {
            System.out.println("You must log in first.");
            return;
        }

        if (currentOrder == null || currentOrder.getFoodItems().isEmpty()) {
            System.out.println("Your order is empty. Add items before checking.");
            return;
        }

        System.out.println("\nCurrent Order for " + loggedInCustomer.getName() + ":");
        for (FoodItem item : currentOrder.getFoodItems()) {
            System.out.println("- " + item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Subtotal: $" + currentOrder.calculateSubtotal());
    }
    public static void addItem() {
        if (loggedInCustomer == null) {
            System.out.println("You must log in first.");
            return;
        }

        System.out.println("\nAvailable Items:");
        System.out.println("1. Spring Rolls - $5.99");
        System.out.println("2. Grilled Chicken - $12.99");
        System.out.println("3. Chocolate Cake - $4.50");
        System.out.print("Select item to add to order (1-3): ");
        int itemChoice = scanner.nextInt();
        scanner.nextLine(); 

        FoodItem itemToAdd = null;

        switch (itemChoice) {
            case 1:
                itemToAdd = new Appetizer(1, "Spring Rolls", 5.99);
                break;
            case 2:
                itemToAdd = new MainCourse(2, "Grilled Chicken", 12.99);
                break;
            case 3:
                itemToAdd = new Dessert(3, "Chocolate Cake", 4.50);
                break;
            default:
                System.out.println("Invalid selection.");
                return;
        }

        currentOrder.addItem(itemToAdd);
        System.out.println("Added " + itemToAdd.getName() + " to the order.");
    }

    public static void removeItem() {
        if (loggedInCustomer == null) {
            System.out.println("You must log in first.");
            return;
        }

        if (currentOrder == null || currentOrder.getFoodItems().isEmpty()) {
            System.out.println("Your order is empty. Add items before removing.");
            return;
        }

        System.out.println("\nCurrent Order:");
        for (int i = 0; i < currentOrder.getFoodItems().size(); i++) {
            System.out.println((i + 1) + ". " + currentOrder.getFoodItems().get(i).getName());
        }

        System.out.print("Enter item number to remove: ");
        int removeChoice = scanner.nextInt();
        scanner.nextLine(); 

        if (removeChoice < 1 || removeChoice > currentOrder.getFoodItems().size()) {
            System.out.println("Invalid selection.");
            return;
        }

        FoodItem itemToRemove = currentOrder.getFoodItems().get(removeChoice - 1);
        try {
            currentOrder.removeItem(itemToRemove);
            System.out.println("Removed " + itemToRemove.getName() + " from the order.");
        } catch (ItemNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void generateBill() {
        if (loggedInCustomer == null) {
            System.out.println("You must log in first.");
            return;
        }

        if (currentOrder == null || currentOrder.getFoodItems().isEmpty()) {
            System.out.println("Your order is empty. Add items before generating the bill.");
            return;
        }

        
        Bill bill = new Bill(1, currentOrder, 10.0, 5.0); 
        bill.createBill();  
        bill.printReceipt();  
    }

    

    public static void logOut() {
        if (loggedInCustomer != null) {
            System.out.println("Logging out... Goodbye " + loggedInCustomer.getName());
            loggedInCustomer = null;
            currentOrder = null;
        } else {
            System.out.println("No user is logged in.");
        }
    }
}
