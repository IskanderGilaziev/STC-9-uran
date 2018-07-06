package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@Entity
@Table(name = "person")
public class Person {

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

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(email, person.email) &&
                status == person.status &&
                Objects.equals(user, person.user) &&
                Objects.equals(team, person.team) &&
                Objects.equals(lessonSet, person.lessonSet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, birthday, email, status, user, team, lessonSet);
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
                '}';
    }
}
