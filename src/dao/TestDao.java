package dao;

import models.Department;
import java.util.List;

public class TestDao {

    public static void main(String[] args) {
        DepartmentDao dao = new DepartmentDao();

        // Test ADD
        try {
            Department dept = new Department("Information Technology", "Dr. Gupta");
            dao.addDepartment(dept);
            System.out.println("✅ Department added successfully");
        } catch (Exception e) {
            System.out.println("❌ Add failed: " + e.getMessage());
        }

        // Test GET ALL
        try {
            List<Department> list = dao.getAllDepartments();
            System.out.println("✅ Total Departments: " + list.size());
            for (Department d : list) {
                System.out.println("   → " + d.getDeptId() + " | " + d.getDeptName() + " | " + d.getHodName());
            }
        } catch (Exception e) {
            System.out.println("❌ Fetch failed: " + e.getMessage());
        }
    }
}