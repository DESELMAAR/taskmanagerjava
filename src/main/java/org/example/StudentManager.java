package org.example;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();  //MAKE ARRAY NAMED students



public void addStudent(String name, int age, double grade) {
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
        var stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, age);
        stmt.setDouble(3, grade);
        stmt.executeUpdate();
        System.out.println("Student added successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void displayStudents() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students";
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name")
                        + ", Age: " + rs.getInt("age") + ", Grade: " + rs.getDouble("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void searchByName(String name) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE name = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            var rs = stmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                System.out.println("Found: ID=" + rs.getInt("id") + ", Name=" + rs.getString("name")
                        + ", Age=" + rs.getInt("age") + ", Grade=" + rs.getDouble("grade"));
                found = true;
            }

            if (!found) System.out.println("Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void searchById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE id = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Found: ID=" + rs.getInt("id") + ", Name=" + rs.getString("name")
                        + ", Age=" + rs.getInt("age") + ", Grade=" + rs.getDouble("grade"));
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void deleteStudent(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    // import java.util.*;
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("grade")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
