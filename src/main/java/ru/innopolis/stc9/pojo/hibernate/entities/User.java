package ru.innopolis.stc9.pojo.hibernate.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Contains the necessary data to describe the user of the system
 */
@Component
@Entity
@Table(name = "users")
public class User {

    private long id;
    private String login;
    private String password;
    private String role;
    private int enabled;
    //    @Column(nullable = true)
    private Person person;

    /**
     * Default constructor
     */
    public User() {
    }

    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    //    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    //    @Column(name = "person", nullable = true)

    public void setPassword(String password) {
        this.password = password;
    }

    //    @Column(name = "role", nullable = true)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //    @Column(name = "enabled", nullable = false)
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @OneToOne(optional = false, mappedBy = "user")
    @JoinColumn(name = "user_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (enabled != user.enabled) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return person != null ? person.equals(user.person) : user.person == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + enabled;
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", person=" + person +
                '}';
    }
}
