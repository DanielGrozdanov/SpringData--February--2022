package entities.vehicles;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicles {

    private int passengerCapacity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public Plane() {
    }

    public Plane(String type, String model, BigDecimal price, String fuelType, int passengerCapacity) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
