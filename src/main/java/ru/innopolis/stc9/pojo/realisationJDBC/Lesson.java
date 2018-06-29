package ru.innopolis.stc9.pojo.realisationJDBC;


import java.sql.Date;

public class Lesson {

  private long id;
  private long scheduleItem;
  private long teacherItem;
  private Date date;
  private String theme;
  private String homework;

  public Lesson(long id, long scheduleItem, long teacherItem, Date date, String theme, String homework) {
    this.id = id;
    this.scheduleItem = scheduleItem;
    this.teacherItem = teacherItem;
    this.date = date;
    this.theme = theme;
    this.homework = homework;
  }

  public Lesson(long scheduleItem, long teacherItem, Date date, String theme, String homework) {
    this.scheduleItem = scheduleItem;
    this.teacherItem = teacherItem;
    this.date = date;
    this.theme = theme;
    this.homework = homework;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getScheduleItem() {
    return scheduleItem;
  }

  public void setScheduleItem(long scheduleItem) {
    this.scheduleItem = scheduleItem;
  }

  public long getTeacherItem() {
    return teacherItem;
  }

  public void setTeacherItem(long teacherItem) {
    this.teacherItem = teacherItem;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getTheme() {
    return theme;
  }

  public void setTheme(String theme) {
    this.theme = theme;
  }

  public String getHomework() {
    return homework;
  }

  public void setHomework(String homework) {
    this.homework = homework;
  }

}
