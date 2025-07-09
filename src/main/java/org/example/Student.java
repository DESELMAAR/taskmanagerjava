package org.example;

public class Student {
    private int id;
    private String name;
    private int age;
    private double grade;

    public Student(int id, String name, int age, double grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + grade;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(Integer.parseInt(parts[0]), parts[1],
                Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
    }
}
