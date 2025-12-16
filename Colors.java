package Stakan;

import java.util.HashMap;
import java.util.Map;

public class Colors {
    public static final Map<Integer, String> COLORS = Map.of(
            0, "\u001B[0m",   // RESET
            1, "\u001B[31m",  // RED
            2, "\u001B[32m",  // GREEN
            3, "\u001B[33m",  // YELLOW
            4, "\u001B[34m",  // BLUE
            5, "\u001B[35m",  // PURPLE
            6, "\u001B[36m"   // CYAN
    );

    public static String ResetColor = "\u001B[0m";

    public static String getColor(int color) {
        var colorCode = COLORS.get(color);
        return colorCode == null ? "" : colorCode;
    }
}
