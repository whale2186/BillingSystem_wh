package org.billingwh.dao;

import org.billingwh.db.DatabaseManager;
import org.billingwh.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void insertProduct(Product product) {
        String sql = "INSERT INTO products (product_id, name, type, price, selling_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getProductId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getType());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setDouble(5, product.getSellingPrice());
            pstmt.executeUpdate();
            System.out.println("Product inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting product: " + e.getMessage());
        }
    }

    public List<Product> viewAllProducts() {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("product_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getDouble("selling_price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing products: " + e.getMessage());
        }
        return products;
    }

    public void deleteProduct(String productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with ID: " + productId);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }

    public Product findProductById(String productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getString("product_id"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getDouble("price"),
                            rs.getDouble("selling_price")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding product: " + e.getMessage());
        }
        return null;
    }
}