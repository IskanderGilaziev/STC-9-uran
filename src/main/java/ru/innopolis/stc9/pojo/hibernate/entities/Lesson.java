package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "lesson")
public class Lesson {
    private long id;

    @Column(nullable = false)
    private Person teacher;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = true)
    private String homework;

    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;
    /**
     * У какой группы проводился урок
     */
    @Column(nullable = false)
    private Team team;

    private Set<Performance> performances = new HashSet<>();

    public Lesson() {
    }

    public Lesson(Subject subject, Person teacher, Date date, String theme, String homework) {
        this.subject = subject;
        this.teacher = teacher;
        this.date = date;
        this.theme = theme;
        this.homework = homework;
    }

    public Lesson(Subject subject, Person teacher, Date date, String theme) {
        this.subject = subject;
        this.teacher = teacher;
        this.date = date;
        this.theme = theme;
    }

    public Lesson(long id, Subject subject, Person teacher, Date date, String theme, String homework) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.date = date;
        this.theme = theme;
        this.homework = homework;
    }

    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "lessonSeq", sequenceName = "LESSON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessonSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    public Set<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(Set<Performance> performances) {
        this.performances = performances;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
//                ", teacher=" + teacher +
                ", date=" + date +
                ", theme='" + theme + '\'' +
                ", homework='" + homework + '\'' +
                '}';
    }
}
