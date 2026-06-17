package models;

public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private String semester;
    private String enrollmentDate;

    // Constructor with ID
    public Enrollment(int enrollmentId, int studentId, int courseId, String semester, String enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.enrollmentDate = enrollmentDate;
    }

    // Constructor without ID
    public Enrollment(int studentId, int courseId, String semester, String enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters
    public int getEnrollmentId() { return enrollmentId; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public String getSemester() { return semester; }
    public String getEnrollmentDate() { return enrollmentDate; }

    // Setters
    public void setEnrollmentId(int enrollmentId) { this.enrollmentId = enrollmentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setEnrollmentDate(String enrollmentDate) { this.enrollmentDate = enrollmentDate; }
}