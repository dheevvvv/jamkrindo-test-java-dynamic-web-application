package com.enigma.javadynamicwebtest.model;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private String departmentName;
    private List<Student> students;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.students = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}