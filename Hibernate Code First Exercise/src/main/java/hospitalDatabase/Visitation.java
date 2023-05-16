package hospitalDatabase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToMany()
    private List<Medicament> medicament;

    @ManyToOne()
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Diagnose diagnose;

    @OneToMany(mappedBy = "visitation", targetEntity = VisitationComment.class)
    private List<VisitationComment> comment;


    public Visitation() {
    }

    public Visitation(LocalDate date) {
        this.date = date;
        this.comment = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Medicament> getMedicament() {
        return medicament;
    }

    public void setMedicament(List<Medicament> medicament) {
        this.medicament = medicament;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public List<VisitationComment> getComment() {
        return comment;
    }

    public void setComment(List<VisitationComment> comment) {
        this.comment = comment;
    }

    public void addVisitationComment(VisitationComment comment) {
        this.comment.add(comment);
    }

    public void addMedication(Medicament medicament) {
        this.medicament.add(medicament);
    }
}
