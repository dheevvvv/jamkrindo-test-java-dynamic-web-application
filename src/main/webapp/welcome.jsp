<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.enigma.javadynamicwebtest.model.Student" %>
<%@ page import="com.enigma.javadynamicwebtest.model.Department" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <script>
        function showStudentName() {
            alert("Dheva");
        }
    </script>
</head>
<body>

<h1>Welcome <span onclick="showStudentName()"><%= request.getAttribute("userID") %></span></h1>

<table>
    <thead>
    <tr>
        <th>Department</th>
        <th>Student ID</th>
        <th>Marks</th>
        <th>Pass %</th>
    </tr>
    </thead>
    <tbody>

    <%
        Map<String, Department> departmentMap =
                (Map<String, Department>) request.getAttribute("department");
        Map<String, Department> sortedMap = departmentMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        for (Map.Entry<String, Department> entry : sortedMap.entrySet()) {
            Department stats = entry.getValue();
            List<Student> studentsInDept = stats.getStudents();
            int passingCount = 0;

            for (Student student : studentsInDept) {
                if (student.getMarks() >= 40) passingCount++;
            }

            double passPercentage = studentsInDept.size() > 0 ? passingCount * 100.0 / studentsInDept.size() : 0;

            int rowspan = studentsInDept.size();
    %>

    <%
        for (int i = 0; i < studentsInDept.size(); i++) {
            Student student = studentsInDept.get(i);
    %>
    <tr>
        <% if (i == 0) { %>
        <td rowspan="<%= rowspan %>"><%= stats.getDepartmentName() %></td>
        <% } %>
        <td><%= student.getStudentId() %></td>
        <td><%= student.getMarks() %></td>
        <% if (i == 0) { %>
        <td rowspan="<%= rowspan %>"><%= String.format("%.2f", passPercentage) + "%" %></td>
        <% } %>
    </tr>
    <%
            }
        }
    %>

    </tbody>
</table>

</body>
</html>
