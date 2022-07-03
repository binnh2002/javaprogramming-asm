package Model;

import java.util.ArrayList;

public class Course {
    protected String CourseName;
    protected String CourseId;
    protected String CourseDescription;
    private int maxStudents;
    private int enrolledStudents;
    private ArrayList<String> students;
    private ArrayList<String> studentCourses;

    public Course() {

    }

    public Course(String pcName, String pcId, String pcDescription, int pmStudent, int pEnrolledStudents, ArrayList<String> pStudents, ArrayList<String> pStudentCourses) {
        this.CourseName = pcName;
        this.CourseId = pcId;
        this.CourseDescription = pcDescription;
        this.maxStudents = pmStudent;
        this.enrolledStudents = pEnrolledStudents;
        this.students = new ArrayList<String>();
        this.studentCourses = new ArrayList<String>();
    }
    // set and get data
    public String getCourseName() {
        return this.CourseName;
    }
    public void getCourseId(String courseId) {
        this.CourseId = courseId;
    }

    public String getCourseDescription() {
        return this.CourseDescription;
    }

    public int getMaxStudents() {
        return this.maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public int getEnrolledStudents(){
        return this.enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents){
        this.enrolledStudents = enrolledStudents;
    }

    public ArrayList<String> getStudent(){
        return this.students;
    }

    public ArrayList<String> setStudents(ArrayList<String> students){
        return this.students = students;
    }

    public ArrayList<String> getStudentCourse(){
        return this.studentCourses;
    }

    public ArrayList<String> setStudentCourse(ArrayList<String> studentCourses){
        return this.studentCourses = studentCourses;
    }

    public void addStudentCourses(String courseID) {
        this.studentCourses.add(courseID);
    }

    public void removeStudentCourses(String courseID) {
        this.studentCourses.remove(courseID);
    }

}