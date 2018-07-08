package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "person")
public class Person implements Serializable {

    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Date birthday;

    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = true)
    private User user;

    @Column(nullable = true)
    private Team team;

    private Set<Lesson> lessonSet = new HashSet<>();

    private Set<Mark> markSet = new HashSet<>();

    public Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name, Date birthday, String email, Status status) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.status = status;
    }

    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "personSeq", sequenceName = "PERSON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToOne(optional = true, mappedBy = "person")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    public Set<Mark> getMarkSet() {
        return markSet;
    }

    public void setMarkSet(Set<Mark> markSet) {
        this.markSet = markSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (birthday != null ? !birthday.equals(person.birthday) : person.birthday != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (status != person.status) return false;
        if (user != null ? !user.equals(person.user) : person.user != null) return false;
        if (team != null ? !team.equals(person.team) : person.team != null) return false;
        if (lessonSet != null ? !lessonSet.equals(person.lessonSet) : person.lessonSet != null) return false;
        return markSet != null ? markSet.equals(person.markSet) : person.markSet == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (lessonSet != null ? lessonSet.hashCode() : 0);
        result = 31 * result + (markSet != null ? markSet.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", user=" + user +
                ", team=" + team +
                ", lessonSet=" + lessonSet +
                ", markSet=" + markSet +
                '}';
    }
}
