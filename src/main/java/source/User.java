package source;

public class User {
    private static int user_id;

    private static int User_Email;

    private static int User_Password;

    private static int user_type; //1: consumer, 2:vendor.
    private static double balance;
    private static String username;
    private static String user_address;
    private static String user_postalCode;
    private static String user_phoneNumber;
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
    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getUsername() {
        return username;
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
    public static void setUser_address(String address){
        user_address = address;
    }
    public static String getUser_address(){
        return user_address;
    }
    public static void setUser_postalCode(String postalCode){
        user_postalCode = postalCode;
    }
    public static String getUser_postalCode() {
        return user_postalCode;
    }
    public static void setUser_phoneNumber(String phoneNumber) {
        user_phoneNumber = phoneNumber;
    }
    public static String getUser_phoneNumber() {
        return user_phoneNumber;
    }
}
