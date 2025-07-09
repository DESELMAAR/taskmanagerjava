package org.example;

import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.txt";
    private int nextId = 1;

    public StudentManager() {
        loadFromFile();
    }

    public void addStudent(String name, int age, double grade) {
        Student s = new Student(nextId++, name, age, grade);
        students.add(s);
        saveToFile();
        System.out.println("Student added successfully.");
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName() +
                    ", Age: " + s.getAge() + ", Grade: " + s.getGrade());
        }
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                System.out.println("Found: " + s);
                found = true;
            }
        }
        if (!found) System.out.println("Student not found.");
    }

    public void searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Found: " + s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent(int id) {
        Iterator<Student> iterator = students.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                found = true;
                saveToFile();
                System.out.println("Student deleted.");
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int maxId = 0;
            while ((line = reader.readLine()) != null) {
                Student s = Student.fromString(line);
                students.add(s);
                if (s.getId() > maxId) maxId = s.getId();
            }
            nextId = maxId + 1;
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
