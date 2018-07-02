package ru.innopolis.stc9.pojo.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "group")
public class Group {

    private long id;

    @Column(name = "name_group")
    private String nameGroup;

    private Person person;
    @Column(name = "program")
    private String program;

    public Group() {
    }

    public Group(String nameGroup, Person person) {
        this.nameGroup = nameGroup;
        this.person = person;
    }

    public Group(long id, String nameGroup, Person person, String program) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.person = person;
        this.program = program;
    }

    public Group(String nameGroup, Person person, String program) {
        this.nameGroup = nameGroup;
        this.person = person;
        this.program = program;
    }

    @Id
    @Column(unique = true)
    @SequenceGenerator(name = "groupSeq", sequenceName = "Group_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSeq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}

