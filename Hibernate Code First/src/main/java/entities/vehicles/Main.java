package entities.vehicles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_test");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Bike bike = new Bike("Track", "Kawasaki", BigDecimal.valueOf(2900), "Gasoline");

        Car car = new Car("Sedan", "Mercedes-Benz", BigDecimal.valueOf(5400), "Diesel", 4);
        PlateNumber plateNumber = new PlateNumber("CO3912CC");

        Plane plane1 = new Plane("Airliner", "Boeing", BigDecimal.valueOf(12000), "Kerosene", 500);
        Plane plane2 = new Plane("Airliner", "Other", BigDecimal.valueOf(11050), "Kerosene", 250);
        Company company = new Company("Turkish Airlines");
        company.addPlane(plane1);
        company.addPlane(plane2);


        Truck truck = new Truck("Tanker", "Volvo", BigDecimal.valueOf(8000), "Diesel", 5000);


        entityManager.persist(plateNumber);
        entityManager.persist(bike);

        entityManager.persist(company);
        car.setPlateNumber(plateNumber);

        entityManager.persist(car);

        plane1.setCompany(company);
        plane2.setCompany(company);

        entityManager.persist(plane1);
        entityManager.persist(plane2);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
    }
}
