package org.uapp.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreationRequest {
  private String name;
  private String email;
  private String username;
  private String password;

  @JsonCreator
  public UserCreationRequest(
      @JsonProperty("name") String name,
      @JsonProperty("email") String email,
      @JsonProperty("username") String username,
      @JsonProperty("password") String password
  ) {
    this.name = name;
    this.email = email;
    this.username = username;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
