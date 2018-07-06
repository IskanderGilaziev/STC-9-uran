package ru.innopolis.stc9.pojo;

public class TeacherSubject {

  private long id;
  private long teacherItem;
  private long subjectItem;
  private String teacherName;
  private String subjectName;

    public TeacherSubject() {
    }

    public TeacherSubject(long id, long teacherItem, long subjectItem) {
    this.id = id;
    this.subjectItem = subjectItem;
    this.teacherItem = teacherItem;
  }

    public TeacherSubject(long id, long teacherItem, String teacherName, long subjectItem, String subjectName) {
        this.id = id;
        this.teacherItem = teacherItem;
        this.teacherName = teacherName;
        this.subjectItem = subjectItem;
        this.subjectName = subjectName;
    }

  public TeacherSubject(long teacherItem, long subjectItem) {
    this.teacherItem = teacherItem;
    this.subjectItem = subjectItem;
  }

  public TeacherSubject(long id, String subjectName, String teacherName) {
    this.id = id;
    this.teacherName = teacherName;
    this.subjectName = subjectName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getTeacherItem() {
    return teacherItem;
  }

  public void setTeacherItem(long teacherItem) {
    this.teacherItem = teacherItem;
  }

  public long getSubjectItem() {
    return subjectItem;
  }

  public void setSubjectItem(long subjectItem) {
    this.subjectItem = subjectItem;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherSubject that = (TeacherSubject) o;

        if (id != that.id) return false;
        if (teacherItem != that.teacherItem) return false;
        if (subjectItem != that.subjectItem) return false;
        if (teacherName != null ? !teacherName.equals(that.teacherName) : that.teacherName != null) return false;
        return subjectName != null ? subjectName.equals(that.subjectName) : that.subjectName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (teacherItem ^ (teacherItem >>> 32));
        result = 31 * result + (int) (subjectItem ^ (subjectItem >>> 32));
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TeacherSubject{" +
                "id=" + id +
                ", teacherItem=" + teacherItem +
                ", subjectItem=" + subjectItem +
                ", teacherName='" + teacherName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }
}
