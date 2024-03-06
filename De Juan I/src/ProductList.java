import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
        // Populate with sample products
        products.add(new Product("CPU", "AMD Ryzen 5 5600X", 11499.99, "R55600X.jpg")); 
        products.add(new Product("GPU", "NVIDIA GeForce RTX 3080", 16799.99, "RTX3080.jpg")); 
        products.add(new Product("RAM", "Corsair Vengeance LPX 16GB (2 x 8GB) DDR4-3200", 2499.99, "CRam16gb.jpg")); 
        products.add(new Product("SSD", "Samsung 970 EVO Plus 1TB NVMe M.2 SSD", 2699.99, "SamsungM2.jpg")); 
        products.add(new Product("HDD", "Seagate Barracuda 1TB 7200RPM HDD", 1999.99, "SeagateHDD1TB.jpg")); 
        products.add(new Product("Motherboard", "ASUS ROG Strix B550-F Gaming (Wi-Fi 6) ATX", 12299.99, "B550F.jpg")); 
        products.add(new Product("PSU", "EVGA SuperNOVA 750 G5, 80 Plus Gold 750W", 8999.99, "EVGApsu750W.jpg")); 
        products.add(new Product("CPU Cooler", "Cooler Master Hyper 212 RGB Black Edition", 4499.99, "CoolerMasterH212.jpg")); 
        products.add(new Product("Case", "NZXT H510 Compact ATX Mid-Tower Case", 9699.99, "NZXTH510case.jpg")); 
        products.add(new Product("Monitor", "ASUS TUF Gaming VG27AQ 27\" WQHD 165Hz IPS", 11399.99, "AsusTUF27inch.jpg")); 
    }

    public List<Product> getAllProducts() {
        return products;
    }

    // Method to add a new product
    public void addProduct(Product product) {
        products.add(product);
    }
}
