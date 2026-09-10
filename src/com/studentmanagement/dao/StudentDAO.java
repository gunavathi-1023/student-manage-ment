package com.studentmanagement.dao;
import com.studentmanagement.model.Student;
import com.studentmanagement.util.DBConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {

    public void addStudent(Student student) {

        String sql = "INSERT INTO students (id, name, email, course, marks) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection connection = DBConnections.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getCourse());
            statement.setDouble(5, student.getMarks());

            statement.executeUpdate();

            System.out.println("Student added to database!");

            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void viewStudents() {

        String sql = "SELECT * FROM students";

        try {

            Connection connection = DBConnections.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            var result = statement.executeQuery();

            System.out.println("\n---Student List ---");

            while (result.next()) {

                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String course = result.getString("course");
                double marks = result.getDouble("marks");

                System.out.println(
                        id + " | " +
                                name + " | " +
                                email + " | " +
                                course + " | " +
                                marks
                );
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void searchStudent(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try {

            Connection connection = DBConnections.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            var result = statement.executeQuery();

            if (result.next()) {

                System.out.println("\n--- com.studentmanagement.model.Student Found ---");

                System.out.println("ID: " + result.getInt("id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Email: " + result.getString("email"));
                System.out.println("Course: " + result.getString("course"));
                System.out.println("Marks: " + result.getDouble("marks"));

            } else {

                System.out.println("com.studentmanagement.model.Student not found.");
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void updateStudent(int id, String name, String email,
                              String course, double marks) {

        String sql = "UPDATE students SET name = ?, email = ?, " +
                "course = ?, marks = ? WHERE id = ?";

        try {

            Connection connection = DBConnections.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, course);
            statement.setDouble(4, marks);
            statement.setInt(5, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("com.studentmanagement.model.Student updated successfully!");
            } else {
                System.out.println("com.studentmanagement.model.Student not found.");
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try {

            Connection connection = DBConnections.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("com.studentmanagement.model.Student deleted successfully!");
            } else {
                System.out.println("com.studentmanagement.model.Student not found.");
            }

            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}