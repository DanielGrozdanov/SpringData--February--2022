import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class _7_GetEmployeeWithProject {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scanner = new Scanner(System.in);
        int employeeId = Integer.parseInt(scanner.nextLine());
        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e " +
                " WHERE e.id = : employeeId ", Employee.class).setParameter("employeeId", employeeId).getResultList();


        resultList.forEach(employee -> {
            System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
            employee.getProjects()
                    .stream()
                    .sorted(Comparator.comparing(Project::getName)).forEach(p -> System.out.printf("    %s%n", p.getName()));
        });

        entityManager.getTransaction().commit();
    }
}
