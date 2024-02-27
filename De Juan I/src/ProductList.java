import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
        // Populate with sample products
        products.add(new Product("CPU", "AMD Ryzen 5 5600X", 11499.99)); // Updated price format
        products.add(new Product("GPU", "NVIDIA GeForce RTX 3080", 16799.99)); // Updated price format
        products.add(new Product("RAM", "Corsair Vengeance LPX 16GB (2 x 8GB) DDR4-3200", 2499.99)); // Updated price format
        products.add(new Product("SSD", "Samsung 970 EVO Plus 1TB NVMe M.2 SSD", 2699.99)); // Updated price format
        products.add(new Product("HDD", "Seagate Barracuda 2TB 7200RPM HDD", 1999.99)); // Updated price format
        products.add(new Product("Motherboard", "ASUS ROG Strix B550-F Gaming (Wi-Fi 6) ATX", 12299.99)); // Updated price format
        products.add(new Product("PSU", "EVGA SuperNOVA 750 G5, 80 Plus Gold 750W", 8999.99)); // Updated price format
        products.add(new Product("CPU Cooler", "Cooler Master Hyper 212 RGB Black Edition", 4499.99)); // Updated price format
        products.add(new Product("Case", "NZXT H510 Compact ATX Mid-Tower Case", 9699.99)); // Updated price format
        products.add(new Product("Monitor", "ASUS TUF Gaming VG27AQ 27\" WQHD 165Hz IPS", 11399.99)); // Updated price format
    }

    public List<Product> getAllProducts() {
        return products;
    }

    // Method to add a new product
    public void addProduct(Product product) {
        products.add(product);
    }
}
