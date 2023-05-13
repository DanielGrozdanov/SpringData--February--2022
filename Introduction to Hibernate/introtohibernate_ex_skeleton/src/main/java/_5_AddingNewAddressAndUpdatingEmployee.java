import entities.Address;
import entities.Employee;
import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Scanner;

public class _5_AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        //Hibernate
//        Configuration configuration = new Configuration();
//        configuration.configure();
//
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        Town town = session.get(Town.class, 1);
//
//        System.out.println(town);
//        session.getTransaction().commit();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String addressText = "Vitoshka 15";
        String employeeLastName = scanner.nextLine();

        Address address = new Address();
        address.setText(addressText);
        address.setTown(null);
        entityManager.persist(address);

//        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName =: lastName", Employee.class);
//        query.setParameter("lastName", employeeLastName);
//
//        Employee employee = query.getSingleResult();
//
//        employee.setAddress(address);
//
//        entityManager.persist(employee);

        entityManager.createQuery("UPDATE Employee e SET e.address =: address WHERE e.lastName =: lastName")
                .setParameter("address", address)
                .setParameter("lastName", employeeLastName).executeUpdate();


        entityManager.getTransaction().commit();
    }
}
