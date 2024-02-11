package org.uapp.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdationRequest {
  private final String id;
  private final String name;
  private final String email;
  private final String password;

  @JsonCreator
  public UserUpdationRequest(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("email") String email,
      @JsonProperty("password") String password
  ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
