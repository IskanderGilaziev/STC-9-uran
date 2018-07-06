package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Component
@Entity
@Table(name = "lesson")
public class Lesson {
    private long id;

    @Column(nullable = false)
    private Person person;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = true)
    private String homework;

    @JoinColumn(name = "subjectId", nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private Team team;

    public Lesson() {
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

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                Objects.equals(person, lesson.person) &&
                Objects.equals(date, lesson.date) &&
                Objects.equals(theme, lesson.theme) &&
                Objects.equals(homework, lesson.homework) &&
                Objects.equals(subject, lesson.subject) &&
                Objects.equals(team, lesson.team);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, person, date, theme, homework, subject, team);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", person=" + person +
                ", date=" + date +
                ", theme='" + theme + '\'' +
                ", homework='" + homework + '\'' +
                ", subject=" + subject +
                ", team=" + team +
                '}';
    }
}
