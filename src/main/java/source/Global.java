package source;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Global extends User{
    public static final String PASSWORD = "12345678";
    private static Stage stage;

    public static void setStage(Stage s, int password) {
        if (password == 12345678) {
            stage = s;
        }
    }
    public static Stage getStage() {
        return stage;
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
