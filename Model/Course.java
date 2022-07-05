package Model;


public class Course {
    public String courseName;
    public String courseId;
    public String courseDescription;

    public String getCourseId(String courseId){
        return this.courseId;
    }

    public String getCourseName(String courseName){
        return this.courseName;
    }

    public String getCourseDescription(String courseDescription){
        return this.courseDescription;
    }

    public String getId() {
        return courseId;
    }

    public String getName() {
        return courseName;
    }

    public String getDescription() {
        return courseDescription;
    }
}