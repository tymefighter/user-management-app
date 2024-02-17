package org.uapp.user;

public class UserFilterParams {
  private final String id;
  private final String name;
  private final String username;
  private final String email;

  public UserFilterParams() {
    id = null;
    name = null;
    username = null;
    email = null;
  }

  public UserFilterParams(
      String id,
      String name,
      String username,
      String email
  ) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
