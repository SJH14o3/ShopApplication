package source.products;

public class Comment {
    private final int userID;
    private final int productID;
    private final String userName;
    private final String text;
    private final boolean buyer;

    public Comment(int userID, int productID, String userName, String text, boolean buyer) {
        this.userID = userID;
        this.productID = productID;
        this.userName = userName;
        this.text = text;
        this.buyer = buyer;
    }

    public int getUserID() {
        return userID;
    }

    public int getProductID() {
        return productID;
    }

    public String getText() {
        return text;
    }

    public String getUserName() {
        return userName;
    }
    public boolean isBuyer() {
        return buyer;
    }
}
