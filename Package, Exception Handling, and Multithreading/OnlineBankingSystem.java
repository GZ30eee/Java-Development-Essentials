import java.util.*;
import java.util.concurrent.*;

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited. Current balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public synchronized void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient funds!");
        } else if (amount <= 0) {
            throw new Exception("Invalid withdrawal amount.");
        } else {
            balance -= amount;
            System.out.println(amount + " withdrawn. Current balance: " + balance);
        }
    }

    public synchronized double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}

class BankUser implements Runnable {
    private BankAccount account;
    private double amount;
    private String transactionType;

    public BankUser(BankAccount account, double amount, String transactionType) {
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    @Override
    public void run() {
        try {
            switch (transactionType) {
                case "deposit":
                    account.deposit(amount);
                    break;
                case "withdraw":
                    account.withdraw(amount);
                    break;
                default:
                    System.out.println("Invalid transaction type.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public class OnlineBankingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static BankAccount account;

    public static void main(String[] args) {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        account = new BankAccount(name, 1000.00); // Initial balance of 1000
        System.out.println("Account created for " + name);

        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                BankUser user = new BankUser(account, amount, "deposit");
                new Thread(user).start();
            } else if (choice == 2) {
                System.out.print("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                BankUser user = new BankUser(account, amount, "withdraw");
                new Thread(user).start();
            } else if (choice == 3) {
                System.out.println("Current balance: " + account.getBalance());
            } else if (choice == 4) {
                System.out.println("Thank you for using the online banking system!");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}
