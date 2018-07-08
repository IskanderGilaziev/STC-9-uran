package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "subject")
public class Subject {

    private long id;

    @Column(nullable = false)
    private String name;

    private Set<Speciality> specialtySet = new HashSet<>();

    private Set<Lesson> lessonSet = new HashSet<Lesson>();

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
    @JoinTable(name = "specialty_subject",
            joinColumns = @JoinColumn(name = "specialty_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public Set<Speciality> getSpecialtySet() {
        return specialtySet;
    }

    public void setSpecialtySet(Set<Speciality> specialtySet) {
        this.specialtySet = specialtySet;
    }

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }
}
