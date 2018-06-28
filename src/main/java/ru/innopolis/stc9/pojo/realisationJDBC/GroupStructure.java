package ru.innopolis.stc9.pojo.realisationJDBC;

public class GroupStructure {

  private long id;
  private long studentItem;
  private long groupItem;

  public GroupStructure(long id, long studentItem, long groupItem) {
    this.id = id;
    this.studentItem = studentItem;
    this.groupItem = groupItem;
  }

  public GroupStructure(long studentItem, long groupItem) {
    this.studentItem = studentItem;
    this.groupItem = groupItem;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getStudentItem() {
    return studentItem;
  }

  public void setStudentItem(long studentItem) {
    this.studentItem = studentItem;
  }


  public long getGroupItem() {
    return groupItem;
  }

  public void setGroupItem(long groupItem) {
    this.groupItem = groupItem;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupStructure that = (GroupStructure) o;

    if (id != that.id) return false;
    if (studentItem != that.studentItem) return false;
    return groupItem == that.groupItem;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (int) (studentItem ^ (studentItem >>> 32));
    result = 31 * result + (int) (groupItem ^ (groupItem >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "GroupStructure{" +
            "id=" + id +
            ", studentItem=" + studentItem +
            ", groupItem=" + groupItem +
            '}';
  }
}
