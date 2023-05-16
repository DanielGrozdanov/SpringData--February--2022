package hospitalDatabase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visitation_comments")
public class VisitationComment extends Comment {

    @ManyToOne()
    @JoinColumn(name = "visitation_id", referencedColumnName = "id")
    private Visitation visitation;

    public VisitationComment() {
    }

    public VisitationComment(String text) {
        super(text);
    }

    public Visitation getVisitation() {
        return visitation;
    }

    public void setVisitation(Visitation visitation) {
        this.visitation = visitation;
    }
}
