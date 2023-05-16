package hospitalDatabase;

import com.sun.xml.bind.v2.model.core.ID;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "diagnose", targetEntity = Visitation.class)
    private List<Visitation> visitation;

    @OneToMany(mappedBy = "diagnose", targetEntity = DiagnoseComment.class)
    private List<DiagnoseComment> comment;

    public Diagnose() {
    }

    public Diagnose(String name) {
        this.name = name;
        this.comment = new ArrayList<>();
        this.visitation = new ArrayList<>();
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

    public List<Visitation> getVisitation() {
        return visitation;
    }

    public void setVisitation(List<Visitation> visitation) {
        this.visitation = visitation;
    }

    public List<DiagnoseComment> getComment() {
        return comment;
    }

    public void setComment(List<DiagnoseComment> comment) {
        this.comment = comment;
    }

    public void addComment(DiagnoseComment comment) {
        this.comment.add(comment);
    }
}
