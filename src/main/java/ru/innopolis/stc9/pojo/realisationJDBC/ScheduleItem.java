package ru.innopolis.stc9.pojo.realisationJDBC;


public class ScheduleItem {

    private long id;
    private long dayOfWeek;
    private long lessonNumber;
    private Group groupItem;
    private TeacherSubject subjectWithTeacher;
    private long room;

    public ScheduleItem(long id, long dayOfWeek, long lessonNumber, Group groupItem, TeacherSubject subjectWithTeacher, long room) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.lessonNumber = lessonNumber;
        this.groupItem = groupItem;
        this.subjectWithTeacher = subjectWithTeacher;
        this.room = room;
    }

    public ScheduleItem(long dayOfWeek, long lessonNumber, Group groupItem, TeacherSubject subjectWithTeacher, long room) {
        this.dayOfWeek = dayOfWeek;
        this.lessonNumber = lessonNumber;
        this.groupItem = groupItem;
        this.subjectWithTeacher = subjectWithTeacher;
        this.room = room;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(long dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public long getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(long lessonNummber) {
        this.lessonNumber = lessonNummber;
    }

    public Group getGroupItem() {
        return groupItem;
    }

    public void setGroupItem(Group groupItem) {
        this.groupItem = groupItem;
    }

    public TeacherSubject getSubjectWithTeacher() {
        return subjectWithTeacher;
    }

    public void setSubjectWithTeacher(TeacherSubject subjectWithTeacher) {
        this.subjectWithTeacher = subjectWithTeacher;
    }

    public long getRoom() {
        return room;
    }

    public void setRoom(long room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleItem that = (ScheduleItem) o;

        if (id != that.id) return false;
        if (dayOfWeek != that.dayOfWeek) return false;
        if (lessonNumber != that.lessonNumber) return false;
        if (room != that.room) return false;
        if (!groupItem.equals(that.groupItem)) return false;
        return subjectWithTeacher.equals(that.subjectWithTeacher);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (dayOfWeek ^ (dayOfWeek >>> 32));
        result = 31 * result + (int) (lessonNumber ^ (lessonNumber >>> 32));
        result = 31 * result + groupItem.hashCode();
        result = 31 * result + subjectWithTeacher.hashCode();
        result = 31 * result + (int) (room ^ (room >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", lessonNumber=" + lessonNumber +
                ", groupItem=" + groupItem +
                ", subjectWithTeacher=" + subjectWithTeacher +
                ", room=" + room +
                '}';
    }
}
