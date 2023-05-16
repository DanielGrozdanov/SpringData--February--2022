package salesDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("code_first_test");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Customer customer = new Customer("John", "John@gmail.com", "2342-4543-2134-2550");
        Product product = new Product("Joystick", 1, 12.99);
        StoreLocation storeLocation = new StoreLocation("First Shelf");
        Sale sale = new Sale(product, customer, storeLocation, LocalDate.now());

        customer.addSale(sale);
        product.addSales(sale);
        storeLocation.addSales(sale);

        em.persist(customer);
        em.persist(product);
        em.persist(storeLocation);
        em.persist(sale);

        em.getTransaction().commit();

    }
}
