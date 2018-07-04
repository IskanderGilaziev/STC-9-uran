package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@Entity
@Table(name = "speciality")
public class Speciality {

    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long semesterCount;

    @Column(nullable = false)
    private Set<Program> programs = new HashSet<Program>();

    public Speciality() {
    }


    public Speciality(long id, String name, long semesterCount, Program program) {
        this.id = id;
        this.name = name;
        this.semesterCount = semesterCount;
    }


    public Speciality(String name, long semesterCount, Program program) {
        this.name = name;
        this.semesterCount = semesterCount;
    }

    @Id
    @SequenceGenerator(name = "specialitySeq", sequenceName = "speciality_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialitySeq")
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

    public long getSemesterCount() {
        return semesterCount;
    }

    public void setSemesterCount(long semesterCount) {
        this.semesterCount = semesterCount;
    }
    @OneToMany(mappedBy = "programs", fetch = FetchType.LAZY)
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

        Speciality that = (Speciality) o;

        return id == that.id &&
                Objects.equals(name, that.name) &&
                semesterCount == that.semesterCount &&
                Objects.equals(programs, that.programs);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, semesterCount, programs);
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", semesterCount=" + semesterCount +
                ", Program=" + programs.toString() +
                '}';
    }
}
