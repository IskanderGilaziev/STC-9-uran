package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "speciality")
public class Speciality {

    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long semesterCount;

    public Speciality() {
    }

    public Speciality(long id, String name, long semesterCount) {
        this.id = id;
        this.name = name;
        this.semesterCount = semesterCount;
    }

    public Speciality(String name, long semesterCount) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        if (id != that.id) return false;
        if (semesterCount != that.semesterCount) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (semesterCount ^ (semesterCount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", semesterCount=" + semesterCount +
                '}';
    }
}
