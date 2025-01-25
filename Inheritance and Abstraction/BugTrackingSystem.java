import java.util.*;

abstract class Bug {
    protected int id;
    protected String description;
    protected String status;

    public Bug(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "Open";
    }

    public abstract void resolve();
    
    @Override
    public String toString() {
        return "Bug ID: " + id + ", Description: " + description + ", Status: " + status;
    }
}

class UIBug extends Bug {
    public UIBug(int id, String description) {
        super(id, description);
    }

    @Override
    public void resolve() {
        status = "Resolved";
        System.out.println("UI bug resolved: " + description);
    }
}

class BackendBug extends Bug {
    public BackendBug(int id, String description) {
        super(id, description);
    }

    @Override
    public void resolve() {
        status = "Resolved";
        System.out.println("Backend bug resolved: " + description);
    }
}

abstract class User {
    protected String name;
    protected String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public abstract void menu();
}

class Tester extends User {
    private List<Bug> bugs = new ArrayList<>();

    public Tester(String name) {
        super(name, "Tester");
    }

    public void addBug(Bug bug) {
        bugs.add(bug);
        System.out.println("Bug added: " + bug.description);
    }

    public void viewBugs() {
        System.out.println("Bugs reported:");
        for (Bug bug : bugs) {
            System.out.println(bug);
        }
    }

    @Override
    public void menu() {
        System.out.println("1. Add Bug\n2. View Bugs");
    }
}

class Developer extends User {
    private List<Bug> assignedBugs = new ArrayList<>();

    public Developer(String name) {
        super(name, "Developer");
    }

    public void assignBug(Bug bug) {
        assignedBugs.add(bug);
        System.out.println("Bug assigned: " + bug.description);
    }

    public void resolveBugs() {
        for (Bug bug : assignedBugs) {
            bug.resolve();
        }
        assignedBugs.clear();
    }

    @Override
    public void menu() {
        System.out.println("1. View Assigned Bugs\n2. Resolve Bugs");
    }
}

public class BugTrackingSystem {
    public static void main(String[] args) {
        Tester tester = new Tester("Alice");
        Developer developer = new Developer("Bob");

        UIBug uiBug = new UIBug(1, "Button alignment issue");
        BackendBug backendBug = new BackendBug(2, "Database connection error");

        tester.menu();
        tester.addBug(uiBug);
        tester.addBug(backendBug);
        tester.viewBugs();

        developer.menu();
        developer.assignBug(uiBug);
        developer.assignBug(backendBug);
        developer.resolveBugs();
    }
}
