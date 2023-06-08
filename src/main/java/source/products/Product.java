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
    //it might not be useful, just in case we needed it
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product temp) {
            return temp.id == this.id;
        }
        return false;
    }
    //only for testing purposes
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Product ID: ").append(id).append("\nName: ").append(name).append("\nBrand: ").append(brand).append("\nPrice: ").append(price);
        stringBuilder.append("\nQuantity: ").append(quantity).append("\nImage Address: ").append(imageAddress).append("\nScore: ").append(score);
        stringBuilder.append("\nVote Count: ").append(voteCount).append("\nType: ").append(type).append("\nDescription: ").append(description);
        //TODO stringBuilder says what type of product we have in its name not in its ID.
        return stringBuilder.toString();
    }

    public Product(int id, String name, String brand, int price, int quantity, String imageAddress, double score, int voteCount, int type, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageAddress = imageAddress;
        this.score = score;
        this.type = type;
        this.description = description;
        this.voteCount = voteCount;
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
