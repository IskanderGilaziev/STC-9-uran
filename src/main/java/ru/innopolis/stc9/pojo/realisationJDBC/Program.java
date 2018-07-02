package ru.innopolis.stc9.pojo.realisationJDBC;


import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

public class Program {

  private long id;
  private Speciality specialty;
  private long semester;
  private Subject subject;
  private long hours;

  public Program(long id, Speciality specialty, long semester, Subject subject, long hours) {
    this.id = id;
    this.specialty = specialty;
    this.semester = semester;
    this.subject = subject;
    this.hours = hours;
  }

  public Program(Speciality specialty, long semester, Subject subject, long hours) {
    this.specialty = specialty;
    this.semester = semester;
    this.subject = subject;
    this.hours = hours;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Speciality getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Speciality specialty) {
    this.specialty = specialty;
  }

  public long getSemester() {
    return semester;
  }

  public void setSemester(long semester) {
    this.semester = semester;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
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
    if (specialty != null ? !specialty.equals(program.specialty) : program.specialty != null) return false;
    return subject != null ? subject.equals(program.subject) : program.subject == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
    result = 31 * result + (int) (semester ^ (semester >>> 32));
    result = 31 * result + (subject != null ? subject.hashCode() : 0);
    result = 31 * result + (int) (hours ^ (hours >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "Program{" +
            "id=" + id +
            ", specialty=" + specialty +
            ", semester=" + semester +
            ", subject=" + subject +
            ", hours=" + hours +
            '}';
  }
}
