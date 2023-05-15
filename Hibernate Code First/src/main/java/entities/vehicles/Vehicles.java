package entities.vehicles;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String type;
    private String model;
    private BigDecimal price;
    private String fuelType;


    protected Vehicles() {
    }

    protected Vehicles(String type, String model, BigDecimal price, String fuelType) {
        this.type = type;
        this.model = model;
        this.price = price;
        this.fuelType = fuelType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
