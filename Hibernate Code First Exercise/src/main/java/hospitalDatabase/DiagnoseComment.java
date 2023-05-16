package hospitalDatabase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "diagnoses_comment")
public class DiagnoseComment extends Comment {

    @ManyToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Diagnose diagnose;

    public DiagnoseComment() {

    }

    public DiagnoseComment(String text) {
        super(text);
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }
}
