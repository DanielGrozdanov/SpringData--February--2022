import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class _1_ChangeCasingOfTowns {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
        List<Town> resultList = em.createQuery("FROM Town WHERE length(name) > 5 ", Town.class).getResultList();

        em.getTransaction().begin();

        for (Town town : resultList) {
            em.detach(town);
        }


        em.createQuery("UPDATE Town t SET t.name = UPPER(t.name)").executeUpdate();
        resultList.forEach(em::merge);
        em.getTransaction().commit();
    }
}
