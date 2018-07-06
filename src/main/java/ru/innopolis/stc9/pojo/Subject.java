package ru.innopolis.stc9.pojo;

public class Subject {

  private long id;
  private String name;

  public Subject(long id, String name) {
    this.id = id;
    this.name = name;
  }


    public Subject(String name) {
    this.name = name;
  }

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Subject subject = (Subject) o;

    if (id != subject.id) return false;
    return name != null ? name.equals(subject.name) : subject.name == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Subject{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
