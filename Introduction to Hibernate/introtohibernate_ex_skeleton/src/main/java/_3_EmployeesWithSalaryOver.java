import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class _3_EmployeesWithSalaryOver {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();

        TypedQuery<String> query = entityManager.createQuery("SELECT e.firstName FROM Employee e WHERE e.salary > 50000", String.class);
        List<String> resultList = query.getResultList();
        resultList.forEach(System.out::println);
    }
}
