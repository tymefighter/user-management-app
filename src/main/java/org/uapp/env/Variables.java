package org.uapp.env;

public enum Variables {
  USER_FILENAME("USER_FILENAME"),
  USER_PERSISTENCE_TIME_MS("USER_PERSISTENCE_TIME_MS");

  private String name;

  private Variables(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
