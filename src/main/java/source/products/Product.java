package source.products;

public class Product {

    private int id;
    private String name;
    private String brand;
    private int quantity;
    private int price;
    private int type;
    String imageAddress;
    private String description;


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

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Product(int id, String name, String brand, int price, int quantity, String imageAddress,int type,String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageAddress = imageAddress;
        this.type = type;
        this.description = description;
    }
    public Product(String name, String brand, int price, int quantity, String imageAddress,int type, String description) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageAddress = imageAddress;
        this.type = type;
        this.description= description;
    }
}
