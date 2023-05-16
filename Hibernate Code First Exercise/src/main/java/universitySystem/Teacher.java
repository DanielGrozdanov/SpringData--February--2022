package universitySystem;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends BasicEntity {

    private String email;

    @Column(name = "salary_per_hour")
    private double salaryPerHour;

    @OneToMany(mappedBy = "teacher", targetEntity = Course.class)
    private Set<Course> courses;

    public Teacher() {

    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.courses = new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
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
