package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "team")
public class Team {
    private long id;
    /**
     * Название группы
     */
    @Column(nullable = false)
    private String nameGroup;

    /**
     * год начала обучения группы
     */
    @Column(nullable = false)
    private int yStart;
    /**
     * Список студентов группы
     */
    private Set<Person> personSet;
    /**
     * Список состоявшихся уроков группы
     */
    private Set<Lesson> lessonSet = new HashSet<>();
    /**
     * Специальность, по которой обучается группа.
     * В ней же определены предметы, которые они должны будут изучить.
     */
    private Speciality speciality;

    public Team() {
    }

    public Team(String nameGroup, int yStart) {
        this.nameGroup = nameGroup;
        this.yStart = yStart;
    }

    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "teamSeq", sequenceName = "TEAM_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    //    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
