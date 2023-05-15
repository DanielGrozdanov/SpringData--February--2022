package entities.vehicles;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;

    @OneToMany(mappedBy = "company", targetEntity = Plane.class)
    private Set<Plane> plane;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
        this.plane = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Plane> getPlane() {
        return Collections.unmodifiableSet(plane);
    }

    public void addPlane(Plane plane) {
        this.plane.add(plane);
    }

    public void setPlane(Set<Plane> plane) {
        this.plane = plane;
    }
}
