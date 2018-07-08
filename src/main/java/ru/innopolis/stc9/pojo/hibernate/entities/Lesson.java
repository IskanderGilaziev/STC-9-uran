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
}
