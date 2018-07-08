package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "mark")
public class Mark {

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
}
