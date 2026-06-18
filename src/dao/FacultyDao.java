package dao;

import db.DBConnection;
import models.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDao {

    // ADD new faculty
    public void addFaculty(Faculty f) throws Exception {
        String sql = "INSERT INTO faculty (full_name, email, dept_id) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getFullName());
            ps.setString(2, f.getEmail());
            ps.setInt(3, f.getDeptId());
            ps.executeUpdate();
        }
    }

    // GET ALL faculty
    public List<Faculty> getAllFaculty() throws Exception {
        List<Faculty> list = new ArrayList<>();
        String sql = "SELECT * FROM faculty";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Faculty(
                        rs.getInt("faculty_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getInt("dept_id")
                ));
            }
        }
        return list;
    }

    // UPDATE faculty
    public void updateFaculty(Faculty f) throws Exception {
        String sql = "UPDATE faculty SET full_name=?, email=?, dept_id=? WHERE faculty_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getFullName());
            ps.setString(2, f.getEmail());
            ps.setInt(3, f.getDeptId());
            ps.setInt(4, f.getFacultyId());
            ps.executeUpdate();
        }
    }

    // DELETE faculty
    public void deleteFaculty(int facultyId) throws Exception {
        String sql = "DELETE FROM faculty WHERE faculty_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, facultyId);
            ps.executeUpdate();
        }
    }
}