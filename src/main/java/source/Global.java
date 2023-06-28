package source;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Global {
    public static final String PASSWORD = "12345678";
    private static Stage stage;
    private static int user_id;
    private static int user_type; //1: consumer, 2:vendor.

    public static void setUser_type(int user_type) {
        Global.user_type = user_type;
    }
    public static int getUser_type() {
        return user_type;
    }
    public static void setStage(Stage s, int password) {
        if (password == 12345678) {
            stage = s;
        }
    }
    public static Stage getStage() {
        return stage;
    }

    public static void setUser_id(int user_id) {
        Global.user_id = user_id;
    }
    public static int getUser_id() {
        return user_id;
    }
    public static final Integer COLOR1 = 0xE0E0E0;
    public static final Integer COLOR2 = 0xFFFF33;
    public static Color hexToColor(int color, double opacity) {
        if (opacity < 0.0 || opacity > 1.0) {
            System.out.println("Invalid Opacity Value, it must be between 0 to 1, using max opacity instead");
            opacity = 1.0f;
        }
        //System.out.printf("%X%n", color);
        return Color.web("#" + String.format("%X", color), opacity);
    }
    public static String getMonthName(int month) {
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "Invalid month";
        };
    }
    private Global() {}
}
