package entities.vehicles;

import javax.persistence.*;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String number;

    @OneToOne(mappedBy = "plateNumber", targetEntity = Car.class)
    private Car car;

    public PlateNumber() {

    }

    public PlateNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
