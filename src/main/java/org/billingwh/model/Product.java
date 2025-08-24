package org.billingwh.model;

public class Product {
    private String productId;
    private String name;
    private String type;
    private double price; // Original Price
    private double sellingPrice; // Selling Price

    public Product(String productId, String name, String type, double price, double sellingPrice) {
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.price = price;
        this.sellingPrice = sellingPrice;
    }

    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }

    // New method to calculate the discount percentage
    public double getDiscountPercentage() {
        if (price > sellingPrice && price > 0) {
            return (price - sellingPrice) / price;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Type: " + type + ", Price: $" + String.format("%.2f", price) + ", Selling Price: ₹" + String.format("%.2f", sellingPrice);
    }
}