package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "lesson")
public class Lesson implements Serializable {
    private long id;
    /**
     * Преподаватель, что провел урок
     */
    @Column(nullable = false)
    private Person teacher;
    /**
     * Дата проведения урока
     */
    @Column(nullable = false)
    private Date date;
    /**
     * Тема урока
     */
    @Column(nullable = false)
    private String theme;
    /**
     * Домашнее задание, что задано на уроке
     */
    @Column(nullable = true)
    private String homework;
    /**
     * По какой дисциплине проводился урок
     */
    @Column(nullable = false)
    private Subject subject;

    /**
     * У какой группы проводился урок
     */
    @Column(nullable = false)
    private Team team;
    /**
     * Список оценок, которые были выставлены на данном уроке
     */
    private Set<Mark> markSet = new HashSet<>();

    public Lesson() {
    }

    public Lesson(Date date, String theme, String homework) {
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
    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    //    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "mark", fetch = FetchType.LAZY)
    public Set<Mark> getMarkSet() {
        return markSet;
    }

    public void setMarkSet(Set<Mark> markSet) {
        this.markSet = markSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (id != lesson.id) return false;
        if (teacher != null ? !teacher.equals(lesson.teacher) : lesson.teacher != null) return false;
        if (date != null ? !date.equals(lesson.date) : lesson.date != null) return false;
        if (theme != null ? !theme.equals(lesson.theme) : lesson.theme != null) return false;
        if (homework != null ? !homework.equals(lesson.homework) : lesson.homework != null) return false;
        if (subject != null ? !subject.equals(lesson.subject) : lesson.subject != null) return false;
        if (team != null ? !team.equals(lesson.team) : lesson.team != null) return false;
        return markSet != null ? markSet.equals(lesson.markSet) : lesson.markSet == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (homework != null ? homework.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (markSet != null ? markSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", date=" + date +
                ", theme='" + theme + '\'' +
                ", homework='" + homework + '\'' +
                ", subject=" + subject +
                ", team=" + team +
                ", markSet=" + markSet +
                '}';
    }
}
