package source.products;

public class DiscountCode {
    public String code;
    public double percentage;

    @Override
    public String toString() {
        return "Code: \"" + code + "\", percentage: " + percentage + "%";
    }
    public DiscountCode(String code, double percentage) {
        this.code = code;
        this.percentage = percentage;
    }
}
