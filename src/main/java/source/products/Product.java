package source.products;

public class Product {

    private int id;
    private String name;
    private String brand;
    private int quantity;
    private int price;
    private int type;
    private String imageAddress;
    private String description;
    private double score;
    private int voteCount;

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

    public double getScore() {
        return score;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void recalculateScore(int newScore) {
        int totalScore = (int) (score * voteCount);
        totalScore += newScore;
        voteCount++;
        score = (totalScore / voteCount);
        //TODO: implement the code to update the score in database
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
