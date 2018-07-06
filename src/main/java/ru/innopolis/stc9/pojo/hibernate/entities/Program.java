package ru.innopolis.stc9.pojo.hibernate.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "program")
public class Program {

    private long id;
    private Speciality speciality;
    private long semester;
    private Set<Subject> subjects = new HashSet<>();
    private long hours;

    public Program() {
    }


    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "programSeq", sequenceName = "PROGRAM_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "programSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public long getSemester() {
        return semester;
    }

    public void setSemester(long semester) {
        this.semester = semester;
    }

    @ManyToMany
    @JoinTable(name = "program_subject",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setSubject(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Program program = (Program) o;
        return id == program.id &&
                semester == program.semester &&
                hours == program.hours &&
                Objects.equals(speciality, program.speciality) &&
                Objects.equals(subjects, program.subjects);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, speciality, semester, subjects, hours);
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", speciality=" + speciality +
                ", semester=" + semester +
                ", subjects=" + subjects +
                ", hours=" + hours +
                '}';
    }
}
