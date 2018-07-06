package ru.innopolis.stc9.pojo;

public class User {

  private long id;
  private String login;
  private String password;
  private long personId;
    private String role;

    public User() {
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

  public User(long id, String login, String password, long personId) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.personId = personId;
  }

  public User(String login, String password, long personId) {
    this.login = login;
    this.password = password;
    this.personId = personId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
