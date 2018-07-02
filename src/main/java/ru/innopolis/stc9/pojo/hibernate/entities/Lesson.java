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
    private long scheduleItem;

    @Column(nullable = false)
    private long teacherItem;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = true)
    private String homework;

    public Lesson() {
    }

    public Lesson(long scheduleItem, long teacherItem, Date date, String theme, String homework) {
        this.scheduleItem = scheduleItem;
        this.teacherItem = teacherItem;
        this.date = date;
        this.theme = theme;
        this.homework = homework;
    }

    public Lesson(long scheduleItem, long teacherItem, Date date, String theme) {
        this.scheduleItem = scheduleItem;
        this.teacherItem = teacherItem;
        this.date = date;
        this.theme = theme;
    }

    public Lesson(long id, long scheduleItem, long teacherItem, Date date, String theme, String homework) {
        this.id = id;
        this.scheduleItem = scheduleItem;
        this.teacherItem = teacherItem;
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

    public long getScheduleItem() {
        return scheduleItem;
    }

    public void setScheduleItem(long scheduleItem) {
        this.scheduleItem = scheduleItem;
    }

    public long getTeacherItem() {
        return teacherItem;
    }

    public void setTeacherItem(long teacherItem) {
        this.teacherItem = teacherItem;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                scheduleItem == lesson.scheduleItem &&
                teacherItem == lesson.teacherItem &&
                Objects.equals(date, lesson.date) &&
                Objects.equals(theme, lesson.theme) &&
                Objects.equals(homework, lesson.homework);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scheduleItem, teacherItem, date, theme, homework);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", scheduleItem=" + scheduleItem +
                ", teacherItem=" + teacherItem +
                ", date=" + date +
                ", theme='" + theme + '\'' +
                ", homework='" + homework + '\'' +
                '}';
    }
}
