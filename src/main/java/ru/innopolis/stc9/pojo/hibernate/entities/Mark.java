package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "mark")
public class Mark implements Serializable {

    private long id;

    @Column(nullable = false)
    private int mark;

    @Column(nullable = true)
    private String comment;

    @Column(nullable = true)
    private Lesson lesson;

    @Column(nullable = true)
    private Person student;

    public Mark() {
    }

    public Mark(int mark) {
        this.mark = mark;
    }

    public Mark(int mark, String comment) {
        this.mark = mark;
        this.comment = comment;
    }

    @Id
    @SequenceGenerator(name = "markSeq", sequenceName = "mark_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "markSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark1 = (Mark) o;

        if (id != mark1.id) return false;
        if (mark != mark1.mark) return false;
        if (comment != null ? !comment.equals(mark1.comment) : mark1.comment != null) return false;
        if (lesson != null ? !lesson.equals(mark1.lesson) : mark1.lesson != null) return false;
        return student != null ? student.equals(mark1.student) : mark1.student == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + mark;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (lesson != null ? lesson.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                ", lesson=" + lesson +
                ", student=" + student +
                '}';
    }
}
