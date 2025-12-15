package Stakan;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Logic creating a Glass object with input parameters.
        int glassVolume = readPositiveInt(input, "What`s the volume of the glass?");
        Glass glass = new Glass(glassVolume);


        // Main logic of program
        int action = 0;
        while (action != 6) {
            action = readIntMenu(input);
            switch (action) {
                case 1:
                    // Add water
                    String glassFull = "[INFO] The glass is full. No more water can be added.";
                    if (glass.isFull()) {
                        System.out.println(glassFull);
                        break;
                    }
                    while (true) {
                        int mlAdd = readIntOrZero(input, "Enter ml to add (0 = back)");
                        if (mlAdd == 0) {
                            System.out.println("[INFO] Back to menu.");
                            break;
                        }

                        int mlBeforeAdd = glass.getWaterInGlass();
                        glass.addWaterML(mlAdd);
                        int realAdd = (glass.getWaterInGlass() - mlBeforeAdd);
                        System.out.println("[INFO] Added " + realAdd + "ml.\tCan be added: "
                                + glass.getFreeSpace() + "ml.");

                        // Prevent add water to Full glass
                        if (glass.isFull()) {
                            System.out.println(glassFull);
                            break;
                        }
                    }
                    break;

                case 2:
                    // Remove water
                    String glassEmpty = "[INFO] The glass is empty. There is nothing to remove.";
                    if (glass.isEmpty()) {
                        System.out.println(glassEmpty);
                        break;
                    }
                    while (true) {
                        int mlDel = readIntOrZero(input, "Enter ml to remove (0 = back)");
                        if (mlDel == 0) {
                            System.out.println("[INFO] Back to menu.");
                            break;
                        }

                        int mlBeforeDel = glass.getWaterInGlass();
                        glass.removeWaterML(mlDel);
                        int realDel = (mlBeforeDel - glass.getWaterInGlass());
                        System.out.println("[INFO] Removed " + realDel + "ml.\tCan be removed: "
                                + glass.getWaterInGlass() + "ml.");
                        if (glass.isEmpty()) {
                            System.out.println(glassEmpty);
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
                    System.out.println("[INFO] Exiting ... ");
                    break;

                default:
                    System.out.println("[ERROR] Something went wrong.");
            }
        }
        System.out.print("[INFO] The program is finished!");
    }


    static void printMenu() {
        String menu = """
                \n===========MENU===========
                Choose one of the options
                1. Add water
                2. Remove water
                3. Get volume of the glass
                4. Get water in glass
                5. Get free space
                6. Exit
                ==========================""";
        System.out.println(menu);
    }


    static int readIntMenu(Scanner input) {
        while (true) {
            try {
                printMenu();
                String inputValue = input.nextLine().trim();

                // Prevent empty input
                if (inputValue.isEmpty()) {
                    System.out.println("[ERROR] Empty input is NOT allowed.");
                    continue;
                }

                // Parsed String to Integer
                int parsedInput = Integer.parseInt(inputValue);

                // Prevent input less than 0 or more than 6
                if (parsedInput <= 0 || parsedInput > 6) {
                    System.out.println("[ERROR] Choose from 1-6.");
                    continue;
                }
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Input must be an integer.");
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
                    System.out.println("[ERROR] Empty input is NOT allowed.");
                    continue;
                }

                // Parse String input to Integer
                int parsedInput = Integer.parseInt(inputValue);

                // Prevent 0 input
                if (parsedInput <= 0) {
                    System.out.println("[ERROR] Number should be greater than zero.");
                    continue;
                }

                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Input must be a number.");
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
                    System.out.println("[ERROR] Empty input is NOT allowed.");
                    continue;
                }

                // Parse String to Integer
                int parsedInput = Integer.parseInt(inputValue);

                // Prevent input less than 0
                if (parsedInput < 0) {
                    System.out.println("[ERROR] Negative input is NOT allowed.");
                    continue;
                }
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Input must be a number.");
            }
        }
    }
}