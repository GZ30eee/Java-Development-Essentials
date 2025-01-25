import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        System.out.println("Welcome to the Enhanced Calculator!");

        while (continueCalculating) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Modulus (%)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();

            if (choice == 6) {
                System.out.println("Thank you for using the Enhanced Calculator! Goodbye!");
                break;
            }

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();
            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();

            switch (choice) {
                case 1 -> System.out.println("Result: " + (num1 + num2));
                case 2 -> System.out.println("Result: " + (num1 - num2));
                case 3 -> System.out.println("Result: " + (num1 * num2));
                case 4 -> {
                    if (num2 != 0) {
                        System.out.println("Result: " + (num1 / num2));
                    } else {
                        System.out.println("Error: Division by zero is not allowed!");
                    }
                }
                case 5 -> System.out.println("Result: " + (num1 % num2));
            }
        }
        scanner.close();
    }
}
