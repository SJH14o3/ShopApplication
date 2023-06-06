package source.products;

public class Product {

    private int id;
    private String name;
    private String brand;
    private int quantity;
    private int price;
    String imageAddress;

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Product(int id, String name, String brand, int price, int quantity, String imageAddress) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageAddress = imageAddress;
    }
    public Product(String name, String brand, int price, int quantity, String imageAddress) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageAddress = imageAddress;
    }
}
