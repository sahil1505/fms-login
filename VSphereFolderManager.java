import java.io.File;
import java.util.Scanner;

public class VSphereFolderManager {
    
    private static final String BASE_DIR = "vCenter_Simulation"; // Root directory for simulation

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File baseFolder = new File(BASE_DIR);

        // Ensure base directory exists
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        while (true) {
            System.out.println("\nvSphere Folder Management Menu:");
            System.out.println("1. Create Data Center");
            System.out.println("2. Create VM Folder in a Data Center");
            System.out.println("3. List Existing Folders");
            System.out.println("4. Delete a Folder");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createDataCenter(scanner);
                    break;
                case 2:
                    createVMFolder(scanner);
                    break;
                case 3:
                    listFolders();
                    break;
                case 4:
                    deleteFolder(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Create a new data center folder
    private static void createDataCenter(Scanner scanner) {
        System.out.print("Enter Data Center Name: ");
        String dcName = scanner.nextLine();
        File dcFolder = new File(BASE_DIR + "/" + dcName);

        if (dcFolder.exists()) {
            System.out.println(" Data Center already exists!");
        } else {
            if (dcFolder.mkdirs()) {
                System.out.println(" Data Center '" + dcName + "' created successfully.");
            } else {
                System.out.println(" Failed to create Data Center.");
            }
        }
    }

    // Create a VM folder inside an existing data center
    private static void createVMFolder(Scanner scanner) {
        System.out.print("Enter Data Center Name: ");
        String dcName = scanner.nextLine();
        File dcFolder = new File(BASE_DIR + "/" + dcName);

        if (!dcFolder.exists()) {
            System.out.println(" Data Center does not exist! Create it first.");
            return;
        }

        System.out.print("Enter VM Folder Name: ");
        String vmName = scanner.nextLine();
        File vmFolder = new File(dcFolder, vmName);

        if (vmFolder.exists()) {
            System.out.println(" VM Folder already exists!");
        } else {
            if (vmFolder.mkdirs()) {
                System.out.println(" VM Folder '" + vmName + "' created inside Data Center '" + dcName + "'.");
            } else {
                System.out.println(" Failed to create VM Folder.");
            }
        }
    }

    // List all existing folders inside the vCenter simulation directory
    private static void listFolders() {
        File baseFolder = new File(BASE_DIR);

        if (!baseFolder.exists() || baseFolder.list().length == 0) {
            System.out.println(" No Data Centers found.");
            return;
        }

        System.out.println("\n📂 Existing Data Centers and VMs:");
        for (File dcFolder : baseFolder.listFiles()) {
            if (dcFolder.isDirectory()) {
                System.out.println(" Data Center: " + dcFolder.getName());

                for (File vmFolder : dcFolder.listFiles()) {
                    if (vmFolder.isDirectory()) {
                        System.out.println("    VM Folder: " + vmFolder.getName());
                    }
                }
            }
        }
    }

    // Delete a specific folder (Data Center or VM)
    private static void deleteFolder(Scanner scanner) {
        System.out.print("Enter the folder path to delete (e.g., DataCenter/VMFolder): ");
        String folderPath = scanner.nextLine();
        File folder = new File(BASE_DIR + "/" + folderPath);

        if (!folder.exists()) {
            System.out.println(" Folder does not exist!");
            return;
        }

        if (deleteRecursively(folder)) {
            System.out.println(" Folder '" + folderPath + "' deleted successfully.");
        } else {
            System.out.println(" Failed to delete folder.");
        }
    }

    // Helper method to delete folders and sub-folders
    private static boolean deleteRecursively(File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                deleteRecursively(subFile);
            }
        }
        return file.delete();
    }
}
