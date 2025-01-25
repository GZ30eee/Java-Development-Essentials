import java.util.ArrayList;
import java.util.Scanner;

// Abstract class for Student
abstract class Student {
    private int id;
    private String name;
    private String course;

    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public abstract String getDetails();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Course: " + course;
    }
}

// Specialized class for Undergraduate Students
class UndergraduateStudent extends Student {
    private String year;

    public UndergraduateStudent(int id, String name, String course, String year) {
        super(id, name, course);
        this.year = year;
    }

    @Override
    public String getDetails() {
        return super.toString() + ", Year: " + year;
    }
}

// Specialized class for Postgraduate Students
class PostgraduateStudent extends Student {
    private String researchTopic;

    public PostgraduateStudent(int id, String name, String course, String researchTopic) {
        super(id, name, course);
        this.researchTopic = researchTopic;
    }

    @Override
    public String getDetails() {
        return super.toString() + ", Research Topic: " + researchTopic;
    }
}

// Student Management System
class StudentManagement {
    private ArrayList<Student> students;

    public StudentManagement() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    public void updateStudent(int id, String name, String course) {
        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                students.add(new UndergraduateStudent(id, name, course, "Updated Year"));
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found!");
    }

    public void viewAllStudents() {
        System.out.println("Student Records:");
        for (Student student : students) {
            System.out.println(student.getDetails());
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. View All Students");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();
                    System.out.print("Enter Type (Undergraduate/Postgraduate): ");
                    String type = scanner.nextLine();

                    if (type.equalsIgnoreCase("Undergraduate")) {
                        System.out.print("Enter Year: ");
                        String year = scanner.nextLine();
                        management.addStudent(new UndergraduateStudent(id, name, course, year));
                    } else if (type.equalsIgnoreCase("Postgraduate")) {
                        System.out.print("Enter Research Topic: ");
                        String researchTopic = scanner.nextLine();
                        management.addStudent(new PostgraduateStudent(id, name, course, researchTopic));
                    } else {
                        System.out.println("Invalid Type! Try Again.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Student ID to Update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter New Course: ");
                    String course = scanner.nextLine();
                    management.updateStudent(id, name, course);
                }
                case 3 -> management.viewAllStudents();
                case 4 -> {
                    System.out.println("Exiting Student Management System. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
