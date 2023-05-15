package entities.vehicles;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicles {

    private double loadCapacity;

    public Truck() {
    }

    public Truck(String type, String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(type, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }
}
