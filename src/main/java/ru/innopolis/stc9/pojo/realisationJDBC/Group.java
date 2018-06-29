package ru.innopolis.stc9.pojo.realisationJDBC;

public class Group {

  private long id;
  private long curSemesterEducation;
    private Program program;

    public Group(long id, long curSemesterEducation, Program program) {
    this.id = id;
    this.curSemesterEducation = curSemesterEducation;
    this.program = program;
  }

    public Group(long curSemesterEducation, Program program) {
    this.curSemesterEducation = curSemesterEducation;
    this.program = program;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCurSemesterEducation() {
    return curSemesterEducation;
  }

  public void setCurSemesterEducation(long curSemesterEducation) {
    this.curSemesterEducation = curSemesterEducation;
  }


    public Program getProgram() {
    return program;
  }

    public void setProgram(Program program) {
    this.program = program;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (curSemesterEducation != group.curSemesterEducation) return false;
        return program != null ? program.equals(group.program) : group.program == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (curSemesterEducation ^ (curSemesterEducation >>> 32));
        result = 31 * result + (program != null ? program.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", curSemesterEducation=" + curSemesterEducation +
                ", program=" + program +
                '}';
    }

    /**
     * Generate a short name of the group.
     *
     * @return
     */
    public String generateGroupName() {
        StringBuffer str = new StringBuffer();
        if (program != null && program.getSpecialty() != null && program.getSpecialty().getName() != null) {
            str.append(program.getSpecialty().getName().substring(0, 5));
        }
        str.append("-");
        if (curSemesterEducation >= 0) {
            str.append(curSemesterEducation);
        }
        return str.toString();
    }
}
