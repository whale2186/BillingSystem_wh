package org.billingwh.app;

import org.billingwh.dao.ProductDAO;
import org.billingwh.model.Product;

import java.util.List;
import java.util.Scanner;

public class BillingApp {

    private static final ProductDAO productDAO = new ProductDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dashboard();
    }

    public static void dashboard() {
        while (true) {
            System.out.println("\n--- Dashboard ---");
            System.out.println("1. Insert Product");
            System.out.println("2. View All Products");
            System.out.println("3. Delete Product");
            System.out.println("4. Billing Page");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    insertProductPage();
                    break;
                case "2":
                    viewProductsPage();
                    break;
                case "3":
                    deleteProductPage();
                    break;
                case "4":
                    billingPage();
                    break;
                case "5":
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void insertProductPage() {
        System.out.println("\n--- Insert Product ---");
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Product Selling Price: ");
        double sellingPrice = Double.parseDouble(scanner.nextLine());

        Product newProduct = new Product(id, name, type, price, sellingPrice);
        productDAO.insertProduct(newProduct);
    }

    private static void viewProductsPage() {
        System.out.println("\n--- View All Products ---");
        List<Product> products = productDAO.viewAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void deleteProductPage() {
        System.out.println("\n--- Delete Product ---");
        System.out.print("Enter Product ID to delete: ");
        String id = scanner.nextLine();
        productDAO.deleteProduct(id);
    }

    private static void billingPage() {
        System.out.println("\n--- Billing Page ---");
        while (true) {
            System.out.print("Enter Product ID (or 'done' to finish): ");
            String productId = scanner.nextLine();
            if (productId.equalsIgnoreCase("done")) {
                break;
            }

            Product product = productDAO.findProductById(productId);
            if (product != null) {
                System.out.println("Found product: " + product.getName());
                System.out.println("Original Price: $" + String.format("%.2f", product.getPrice()));
                System.out.println("Selling Price: $" + String.format("%.2f", product.getSellingPrice()));
                System.out.println("Discount: " + String.format("%.2f", product.getDiscountPercentage() * 100) + "%");
            } else {
                System.out.println("Product not found. Please try again.");
            }
        }
        System.out.println("Returning to dashboard.");
    }
}