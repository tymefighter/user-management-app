package org.uapp.context;

public enum ContextAttributes {
  LOGGER("logger"),
  USERS("users");

  private String attribute;

  private ContextAttributes(String attribute) {
    this.attribute = attribute;
  }

  public String getAttribute() {
    return attribute;
  }
}
