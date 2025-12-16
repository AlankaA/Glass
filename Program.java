package Stakan;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Program {
    // MENU
    public static final String MENU = """
            \n===========MENU===========
            Choose one of the options
            1. Add water
            2. Remove water
            3. Get volume of the glass
            4. Get water in glass
            5. Get free space
            6. Exit
            7. Change color
            ==========================""";
    // INFO
    public static final String EXIT = "Exiting...";
    public static final String FINISHED = "Program finished.";
    public static final String BACK_TO_MENU = "Back to menu.";
    public static final String GLASS_IS_EMPTY = "The glass is empty. Nothing to delete.";
    public static final String GLASS_IS_FULL = "Glass is full. No more water can be added.";
    public static final String COLOR_CHANGED = "Color changed!";
    // PROMPT
    public static final String GLASS_VOLUME = "[PROMPT] Enter glass volume (ml):";
    public static final String ENTER_ML_TO_ADD = "[PROMPT] Enter ml to add (0 = back):";
    public static final String ENTER_ML_TO_DEL = "[PROMPT] Enter ml to remove (0 = back):";
    // ERROR
    public static final String ENTER_INTEGER = "Invalid input. Enter an integer.";
    public static final String MENU_INVALID_OPTION = "Invalid option. Choose 1-7.";
    public static final String EMPTY_IS_NOT_ALLOWED = "Empty input is not allowed.";
    public static final String NEGATIVE_IS_NOT_ALLOWED = "Negative input is not allowed.";
    public static final String NUMBER_GREATER_THAN_ZERO = "Number should be greater than zero.";
    private static String waterColor = "";


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create Glass object.
        int glassVolume = readPositiveInt(input, GLASS_VOLUME);
        Glass glass = new Glass(glassVolume);


        // Main logic.
        int action = 0;
        while (action != 6) {
            action = readIntMenu(input);
            switch (action) {
                case 1:
                    addWaterML(input, glass);
                    break;

                case 2:
                    removeWaterML(input, glass);
                    break;

                case 3:
                    printInfoMessage(("Glass volume: " + glass.getGlassVolumeML() + "ml."));
                    break;

                case 4:
                    printInfoMessage(("Water in glass: " + glass.getWaterInGlass() + "ml."));
                    break;

                case 5:
                    printInfoMessage(("Free space: " + glass.getFreeSpace() + "ml."));
                    break;

                case 6:
                    printInfoMessage(EXIT);
                    break;

                case 7:
                    var random = ThreadLocalRandom.current().nextInt(0, 7);
                    waterColor = Colors.getColor(random);
                    printInfoMessage((Colors.getColor(random) + COLOR_CHANGED + Colors.getColor(0)));
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected menu option: " + action);
            }
        }
        printInfoMessage(FINISHED);
    }


    // Business rules (glass state)
    public static void addWaterML(Scanner input, Glass glass) {
        if (handleFullGlass(glass)) {
            return;
        }
        while (true) {
            int waterVolumeToAdd = readIntOrZero(input, ENTER_ML_TO_ADD);
            if (waterVolumeToAdd == 0) {
                printInfoMessage(BACK_TO_MENU);
                break;
            }
            int waterAlreadyInGlass = glass.getWaterInGlass();
            glass.addWaterML(waterVolumeToAdd);
            int waterVolumeWasAdded = (glass.getWaterInGlass() - waterAlreadyInGlass);
            printInfoMessage("Added " + waterVolumeWasAdded + " ml. " +
                    "Can be added: " + glass.getFreeSpace() + " ml.");
            if (handleFullGlass(glass)) {
                break;
            }
        }
    }

    public static void removeWaterML(Scanner input, Glass glass) {
        if (handleEmptyGlass(glass)){
            return;
        }
        while (true) {
            int mlDel = readIntOrZero(input, ENTER_ML_TO_DEL);
            if (mlDel == 0) {
                printInfoMessage(BACK_TO_MENU);
                break;
            }
            int mlBeforeDel = glass.getWaterInGlass();
            glass.removeWaterML(mlDel);
            int realDel = (mlBeforeDel - glass.getWaterInGlass());
            printInfoMessage("Removed " + realDel + " ml. " +
                    "Can be removed: " + glass.getWaterInGlass() + " ml.");
            if (handleEmptyGlass(glass)) {
                break;
            }
        }
    }

    static boolean handleFullGlass(Glass glass) {
        if (glass.isFull()) {
            printInfoMessage(GLASS_IS_FULL);
            return true;
        }
        return false;
    }

    static boolean handleEmptyGlass(Glass glass) {
        if (glass.isEmpty()) {
            printInfoMessage(GLASS_IS_EMPTY);
            return true;
        }
        return false;
    }


    // UI output
    static void printMenu() {
        printMessage(MENU);
    }

    public static void printMessage(String message) {
        System.out.println(waterColor + message + Colors.ResetColor);
    }

    public static void printInfoMessage(String message) {
        System.out.println(waterColor + "[INFO] " + message + Colors.ResetColor);
    }

    public static void printErrorMessage(String message) {
        System.out.println(waterColor + "[ERROR] " + message + Colors.ResetColor);
    }


    // Input parsing & validation
    static int readIntMenu(Scanner input) {
        while (true) {
            try {
                printMenu();
                String inputValue = input.nextLine().trim();

                if (inputValue.isEmpty()) {
                    printErrorMessage(EMPTY_IS_NOT_ALLOWED);
                    continue;
                }

                int parsedInput = Integer.parseInt(inputValue);

                if (parsedInput <= 0 || parsedInput > 7) {
                    printErrorMessage(MENU_INVALID_OPTION);
                    continue;
                }
                return parsedInput;
            } catch (NumberFormatException e) {
                printErrorMessage(ENTER_INTEGER);
            }
        }
    }


    static int readPositiveInt(Scanner input, String message) {
        while (true) {
            try {
                System.out.println(message);

                String inputValue = input.nextLine().trim();
                if (inputValue.isEmpty()) {
                    printErrorMessage(EMPTY_IS_NOT_ALLOWED);
                    continue;
                }

                int parsedInput = Integer.parseInt(inputValue);

                if (parsedInput <= 0) {
                    printErrorMessage(NUMBER_GREATER_THAN_ZERO);
                    continue;
                }
                return parsedInput;
            } catch (NumberFormatException e) {
                printErrorMessage(ENTER_INTEGER);
            }
        }
    }


    static int readIntOrZero(Scanner input, String message) {
        while (true) {
            try {
                printMessage(message);

                String inputValue = input.nextLine().trim();
                if (inputValue.isEmpty()) {
                    printErrorMessage(EMPTY_IS_NOT_ALLOWED);
                    continue;
                }

                int parsedInput = Integer.parseInt(inputValue);

                if (parsedInput < 0) {
                    printErrorMessage(NEGATIVE_IS_NOT_ALLOWED);
                    continue;
                }
                return parsedInput;
            } catch (NumberFormatException e) {
                printErrorMessage(ENTER_INTEGER);
            }
        }
    }
}