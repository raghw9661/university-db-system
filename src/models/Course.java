package models;

public class Course {

    private int courseId;
    private String courseName;
    private int credits;
    private int deptId;

    // Constructor with ID
    public Course(int courseId, String courseName, int credits, int deptId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.deptId = deptId;
    }

    // Constructor without ID
    public Course(String courseName, int credits, int deptId) {
        this.courseName = courseName;
        this.credits = credits;
        this.deptId = deptId;
    }

    // Getters
    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public int getDeptId() { return deptId; }

    // Setters
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setDeptId(int deptId) { this.deptId = deptId; }
}