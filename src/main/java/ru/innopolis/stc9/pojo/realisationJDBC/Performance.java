package ru.innopolis.stc9.pojo.realisationJDBC;

public class Performance {

  private long id;
  private long student;
  private long lesson;
  private boolean onLesson;
  private long mark;

  public Performance(long id, long student, long lesson, boolean onLesson, long mark) {
    this.id = id;
    this.student = student;
    this.lesson = lesson;
    this.onLesson = onLesson;
    this.mark = mark;
  }

  public Performance(long student, long lesson, boolean onLesson, long mark) {
    this.student = student;
    this.lesson = lesson;
    this.onLesson = onLesson;
    this.mark = mark;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getStudent() {
    return student;
  }

  public void setStudent(long student) {
    this.student = student;
  }

  public long getLesson() {
    return lesson;
  }

  public void setLesson(long lesson) {
    this.lesson = lesson;
  }

  public boolean getOnLesson() {
    return onLesson;
  }

  public void setOnLesson(boolean onLesson) {
    this.onLesson = onLesson;
  }

  public long getMark() {
    return mark;
  }

  public void setMark(long mark) {
    this.mark = mark;
  }

}
