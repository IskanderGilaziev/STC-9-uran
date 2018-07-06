package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@Entity
@Table(name = "subject")
public class Subject {

    private long id;

    @Column(nullable = false)
    private String name;

    private Set<Program> programSet = new HashSet<Program>();

    private Set<Lesson> lessonList = new HashSet<Lesson>();

    public Subject() {
    }

    public Subject(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject(String name) {
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "subjectSeq", sequenceName = "subject_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subjectSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "program_subject",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public Set<Program> getProgramSet() {
        return programSet;
    }

    public void setProgramSet(Set<Program> programSet) {
        this.programSet = programSet;
    }

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    public Set<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(Set<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id &&
                Objects.equals(name, subject.name) &&
                Objects.equals(programSet, subject.programSet) &&
                Objects.equals(lessonList, subject.lessonList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, programSet, lessonList);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", programSet=" + programSet +
                ", lessonList=" + lessonList +
                '}';
    }
}
