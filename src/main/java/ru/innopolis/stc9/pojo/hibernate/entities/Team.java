package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "team")
public class Team {
    private long id;
    private String fullName;
    private String shortName;
    private int yearCurrent;
    private int yearStart;
    private int yearTotal;
    private Set<Student> studentSet;
    private Set<Subject> subjectSet;

    public Team() {
    }

    public Team(String fullName, String shortName, int yearCurrent, int yearStart, int yearTotal) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.yearCurrent = yearCurrent;
        this.yearStart = yearStart;
        this.yearTotal = yearTotal;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getYearCurrent() {
        return yearCurrent;
    }

    public void setYearCurrent(int yearCurrent) {
        this.yearCurrent = yearCurrent;
    }

    public int getYearStart() {
        return yearStart;
    }

    public void setYearStart(int yearStart) {
        this.yearStart = yearStart;
    }

    public int getYearTotal() {
        return yearTotal;
    }

    public void setYearTotal(int yearTotal) {
        this.yearTotal = yearTotal;
    }

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "teams_subjects",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }
}
