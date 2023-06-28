package source;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Global {
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

    private Global() {}
}
