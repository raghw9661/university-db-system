package dao;

import db.DBConnection;
import models.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    // ADD new department
    public void addDepartment(Department dept) throws Exception {
        String sql = "INSERT INTO departments (dept_name, hod_name) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getHodName());
            ps.executeUpdate();
        }
    }

    // GET ALL departments
    public List<Department> getAllDepartments() throws Exception {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("dept_id"),
                        rs.getString("dept_name"),
                        rs.getString("hod_name")
                ));
            }
        }
        return list;
    }

    // UPDATE department
    public void updateDepartment(Department dept) throws Exception {
        String sql = "UPDATE departments SET dept_name=?, hod_name=? WHERE dept_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dept.getDeptName());
            ps.setString(2, dept.getHodName());
            ps.setInt(3, dept.getDeptId());
            ps.executeUpdate();
        }
    }

    // DELETE department
    public void deleteDepartment(int deptId) throws Exception {
        String sql = "DELETE FROM departments WHERE dept_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            ps.executeUpdate();
        }
    }
}