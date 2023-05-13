import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _4_EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();


        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e " +
                " WHERE e.department.id = 6 " +
                " ORDER BY e.salary ASC , e.id ASC ", Employee.class).getResultList();

        printEmployeesWithInfo(resultList);

    }

    private static void printEmployeesWithInfo(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.printf("%s %s from Research and Development - $%.2f%n",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getSalary());
        }
    }
}
