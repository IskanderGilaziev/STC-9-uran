package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "team")
public class Team implements Serializable {
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
    private Set<Person> personSet = new HashSet<>();
//    /**
//     * Список состоявшихся уроков группы
//     */
//    private Set<Lesson> lessonSet = new HashSet<>();
    /**
     * Специальность, по которой обучается группа.
     * В ней же определены предметы, которые они должны будут изучить.
     */
    @Column(nullable = true)
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

    /*@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }*/

    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (yStart != team.yStart) return false;
        return nameGroup != null ? nameGroup.equals(team.nameGroup) : team.nameGroup == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nameGroup != null ? nameGroup.hashCode() : 0);
        result = 31 * result + yStart;
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", nameGroup='" + nameGroup + '\'' +
                ", yStart=" + yStart +
                '}';
    }
}
