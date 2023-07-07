package source.products;

public class ArchiveItem extends CartItem{
    private final String date;
    public String getDate() {
        return date;
    }
    public ArchiveItem(Product product, int ID, int quantity, String date) {
        super(product, ID, quantity);
        this.date = date;
    }
}
