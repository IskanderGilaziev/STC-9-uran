package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.realisationJDBC.Group;

import java.util.List;

public interface IGroupService {
    void update(Group group);

    Group getById(long id);

    void deleteById(long id);

    void add(Group group);

    List<Group> getAll();

}
