package ru.innopolis.stc9.pojo.hibernate.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
     * время обучения, года
     */
    @Column(nullable = false)
    private int yTotal;

    /**
     * Отметка, что данная специальность с набором свойственных ей предметов еще актуальна.
     * 0 - действует
     * не 0 - архивная
     * Соответственно, при создании новых групп ее можно будет использовать.
     * Если значение составить false, то эта специальность
     * может быть использована только для уже существующих групп для окончания обучения.
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private int isActive;
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

    public Speciality(String name, int yTotal) {
        this.name = name;
        this.yTotal = yTotal;
    }

    public Speciality(String name, int yTotal, int isActive) {
        this.name = name;
        this.yTotal = yTotal;
        this.isActive = isActive;
    }

    @Id
    @SequenceGenerator(name = "specialitySeq", sequenceName = "speciality_SEQUENCE", allocationSize = 0)
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
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
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + yTotal;
        result = 31 * result + isActive;
        return result;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yTotal=" + yTotal +
                ", isActive=" + isActive +
                ", subject count=" + subjectSet.size() +
                ", team count=" + teamSet.size() +
                '}';
    }
}
