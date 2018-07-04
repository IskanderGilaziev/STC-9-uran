package ru.innopolis.stc9.pojo.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "programs")
public class Program {

  private long id;
  private Speciality speciality;
  private long semester;
  private Subject subject;
  private long hours;

    public Program() {
    }

  public Program(long id, Speciality speciality, long semester, Subject subject, long hours) {
    this.id = id;
    this.speciality = speciality;
    this.semester = semester;
    this.subject = subject;
    this.hours = hours;
  }

  public Program(Speciality speciality, long semester, Subject subject, long hours) {
    this.speciality = speciality;
    this.semester = semester;
    this.subject = subject;
    this.hours = hours;
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

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public Speciality getSpecialty() {
    return speciality;
  }

  public void setSpecialty(Speciality speciality) {
    this.speciality = speciality;
  }

  public long getSemester() {
    return semester;
  }

  public void setSemester(long semester) {
    this.semester = semester;
  }

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public Subject getSubject() {
    return subject;
  }

  public void setSubject( Subject subject ) {
    this.subject = subject;
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
    return subject != null ? subject.equals(program.subject) : program.subject == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
    result = 31 * result + (int) (semester ^ (semester >>> 32));
    result = 31 * result + (subject != null ? subject.hashCode() : 0);
    result = 31 * result + (int) (hours ^ (hours >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Program{" +
            "id=" + id +
            ", speciality=" + speciality +
            ", semester=" + semester +
            ", subject=" + subject +
            ", hours=" + hours +
            '}';
  }
}
