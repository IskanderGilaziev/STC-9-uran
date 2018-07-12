package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "performance")
public class Performance {
    private long id;

    @JoinColumn(name = "personId", nullable = false)
    private Person person;

    @JoinColumn(name = "lessonId", nullable = false)
    private Lesson lesson;

    @Column(nullable = false)
    private int mark;

    @Column(nullable = false)
    private boolean attendance;


    public Performance() {
    }

    public Performance(Person person, Lesson lesson, int mark, boolean attendance) {
        this.person = person;
        this.lesson = lesson;
        this.mark = mark;
        this.attendance = attendance;
    }

    public Performance(Person person, Lesson lesson, int mark) {
        this.person = person;
        this.lesson = lesson;
        this.mark = mark;
    }

    public Performance(long id, Person person, Lesson lesson, int mark, boolean attendance) {
        this.id = id;
        this.person = person;
        this.lesson = lesson;
        this.mark = mark;
        this.attendance = attendance;
    }

    @Id
    @Column(name = "performance_id", unique = true)
    @SequenceGenerator(name = "performanceSeq", sequenceName = "PERFORMANCE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performanceSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }
}