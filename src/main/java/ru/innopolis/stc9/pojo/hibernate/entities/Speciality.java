package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "speciality")
public class Speciality implements Serializable {

    private long id;
    /**
     * Название специальности, по которой могут обучаться группы
     */
    @Column(nullable = false)
    private String name;

    /**
     * Общее время обучения в годах
     */
    @Column(nullable = false)
    private int yTotal;

    /**
     * Отметка, что данная специальность с набором свойственных ей предметов еще актуальна.
     * Соответственно, при создании новых групп ее можно будет использовать.
     * Если значение составить false, то эта специальность
     * может быть использована только для уже существующих групп для окончания обучения.
     */
    @Column(nullable = false)
    private boolean isActive;
    /**
     * Список дисципли, которые должны изучить студенты за время обучения
     */
    private Set<Subject> subjectSet = new HashSet<>();
    /**
     * Список учебных групп, которые обучаются по заданной специальности
     */
    private Set<Team> teamSet = new HashSet<>();

    public Speciality() {
    }

    /**
     * Создает новую действующую специальность обучения
     *
     * @param name
     * @param yTotal
     */
    public Speciality(String name, int yTotal) {
        this.name = name;
        this.yTotal = yTotal;
        isActive = true;
    }

    @Id
    @SequenceGenerator(name = "specialitySeq", sequenceName = "speciality_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialitySeq")
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

    public int getyTotal() {
        return yTotal;
    }

    public void setyTotal(int yTotal) {
        this.yTotal = yTotal;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @ManyToMany
    @JoinTable(name = "specialty_subject",
            joinColumns = @JoinColumn(name = "specialty_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }

    @OneToMany(mappedBy = "speciality", fetch = FetchType.LAZY)
    public Set<Team> getTeamSet() {
        return teamSet;
    }

    public void setTeamSet(Set<Team> teamSet) {
        this.teamSet = teamSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        if (id != that.id) return false;
        if (yTotal != that.yTotal) return false;
        if (isActive != that.isActive) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (subjectSet != null ? !subjectSet.equals(that.subjectSet) : that.subjectSet != null) return false;
        return teamSet != null ? teamSet.equals(that.teamSet) : that.teamSet == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + yTotal;
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (subjectSet != null ? subjectSet.hashCode() : 0);
        result = 31 * result + (teamSet != null ? teamSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yTotal=" + yTotal +
                ", isActive=" + isActive +
//                ", subjectSet=" + subjectSet +
//                ", teamSet=" + teamSet +
                '}';
    }

}
