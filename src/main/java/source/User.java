package source;

public class User {
    private static int user_id;
    private static int user_type; //1: consumer, 2:vendor.
    private static double balance;
    public static void setUser_type(int user_type) {
        User.user_type = user_type;
    }
    public static int getUser_type() {
        return user_type;
    }
    public static void setUser_id(int user_id) {
        User.user_id = user_id;
    }
    public static int getUser_id() {
        return user_id;
    }
    public static double getBalance() {
        return balance;
    }

    public static void setBalance(double balance) {
        User.balance = balance;
    }
    public static void changeBalance(double change) {
        User.balance += change;
    }
}
