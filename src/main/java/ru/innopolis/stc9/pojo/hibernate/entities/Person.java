package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

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

    //    @JoinColumn(name="person_id")
    @OneToOne(optional = true, mappedBy = "person")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return user != null ? user.equals(person.user) : person.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
//                ", status=" + status +
//                ", user=" + user +
                '}';
    }
}
