public class Voucher {
    private String name;
    private double discount;
    private int maxUse;

    public Voucher(String name, double discount, int maxUse) {
        this.name = name;
        this.discount = discount;
        this.maxUse = maxUse;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public int getMaxUse() {
        return maxUse;
    }
}
