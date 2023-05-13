import entities.Employee;

import javax.persistence.*;
import java.util.Scanner;

public class _2_ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String[] name = scanner.nextLine().split(" ");

        TypedQuery<Employee> query = em.createQuery("FROM Employee WHERE firstName =: firstName and  lastName =: lastName", Employee.class);
        query.setParameter("firstName", name[0]);
        query.setParameter("lastName", name[1]);

        em.getTransaction().begin();
        try {
            query.getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");
        }
    }
}
