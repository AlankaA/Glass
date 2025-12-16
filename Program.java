package Stakan;
import java.util.Scanner;

public class Program {
    public static final String MENU = """
                \n===========MENU===========
                Choose one of the options
                1. Add water
                2. Remove water
                3. Get volume of the glass
                4. Get water in glass
                5. Get free space
                6. Exit
                ==========================""";
    public static final String BACK_TO_MENU = "[INFO] Back to menu.";
    public static final String MENU_INVALID_OPTION = "[ERROR] Invalid option. Choose 1-6.";
    public static final String EMPTY_IS_NOT_ALLOWED = "[ERROR] Empty input is not allowed.";
    public static final String NEGATIVE_IS_NOT_ALLOWED = "[ERROR] Negative input is not allowed.";
    public static final String NUMBER_GREATER_THAN_ZERO = "[ERROR] Number should be greater than zero.";
    public static final String ENTER_INTEGER = "[ERROR] Invalid input. Enter an integer.";
    public static final String GLASS_VOLUME = "[PROMPT] Enter glass volume (ml):";
    public static final String GLASS_IS_EMPTY = "[INFO] The glass is empty. Nothing to delete.";
    public static final String GLASS_IS_FULL = "[INFO] Glass is full. No more water can be added.";
    public static final String EXIT = "[INFO] Exiting...";
    public static final String FINISHED = "[INFO] Program finished.";


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Logic creating a Glass object with input parameters.
        int glassVolume = readPositiveInt(input, GLASS_VOLUME);
        Glass glass = new Glass(glassVolume);


        // Main logic of program
        int action = 0;
        while (action != 6) {
            action = readIntMenu(input);
            switch (action) {
                case 1:
                    // Add water
                    if (glass.isFull()) {
                        System.out.println(GLASS_IS_FULL);
                        break;
                    }
                    while (true) {
                        int mlAdd = readIntOrZero(input, "Enter ml to add (0 = back)");
                        if (mlAdd == 0) {
                            System.out.println(BACK_TO_MENU);
                            break;
                        }

                        int mlBeforeAdd = glass.getWaterInGlass();
                        glass.addWaterML(mlAdd);
                        int realAdd = (glass.getWaterInGlass() - mlBeforeAdd);
                        System.out.println("[INFO] Added " + realAdd + "ml.\tCan be added: "
                                + glass.getFreeSpace() + "ml.");

                        // Prevent add water to Full glass
                        if (glass.isFull()) {
                            System.out.println(GLASS_IS_FULL);
                            break;
                        }
                    }
                    break;

                case 2:
                    // Remove water
                    if (glass.isEmpty()) {
                        System.out.println(GLASS_IS_EMPTY);
                        break;
                    }
                    while (true) {
                        int mlDel = readIntOrZero(input, "Enter ml to remove (0 = back)");
                        if (mlDel == 0) {
                            System.out.println(BACK_TO_MENU);
                            break;
                        }

                        int mlBeforeDel = glass.getWaterInGlass();
                        glass.removeWaterML(mlDel);
                        int realDel = (mlBeforeDel - glass.getWaterInGlass());
                        System.out.println("[INFO] Removed " + realDel + "ml.\tCan be removed: "
                                + glass.getWaterInGlass() + "ml.");
                        if (glass.isEmpty()) {
                            System.out.println(GLASS_IS_EMPTY);
                            break;
                        }
                    }
                    break;

                case 3:
                    // Get volume of the glass
                    System.out.println("[INFO] Glass volume: " + glass.getGlassVolumeML() + "ml.");
                    break;

                case 4:
                    //Get water in glass
                    System.out.println("[INFO] Water in glass: " + glass.getWaterInGlass() + "ml.");
                    break;

                case 5:
                    //Get free space
                    System.out.println("[INFO] Free space: " + glass.getFreeSpace() + "ml.");
                    break;

                case 6:
                    // Exit
                    System.out.println(EXIT);
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected menu option: " + action);
            }
        }
        System.out.print(FINISHED);
    }


    static void printMenu() {
        System.out.println(MENU);
    }




    static int readIntMenu(Scanner input) {
        while (true) {
            try {
                printMenu();
                String inputValue = input.nextLine().trim();

                // Prevent empty input
                if (inputValue.isEmpty()) {
                    System.out.println(EMPTY_IS_NOT_ALLOWED);
                    continue;
                }

                // Parsed String to Integer
                int parsedInput = Integer.parseInt(inputValue);

                // Prevent input less than 0 or more than 6
                if (parsedInput <= 0 || parsedInput > 6) {
                    System.out.println(MENU_INVALID_OPTION);
                    continue;
                }
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_INTEGER);
            }
        }
    }


    static int readPositiveInt(Scanner input, String message) {
        while (true) {
            try {
                System.out.println(message);

                // Prevent empty input
                String inputValue = input.nextLine().trim();
                if (inputValue.isEmpty()) {
                    System.out.println(EMPTY_IS_NOT_ALLOWED);
                    continue;
                }

                // Parse String input to Integer
                int parsedInput = Integer.parseInt(inputValue);

                // Prevent 0 input
                if (parsedInput <= 0) {
                    System.out.println(NUMBER_GREATER_THAN_ZERO);
                    continue;
                }

                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_INTEGER);
            }
        }
    }


    static int readIntOrZero(Scanner input, String message) {
        while (true) {
            try {
                System.out.println(message);
                // Prevent empty input
                String inputValue = input.nextLine().trim();
                if (inputValue.isEmpty()) {
                    System.out.println(EMPTY_IS_NOT_ALLOWED);
                    continue;
                }

                // Parse String to Integer
                int parsedInput = Integer.parseInt(inputValue);

                // Prevent input less than 0
                if (parsedInput < 0) {
                    System.out.println(NEGATIVE_IS_NOT_ALLOWED);
                    continue;
                }
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println(ENTER_INTEGER);
            }
        }
    }
}