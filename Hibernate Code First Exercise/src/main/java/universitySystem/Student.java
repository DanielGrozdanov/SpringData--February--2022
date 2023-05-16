package universitySystem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BasicEntity {

    @Column(name = "average_grade")
    private double averageGrade;

    private int attendance;

    @ManyToMany
    @JoinTable(name = "students_courses", joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, String phoneNumber, double averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        this.courses = new HashSet<>();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
