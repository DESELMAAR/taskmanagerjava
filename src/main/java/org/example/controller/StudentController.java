package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    private final StudentManager manager;

    public StudentController(StudentManager manager) {
        this.manager = manager;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/add")
    public String addStudent(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam double grade) {
        manager.addStudent(name, age, grade);
        return "redirect:/";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", manager.getAllStudents());
        return "students";
    }
}