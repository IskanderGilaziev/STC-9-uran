package ru.innopolis.stc9.pojo.hibernate.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    private long id;
    private String name;
    private Set<Program> programs = new HashSet<Program>();

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
    @SequenceGenerator(name = "subjectSeq", sequenceName = "subject_SEQUENCE"  , allocationSize = 1)
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
    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject that = (Subject) o;

        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(programs, that.programs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, programs);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
