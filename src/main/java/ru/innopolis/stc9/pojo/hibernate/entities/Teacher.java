package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "teacher")
public class Teacher {
    private long id;
    private Person person;
    private Set<Subject> subjectSet;
    private Set<Mark> markSet;

    public Teacher() {
    }

    public Teacher(Person person) {
        this.person = person;
    }

    public Teacher(Person person, Set<Subject> subjectSet) {
        this.person = person;
        this.subjectSet = subjectSet;
    }

    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "teacherSeq", sequenceName = "TEACHER_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacherSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne(optional = false, mappedBy = "teacher")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "teachers_subjects",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    public Set<Mark> getMarkSet() {
        return markSet;
    }

    public void setMarkSet(Set<Mark> markSet) {
        this.markSet = markSet;
    }
}
