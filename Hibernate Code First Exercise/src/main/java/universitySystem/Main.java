package universitySystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("code_first_test");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student student = new Student("John", "Doe", "04923842", 5.68, 26);
        Course physics = new Course("Physics", "Course about physics", LocalDate.now(), LocalDate.of(2023, 5, 16), 20);
        Course math = new Course("Math", "Course about math", LocalDate.now(), LocalDate.of(2023, 7, 16), 30);
        Teacher teacher = new Teacher("Pesho", "Peshev", "0988593", "Pesho.peshev@gmail.com", 60);

        teacher.addCourse(physics);
        teacher.addCourse(math);

        physics.setTeacher(teacher);
        math.setTeacher(teacher);

        physics.addStudent(student);
        math.addStudent(student);

        student.addCourse(physics);
        student.addCourse(math);

        em.persist(student);
        em.persist(teacher);

        em.persist(physics);
        em.persist(math);

        em.getTransaction().commit();
    }
}
