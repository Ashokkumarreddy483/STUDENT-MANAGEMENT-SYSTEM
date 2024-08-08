import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {

    // Student class
    static class Student {
        private String name;
        private String rollNumber;
        private String grade;

        // Constructor
        public Student(String name, String rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public String getGrade() {
            return grade;
        }

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setRollNumber(String rollNumber) {
            this.rollNumber = rollNumber;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
        }
    }

    // Main system class
    private List<Student> students;
    private Scanner scanner;

    public StudentManagementSystem() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadFromFile();
    }

    // Add a student
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
            System.out.println("All fields are required!");
            return;
        }

        // Check if the student already exists
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                System.out.println("Student with this roll number already exists.");
                return;
            }
        }

        Student student = new Student(name, rollNumber, grade);
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    // Remove a student
    public void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        String rollNumber = scanner.nextLine();

        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            students.remove(studentToRemove);
            saveToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // Search for a student
    public void searchStudent() {
        System.out.print("Enter roll number of the student to search: ");
        String rollNumber = scanner.nextLine();

        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                System.out.println(student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Load students from file
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Student student = new Student(parts[0], parts[1], parts[2]);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data.");
        }
    }

    // Save students to file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    // Display the menu and handle user input
    public void displayMenu() {
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Main class to run the application
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.displayMenu();
    }
}
