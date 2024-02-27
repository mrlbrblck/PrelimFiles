public class Product {
    private String category;
    private String model;
    private double price;

    public Product(String category, String model, double price) {
        this.category = category;
        this.model = model;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }
}
