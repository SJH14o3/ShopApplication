package source.products;

import source.database.ScoresDataBase;

public class Product {

    private int id;
    private final String name;
    private final String brand;
    private final int quantity;
    private final double price;
    private final int type;
    private final String imageAddress;
    private final String description;
    private double score;
    private int voteCount;

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
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
        score = (double) totalScore / voteCount;
        ScoresDataBase.insertScore(newScore, score, voteCount);
    }
    //it might not be useful, just in case we needed it
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product temp) {
            return temp.id == this.id;
        }
        return false;
    }
    public String typeToString() {
        switch (type){
            case 1 -> {
                return "Vegetables";
            }
            case 2 -> {
                return "Fruits";
            }
            case 3 -> {
                return "Dried Fruits";
            }
            case 4 -> {
                return "Proteins";
            }
            case 5 -> {
                return "Sweets";
            }
            case 6 -> {
                return "Groceries";
            }
            case 7 -> {
                return "Dairy";
            }
            case 8 -> {
                return "Beverages";
            }
            case 9 -> {
                return "Snacks";
            }
            case 10 -> {
                return "Breakfast";
            }
            default -> {
            }
        }
        return "";
    }
    public static int stringToType(String in) {
        switch (in) {
            case "Vegetables" -> {
                return 1;
            }
            case "Fruits" -> {
                return 2;
            }
            case "Dried Fruits"-> {
                return 3;
            }
            case "Proteins"-> {
                return 4;
            }
            case "Sweets" -> {
                return 5;
            }
            case "Pantry" -> {
                return 6;
            }
            case "Dairy" -> {
                return 7;
            }
            case "Beverages" -> {
                return 8;
            }
            case "Snacks" -> {
                return 9;
            }
            case "Breakfast" -> {
                return 10;
            }
            default -> {
            }
        }
        return 0;
    }
    //only for testing purposes
    @Override
    public String toString() {
        return "Product ID: " + id + "\nName: " + name + "\nBrand: " + brand + "\nPrice: " + price +
                "\nQuantity: " + quantity + "\nImage Address: " + imageAddress + "\nScore: " + score +
                "\nVote Count: " + voteCount + "\nType: " + typeToString() + "\nDescription: " + description;
    }

    public Product(int id, String name, String brand, double price, int quantity, String imageAddress, double score, int voteCount, int type, String description) {
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
    public Product(String name, String brand, double price, int quantity, String imageAddress,int type, String description) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.imageAddress = imageAddress;
        this.type = type;
        this.description= description;
    }
}
