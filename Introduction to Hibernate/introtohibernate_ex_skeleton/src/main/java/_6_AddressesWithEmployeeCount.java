import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _6_AddressesWithEmployeeCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        String query = "SELECT ad FROM Address ad" +
                " ORDER BY size(ad.employees) DESC , ad.town.id ASC";

        List<Address> resultList = entityManager.createQuery(query).setMaxResults(10).getResultList();


        for (Address address : resultList) {
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(), address.getTown().getName(),address.getEmployees().size());
        }

        entityManager.getTransaction().commit();

    }
}
