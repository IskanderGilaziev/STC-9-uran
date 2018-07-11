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

  public Program() { }

  public Program(long id, Speciality speciality, long semester, Subject subject, long hours) {
    this.id = id;
    this.speciality = speciality;
    this.semester = semester;
//    this.subject = subject;
    this.hours = hours;
  }
//
  public Program(Speciality speciality, long semester, Subject subject, long hours) {
    this.speciality = speciality;
    this.semester = semester;
//    this.subject = subject;
    this.hours = hours;
  }

  @Id
  @Column(unique = true)
  @SequenceGenerator(name = "programSeq", sequenceName = "PROGRAM_SEQUENCE" , allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "programSeq")
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public Speciality getSpeciality() {
    return speciality;
  }

  public void setSubjects(Set<Subject> subjects) {
    this.subjects = subjects;
  }

  public void setSpeciality(Speciality speciality) {
    this.speciality = speciality;
  }


//  public Speciality getSpecialty() {
//    return speciality;
//  }
//
//  public void setSpecialty(Speciality speciality) {
//    this.speciality = speciality;
//  }

  public long getSemester() {
    return semester;
  }

  public void setSemester(long semester) {
    this.semester = semester;
  }

  @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "program_subject",
          joinColumns = @JoinColumn(name = "program_id"),
          inverseJoinColumns = @JoinColumn(name = "subject_id"))
  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubject(Set<Subject> subjects) {
    this.subjects = subjects;
  }

//  public Subject getSubject() {
//    return subject;
//  }
//
  public void setSubject( Subject subject ) {
    this.subjects.add(subject);
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

    if (id != program.id) return false;
    if (semester != program.semester) return false;
    if (hours != program.hours) return false;
    if (speciality != null ? !speciality.equals(program.speciality) : program.speciality != null) return false;
    return subjects != null ? subjects.equals(program.subjects) : program.subjects == null;
  }

  @Override
  public int hashCode() {return Objects.hash(id, semester, hours);
  }

  @Override
  public String toString() {
    return " ";
  }
}
