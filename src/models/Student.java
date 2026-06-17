package models;

public class Student {

    private int studentId;
    private String fullName;
    private String email;
    private String dateOfBirth;
    private int deptId;

    // Constructor with ID (used when reading from database)
    public Student(int studentId, String fullName, String email, String dateOfBirth, int deptId) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.deptId = deptId;
    }

    // Constructor without ID (used when adding new record)
    public Student(String fullName, String email, String dateOfBirth, int deptId) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.deptId = deptId;
    }

    // Getters
    public int getStudentId() { return studentId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDateOfBirth() { return dateOfBirth; }
    public int getDeptId() { return deptId; }

    // Setters
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setDeptId(int deptId) { this.deptId = deptId; }
}
