import entities.shampoo.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.ProductionBatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_test");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        BasicLabel label = new BasicLabel("red");
        ProductionBatch productionBatch = new ProductionBatch(LocalDate.now());
        BasicShampoo shampoo = new BasicShampoo("SomeName", label, productionBatch);

        BasicIngredient basicIngredient = new BasicIngredient(150, "Something");
        BasicIngredient basicIngredient1 = new BasicIngredient(16, "B1");

        shampoo.addIngredients(basicIngredient);
        shampoo.addIngredients(basicIngredient1);

        entityManager.persist(basicIngredient);
        entityManager.persist(basicIngredient1);

        entityManager.persist(productionBatch);
        entityManager.persist(label);

        entityManager.persist(shampoo);
        entityManager.getTransaction().commit();
    }
}
