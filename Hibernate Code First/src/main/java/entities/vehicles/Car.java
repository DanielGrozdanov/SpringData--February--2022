package entities.vehicles;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicles {
    private int seats;

    @OneToOne()
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Car() {
    }

    public Car(String type, String model, BigDecimal price, String fuelType, int seats) {
        super(type, model, price, fuelType);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }
}
