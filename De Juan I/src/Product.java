public class Product {
    private String type;
    private String name;
    private double price;
    private String imagePath; // Path to the image for the product

    public Product(String type, String name, double price, String imagePath) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }
}
