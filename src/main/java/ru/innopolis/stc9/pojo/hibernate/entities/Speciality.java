package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "speciality")
public class Speciality {

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
}
