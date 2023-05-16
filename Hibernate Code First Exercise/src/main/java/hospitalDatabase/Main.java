package hospitalDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("code_first_test");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

//        Patient patient = new Patient("Pesho", "Peshev");
//
//        Diagnose diagnose = new Diagnose("Flue");
//
//        DiagnoseComment diagnoseComment = new DiagnoseComment("Very Bad Flue");
//        diagnose.addComment(diagnoseComment);
//        diagnoseComment.setDiagnose(diagnose);
//
//        Medicament paracetamol = new Medicament("Paracetamol");
//        Medicament acustivum = new Medicament("Acustivum");
//
//        Visitation visitation = new Visitation(LocalDate.now());
//        visitation.setPatient(patient);
//        visitation.setDiagnose(diagnose);
//        visitation.setMedicament(Arrays.asList(paracetamol, acustivum));
//
//
//        VisitationComment visitationComment = new VisitationComment("Cure was found");
//        visitation.setComment(List.of(visitationComment));
//        visitationComment.setVisitation(visitation);
//
//        diagnose.setComment(List.of(diagnoseComment));
//
//        patient.setVisitations(List.of(visitation));
//        em.persist(patient);
//
//        em.persist(diagnose);
//        em.persist(diagnoseComment);
//        em.persist(paracetamol);
//        em.persist(acustivum);
//
//        em.persist(visitation);
//        em.persist(visitationComment);
//
//        em.getTransaction().commit();

        System.out.println("Welcome to Hospital Database");

        System.out.println("In order to extract information, please enter a number from the bellow options");
        System.out.println("1.Information about all patients in the hospital");
        System.out.println("2.Add new patient to the database");

        int option = Integer.parseInt(scanner.nextLine());


        switch (option) {
            case 1:
                getAllPatients(em);
                break;
            case 2:
                registerNewPatient(em);
                break;
        }
        em.getTransaction().commit();
    }

    private static void registerNewPatient(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the patients details as per requested bellow");
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        Patient patient = new Patient(firstName, lastName);
        em.persist(patient);

        System.out.printf("%s %s successfully added to the Hospital Database ", firstName, lastName);
    }

    private static void getAllPatients(EntityManager em) {
        List<Patient> patients = em.createQuery("FROM Patient p ", Patient.class).getResultList();
        patients.forEach(System.out::println);
    }
}
