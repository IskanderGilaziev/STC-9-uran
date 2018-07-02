package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "student")
public class Student {
    private long id;
    private Person person;
    private Team team;
    private Set<Mark> markSet;

    public Student() {
    }

    public Student(Person person) {
        this.person = person;
    }

    public Student(Person person, Team team) {
        this.person = person;
        this.team = team;
    }

    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "studentSeq", sequenceName = "STUDENT_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    public Set<Mark> getMarkSet() {
        return markSet;
    }

    public void setMarkSet(Set<Mark> markSet) {
        this.markSet = markSet;
    }
}
