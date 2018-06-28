package ru.innopolis.stc9.pojo.realisationJDBC;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
public class Schedule {
    private static final Logger logger = Logger.getLogger(Schedule.class);
    private static final String BEFORE = "First line.";
    private static final String AFTER = "Before exit.";
    private static final String SUCCESS = "Success? ";
    /**
     * Day of week for view form
     */
    private List<String> weekDay = new ArrayList<>();
    /**
     * Count of lesson in a day
     */
    private List<String> lessonNumber = new ArrayList<>();
    /**
     * Draft of schedule
     */
    private List<ScheduleItem> list = new ArrayList<>();



    public Schedule() {
        weekDay.add("Воскресенье");
        weekDay.add("Понедельник");
        weekDay.add("Вторник");
        weekDay.add("Среда");
        weekDay.add("Четверг");
        weekDay.add("Пятница");
        weekDay.add("Суббота");

        lessonNumber.add("Пара №1");
        lessonNumber.add("Пара №2");
        lessonNumber.add("Пара №3");
        lessonNumber.add("Пара №4");
        lessonNumber.add("Пара №5");
        lessonNumber.add("Пара №6");
        lessonNumber.add("Пара №7");
        lessonNumber.add("Пара №8");
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

    public List<ScheduleItem> getList() {
        return list;
    }

    public void setList(List<ScheduleItem> list) {
        this.list = list;
    }

    public void add(ScheduleItem item) {
        list.add(item);
    }

    public void add(List<ScheduleItem> item) {
        list.addAll(item);
    }

    /**
     * Get subject-teacher relationship for a given item in the schedule
     *
     * @param group
     * @param day
     * @param lessonNumber
     * @return
     */
    public TeacherSubject getSubjectInDraft(Group group, String day, long lessonNumber) {
        logger.debug(BEFORE);
        TeacherSubject result = null;
        ScheduleItem ourItem = selectItem(group, day, lessonNumber);
        if (ourItem != null) {
            result = ourItem.getSubjectWithTeacher();
        } else {
            logger.warn("No such item in list.");
        }
        logger.info(SUCCESS + (result == null ? "Result is null" : result.toString()));
        logger.debug(AFTER);
        return result;
    }

    /**
     * Find schedule item for required group, day of week and number of lesson
     *
     * @param group
     * @param day
     * @param lessonNumber
     * @return
     */
    private ScheduleItem selectItem(Group group, String day, long lessonNumber) {
        logger.debug(BEFORE);
        long dayOfWeek = findIndex(day);
        ScheduleItem result = null;
        if (dayOfWeek >= 0 && group != null) {
            for (ScheduleItem item : list) {
                boolean b1 = item.getDayOfWeek() == dayOfWeek;
                boolean b2 = item.getLessonNumber() == lessonNumber;
                boolean b3 = item.getGroupItem().equals(group);

                if (b1 && b2 && b3) {
                    result = item;
                    break;
                }
            }
        } else {
            logger.warn("error in day of week or group is null argument");
        }
        logger.info(SUCCESS + (result == null ? "Result is null" : result.toString()));
        logger.debug(AFTER);
        return result;
    }

    public int getRoomInDraft(Group group, String day, long lessonNumber) {
        logger.debug(BEFORE);
        int result = 0;
        ScheduleItem ourItem = selectItem(group, day, lessonNumber);
        if (ourItem != null) {
            result = (int) ourItem.getRoom();
        } else {
            logger.warn("No such item in list.");
        }
        logger.info(SUCCESS + (result > 0 ? "Yes, room = " + result : "No"));
        logger.debug(AFTER);
        return result;
    }

    private long findIndex(String day) {
        long result = -1;
        for (int i = 0; i < weekDay.size(); i++) {
            if (weekDay.get(i).equals(day)) {
                result = (long) i;
                break;
            }
        }
        return result;
    }
}
