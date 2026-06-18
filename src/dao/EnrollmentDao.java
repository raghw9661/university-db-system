package dao;

import db.DBConnection;
import models.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDao {

    // ADD new enrollment
    public void addEnrollment(Enrollment e) throws Exception {
        String sql = "INSERT INTO enrollments (student_id, course_id, semester, enrollment_date) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getStudentId());
            ps.setInt(2, e.getCourseId());
            ps.setString(3, e.getSemester());
            ps.setString(4, e.getEnrollmentDate());
            ps.executeUpdate();
        }
    }

    // GET ALL enrollments
    public List<Enrollment> getAllEnrollments() throws Exception {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("semester"),
                        rs.getString("enrollment_date")
                ));
            }
        }
        return list;
    }

    // DELETE enrollment
    public void deleteEnrollment(int enrollmentId) throws Exception {
        String sql = "DELETE FROM enrollments WHERE enrollment_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, enrollmentId);
            ps.executeUpdate();
        }
    }
}