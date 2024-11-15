package com.enigma.javadynamicwebtest.servlets;

import com.enigma.javadynamicwebtest.model.Department;
import com.enigma.javadynamicwebtest.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = createStudents();

        Map<String, Department> departmentMap = createDepartmentMap(students);

        request.setAttribute("students", students);
        request.setAttribute("department", departmentMap);

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    private List<Student> createStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("S1", "Dep 1", 35));
        students.add(new Student("S2", "Dep 1", 70));
        students.add(new Student("S3", "Dep 1", 60));
        students.add(new Student("S4", "Dep 1", 90));
        students.add(new Student("S5", "Dep 2", 30));
        students.add(new Student("S6", "Dep 3", 32));
        students.add(new Student("S7", "Dep 3", 70));
        students.add(new Student("S8", "Dep 3", 20));
        return students;
    }

    private Map<String, Department> createDepartmentMap(List<Student> students) {
        Map<String, Department> departmentMap = new HashMap<>();
        for (Student student : students) {
            String department = student.getDepartment();
            departmentMap
                    .computeIfAbsent(department, Department::new)
                    .addStudent(student);
        }
        return departmentMap;
    }
}