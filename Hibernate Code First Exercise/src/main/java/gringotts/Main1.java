package gringotts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main1 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("code_first_test");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        TableWizard tableWizard = new TableWizard("Some", "One", 25, LocalDateTime.now());

        entityManager.persist(tableWizard);
        entityManager.getTransaction().commit();
    }
}
