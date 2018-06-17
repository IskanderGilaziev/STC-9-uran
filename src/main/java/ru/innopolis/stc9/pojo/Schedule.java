package ru.innopolis.stc9.pojo;


import java.util.ArrayList;
import java.util.List;


public class Schedule {
    private List<String> weekDay = new ArrayList<>();
    private List<String> lessonNumber = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    public Schedule() {
    }

    public void initStatState() {
        weekDay.add("�����������");
        weekDay.add("�������");
        weekDay.add("�����");
        weekDay.add("�������");
        weekDay.add("�������");
        weekDay.add("�������");
        weekDay.add("�����������");

        lessonNumber.add("���� 1");
        lessonNumber.add("���� 2");
        lessonNumber.add("���� 3");
        lessonNumber.add("���� 4");
        lessonNumber.add("���� 5");

        list.add("p11");
        list.add("p12");
        list.add("p13");
        list.add("p14");
        list.add("p15");
    }

    public List<String> getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(List<String> weekDay) {
        this.weekDay = weekDay;
    }

    public List<String> getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(List<String> lessonNumber) {
        this.lessonNumber = lessonNumber;
    }


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
