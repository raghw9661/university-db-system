package models;

public class Faculty {

    private int facultyId;
    private String fullName;
    private String email;
    private int deptId;

    // Constructor with ID
    public Faculty(int facultyId, String fullName, String email, int deptId) {
        this.facultyId = facultyId;
        this.fullName = fullName;
        this.email = email;
        this.deptId = deptId;
    }

    // Constructor without ID
    public Faculty(String fullName, String email, int deptId) {
        this.fullName = fullName;
        this.email = email;
        this.deptId = deptId;
    }

    // Getters
    public int getFacultyId() { return facultyId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public int getDeptId() { return deptId; }

    // Setters
    public void setFacultyId(int facultyId) { this.facultyId = facultyId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setDeptId(int deptId) { this.deptId = deptId; }
}