package hospitalDatabase;

import jdk.jshell.Diag;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

@MappedSuperclass
public abstract class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    public Comment() {

    }

    public Comment(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
