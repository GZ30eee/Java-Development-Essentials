import java.io.*;
import java.util.*;

class FileManager {
    private static final String LOG_FILE = "file_operations_log.txt";
    private static List<String> fileMetadata = new ArrayList<>();

    public static void createFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("File created successfully.");
            logOperation("Created file: " + filename);
            fileMetadata.add(filename);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public static void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Reading file: " + filename);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void updateFile(String filename, String newContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(newContent);
            System.out.println("File updated successfully.");
            logOperation("Updated file: " + filename);
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    public static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.delete()) {
            System.out.println("File deleted successfully.");
            logOperation("Deleted file: " + filename);
            fileMetadata.remove(filename);
        } else {
            System.out.println("Error deleting file.");
        }
    }

    public static void logOperation(String operation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(new Date() + ": " + operation);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error logging operation: " + e.getMessage());
        }
    }

    public static void displayFileMetadata() {
        System.out.println("List of files and their metadata:");
        for (String file : fileMetadata) {
            System.out.println("File: " + file);
        }
    }
}

public class FileManagementSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Create File\n2. Read File\n3. Update File\n4. Delete File\n5. View Metadata\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter filename: ");
                String filename = scanner.nextLine();
                System.out.print("Enter content: ");
                String content = scanner.nextLine();
                FileManager.createFile(filename, content);
            } else if (choice == 2) {
                System.out.print("Enter filename to read: ");
                String filename = scanner.nextLine();
                FileManager.readFile(filename);
            } else if (choice == 3) {
                System.out.print("Enter filename to update: ");
                String filename = scanner.nextLine();
                System.out.print("Enter new content to append: ");
                String newContent = scanner.nextLine();
                FileManager.updateFile(filename, newContent);
            } else if (choice == 4) {
                System.out.print("Enter filename to delete: ");
                String filename = scanner.nextLine();
                FileManager.deleteFile(filename);
            } else if (choice == 5) {
                FileManager.displayFileMetadata();
            } else if (choice == 6) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
