package dao;

import db.DBConnection;
import models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {

    // ADD new course
    public void addCourse(Course c) throws Exception {
        String sql = "INSERT INTO courses (course_name, credits, dept_id) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCourseName());
            ps.setInt(2, c.getCredits());
            ps.setInt(3, c.getDeptId());
            ps.executeUpdate();
        }
    }

    // GET ALL courses
    public List<Course> getAllCourses() throws Exception {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("credits"),
                        rs.getInt("dept_id")
                ));
            }
        }
        return list;
    }

    // UPDATE course
    public void updateCourse(Course c) throws Exception {
        String sql = "UPDATE courses SET course_name=?, credits=?, dept_id=? WHERE course_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCourseName());
            ps.setInt(2, c.getCredits());
            ps.setInt(3, c.getDeptId());
            ps.setInt(4, c.getCourseId());
            ps.executeUpdate();
        }
    }

    // DELETE course
    public void deleteCourse(int courseId) throws Exception {
        String sql = "DELETE FROM courses WHERE course_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ps.executeUpdate();
        }
    }
}