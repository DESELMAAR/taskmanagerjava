package org.example.service;

import org.example.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private int nextId = 1;

    public void addStudent(String name, int age, double grade) {
        Student student = new Student();
        student.setId(nextId++);
        student.setName(name);
        student.setAge(age);
        student.setGrade(grade);
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}