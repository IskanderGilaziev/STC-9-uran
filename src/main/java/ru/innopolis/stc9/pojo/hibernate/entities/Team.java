package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@Entity
@Table(name = "team")
public class Team {
    private long id;

    @Column(nullable = false)
    private String nameGroup;

    @Column(nullable = false)
    private String program;

    @Column(nullable = false)
    private Set<Person> personSet;

    @Column(nullable = false)
    private Set<Lesson> lessonSet = new HashSet<>();


    public Team() {
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public Set<Person> getPersonSet() {
        return personSet;
    }

    public void setPersonSet(Set<Person> personSet) {
        this.personSet = personSet;
    }

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id &&
                Objects.equals(nameGroup, team.nameGroup) &&
                Objects.equals(program, team.program) &&
                Objects.equals(personSet, team.personSet) &&
                Objects.equals(lessonSet, team.lessonSet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nameGroup, program, personSet, lessonSet);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", nameGroup='" + nameGroup + '\'' +
                ", program='" + program + '\'' +
                ", personSet=" + personSet +
                ", lessonSet=" + lessonSet +
                '}';
    }
}
