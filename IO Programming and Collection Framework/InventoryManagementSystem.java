import java.io.*;
import java.util.*;

class Product {
    private String productId;
    private String name;
    private int quantity;
    private double price;

    public Product(String productId, String name, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return productId + ", " + name + ", " + quantity + ", " + price;
    }
}

class InventoryManager {
    private static Map<String, Product> inventory = new HashMap<>();
    private static final String INVENTORY_FILE = "inventory.txt";

    public static void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Product added: " + product.getName());
    }

    public static void updateProduct(String productId, int newQuantity) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.updateQuantity(newQuantity);
            System.out.println("Updated quantity of product: " + productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void removeProduct(String productId) {
        if (inventory.remove(productId) != null) {
            System.out.println("Product removed: " + productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void searchProduct(String productId) {
        Product product = inventory.get(productId);
        if (product != null) {
            System.out.println("Product found: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory List:");
            for (Product product : inventory.values()) {
                System.out.println(product);
            }
        }
    }

    public static void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INVENTORY_FILE))) {
            for (Product product : inventory.values()) {
                writer.write(product.toString());
                writer.newLine();
            }
            System.out.println("Inventory saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public static void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    Product product = new Product(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
                    inventory.put(parts[0], product);
                }
            }
            System.out.println("Inventory loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}

public class InventoryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        InventoryManager.loadInventory();

        while (true) {
            System.out.println("\n1. Add Product\n2. Update Product\n3. Remove Product\n4. Search Product\n5. View Inventory\n6. Save Inventory\n7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Product ID: ");
                String productId = scanner.nextLine();
                System.out.print("Enter Product Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Quantity: ");
                int quantity = scanner.nextInt();
                System.out.print("Enter Price: ");
                double price = scanner.nextDouble();
                Product product = new Product(productId, name, quantity, price);
                InventoryManager.addProduct(product);
            } else if (choice == 2) {
                System.out.print("Enter Product ID to update: ");
                String productId = scanner.nextLine();
                System.out.print("Enter New Quantity: ");
                int newQuantity = scanner.nextInt();
                InventoryManager.updateProduct(productId, newQuantity);
            } else if (choice == 3) {
                System.out.print("Enter Product ID to remove: ");
                String productId = scanner.nextLine();
                InventoryManager.removeProduct(productId);
            } else if (choice == 4) {
                System.out.print("Enter Product ID to search: ");
                String productId = scanner.nextLine();
                InventoryManager.searchProduct(productId);
            } else if (choice == 5) {
                InventoryManager.displayInventory();
            } else if (choice == 6) {
                InventoryManager.saveInventory();
            } else if (choice == 7) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
