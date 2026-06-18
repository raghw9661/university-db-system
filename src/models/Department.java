package models;

public class Department {

    private int deptId;
    private String deptName;
    private String hodName;

    // Constructor with ID
    public Department(int deptId, String deptName, String hodName) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.hodName = hodName;
    }

    // Constructor without ID (used when adding new record)
    public Department(String deptName, String hodName) {
        this.deptName = deptName;
        this.hodName = hodName;
    }

    // Getters
    public int getDeptId() { return deptId; }
    public String getDeptName() { return deptName; }
    public String getHodName() { return hodName; }

    // Setters
    public void setDeptId(int deptId) { this.deptId = deptId; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public void setHodName(String hodName) { this.hodName = hodName; }
}