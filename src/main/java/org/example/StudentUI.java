package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentUI extends JFrame {
    private StudentManager manager;

    private JTextField nameField, ageField, gradeField;
    private JTextArea outputArea;

    public StudentUI() {
        manager = new StudentManager();

        setTitle("Student Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField(10);
        add(ageField);

        add(new JLabel("Grade:"));
        gradeField = new JTextField(5);
        add(gradeField);

        JButton addButton = new JButton("Add Student");
        add(addButton);

        JButton showButton = new JButton("Show All Students");
        add(showButton);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        // 🟢 Button actions
        addButton.addActionListener(e -> addStudent());
        showButton.addActionListener(e -> showStudents());

        setVisible(true);
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());
            manager.addStudent(name, age, grade);
            JOptionPane.showMessageDialog(this, "Student added!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void showStudents() {
        outputArea.setText("");           // Clear
        var students = manager.getAllStudents();
        if (students.isEmpty()) {
            outputArea.setText("No students found.");
        } else {
            for (Student s : students) {
                outputArea.append(
                        "ID: " + s.getId() +
                                ", Name: " + s.getName() +
                                ", Age: " + s.getAge() +
                                ", Grade: " + s.getGrade() + "\n");
            }
        }
    }


    public static void main(String[] args) {
        if (!DBConnection.ping()) {
            JOptionPane.showMessageDialog(null, "Cannot connect to DB");
            System.exit(1);
        }
        new StudentUI();
    }
}
