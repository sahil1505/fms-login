import java.io.*;
import java.util.*;

public class SwitchConfigManager {
    
    private static final String CONFIG_FILE = "switch_config.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nvSphere Standard Switch Manager");
            System.out.println("1. View Switch Configuration");
            System.out.println("2. Add Virtual Switch Port");
            System.out.println("3. Remove Virtual Switch Port");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (choice) {
                case 1:
                    displayConfig();
                    break;
                case 2:
                    addPort(scanner);
                    break;
                case 3:
                    removePort(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void displayConfig() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            System.out.println("\nCurrent Switch Configuration:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading configuration file.");
        }
    }

    private static void addPort(Scanner scanner) {
        System.out.print("Enter Port Name (e.g., Port4): ");
        String portName = scanner.nextLine();
        System.out.print("Enter Network Name: ");
        String networkName = scanner.nextLine();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE, true))) {
            writer.write(portName + " - " + networkName);
            writer.newLine();
            System.out.println("Port added successfully: " + portName + " -> " + networkName);
        } catch (IOException e) {
            System.out.println("Error updating configuration.");
        }
    }

    private static void removePort(Scanner scanner) {
        System.out.print("Enter Port Name to Remove (e.g., Port2): ");
        String portToRemove = scanner.nextLine();

        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(portToRemove)) {
                    lines.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading configuration file.");
            return;
        }

        if (!found) {
            System.out.println("Port not found: " + portToRemove);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Port removed successfully: " + portToRemove);
        } catch (IOException e) {
            System.out.println("Error updating configuration.");
        }
    }
}
