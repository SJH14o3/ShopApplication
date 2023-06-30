package source.products;

public class CartItem {
    public final Product product;
    public final int ID;
    public int quantity;
    public CartItem(Product product, int ID, int quantity) {
        this.product = product;
        this.ID = ID;
        this.quantity = quantity;
    }
}
