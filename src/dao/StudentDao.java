package dao;

import db.DBConnection;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    // ADD new student
    public void addStudent(Student s) throws Exception {
        String sql = "INSERT INTO students (full_name, email, date_of_birth, dept_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getFullName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getDateOfBirth());
            ps.setInt(4, s.getDeptId());
            ps.executeUpdate();
        }
    }

    // GET ALL students
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("date_of_birth"),
                        rs.getInt("dept_id")
                ));
            }
        }
        return list;
    }

    // UPDATE student
    public void updateStudent(Student s) throws Exception {
        String sql = "UPDATE students SET full_name=?, email=?, date_of_birth=?, dept_id=? WHERE student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getFullName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getDateOfBirth());
            ps.setInt(4, s.getDeptId());
            ps.setInt(5, s.getStudentId());
            ps.executeUpdate();
        }
    }

    // DELETE student
    public void deleteStudent(int studentId) throws Exception {
        String sql = "DELETE FROM students WHERE student_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.executeUpdate();
        }
    }
}