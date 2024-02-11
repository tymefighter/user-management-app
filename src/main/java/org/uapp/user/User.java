package org.uapp.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

class User {
  private String id;
  private String name;
  private String email;
  private String username;
  private String password;

  @JsonCreator
  public User(
    @JsonProperty("name") String name,
    @JsonProperty("email") String email,
    @JsonProperty("username") String username,
    @JsonProperty("password") String password
  ) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.email = email;
    this.username = username;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}