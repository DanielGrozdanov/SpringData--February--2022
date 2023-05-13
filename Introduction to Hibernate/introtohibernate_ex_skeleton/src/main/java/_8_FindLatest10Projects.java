import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class _8_FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        List<Project> resultList = entityManager.createQuery("SELECT p FROM Project p " +
                " ORDER BY p.startDate DESC", Project.class).setMaxResults(10).getResultList();


        resultList.stream().sorted(Comparator.comparing(Project::getName)).forEach(project -> {
            String builder = "Project name: " + project.getName() + System.lineSeparator() +
                    "        Project Description: " + project.getDescription() + System.lineSeparator() +
                    "        Project Start Date: " + project.getStartDate() + System.lineSeparator() +
                    "        Project End Date: " + project.getEndDate();
            System.out.println(builder);
        });
    }
}
