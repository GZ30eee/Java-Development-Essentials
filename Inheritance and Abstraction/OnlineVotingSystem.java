import java.util.*;

abstract class User {
    protected String name;
    protected String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public abstract void menu();
}

class Voter extends User {
    private boolean hasVoted = false;

    public Voter(String name) {
        super(name, "Voter");
    }

    public void vote(String candidate, Map<String, Integer> votes) {
        if (!hasVoted) {
            votes.put(candidate, votes.getOrDefault(candidate, 0) + 1);
            System.out.println(name + " voted for " + candidate + ".");
            hasVoted = true;
        } else {
            System.out.println("You have already voted!");
        }
    }

    @Override
    public void menu() {
        System.out.println("1. View Elections\n2. Cast Vote");
    }
}

class Admin extends User {
    private List<String> elections = new ArrayList<>();
    private Map<String, Map<String, Integer>> electionData = new HashMap<>();

    public Admin(String name) {
        super(name, "Admin");
    }

    public void createElection(String electionName) {
        elections.add(electionName);
        electionData.put(electionName, new HashMap<>());
        System.out.println("Election created: " + electionName);
    }

    public void addCandidate(String electionName, String candidate) {
        if (elections.contains(electionName)) {
            electionData.get(electionName).put(candidate, 0);
            System.out.println(candidate + " added to " + electionName);
        } else {
            System.out.println("Election not found!");
        }
    }

    public void viewResults(String electionName) {
        if (electionData.containsKey(electionName)) {
            System.out.println("Results for " + electionName + ":");
            electionData.get(electionName).forEach((candidate, votes) ->
                System.out.println(candidate + ": " + votes + " votes")
            );
        } else {
            System.out.println("Election not found!");
        }
    }

    @Override
    public void menu() {
        System.out.println("1. Create Election\n2. Add Candidate\n3. View Results");
    }
}

public class OnlineVotingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, User> users = new HashMap<>();
    private static Admin admin;

    public static void main(String[] args) {
        admin = new Admin("SystemAdmin");
        users.put("admin", admin);

        System.out.println("=== Online Voting System ===");
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Thank you for using the system.");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void register() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter role (admin/voter): ");
        String role = scanner.nextLine();

        if ("voter".equalsIgnoreCase(role)) {
            users.put(name.toLowerCase(), new Voter(name));
            System.out.println("Voter registered successfully!");
        } else if ("admin".equalsIgnoreCase(role)) {
            System.out.println("Only one admin allowed.");
        } else {
            System.out.println("Invalid role!");
        }
    }

    private static void login() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        User user = users.get(name.toLowerCase());
        if (user != null) {
            System.out.println("Welcome, " + name + "!");
            user.menu();
            handleUserActions(user);
        } else {
            System.out.println("User not found!");
        }
    }

    private static void handleUserActions(User user) {
        if (user instanceof Admin) {
            Admin adminUser = (Admin) user;
            while (true) {
                System.out.println("\nChoose an admin action:");
                adminUser.menu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter election name: ");
                        String electionName = scanner.nextLine();
                        adminUser.createElection(electionName);
                    }
                    case 2 -> {
                        System.out.print("Enter election name: ");
                        String electionName = scanner.nextLine();
                        System.out.print("Enter candidate name: ");
                        String candidate = scanner.nextLine();
                        adminUser.addCandidate(electionName, candidate);
                    }
                    case 3 -> {
                        System.out.print("Enter election name: ");
                        String electionName = scanner.nextLine();
                        adminUser.viewResults(electionName);
                    }
                    default -> System.out.println("Exiting admin menu.");
                }
            }
        } else if (user instanceof Voter) {
            Voter voter = (Voter) user;
            System.out.println("Elections are available.");
            // Implement voting logic here
        }
    }
}
