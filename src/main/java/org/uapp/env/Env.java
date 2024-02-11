package org.uapp.env;

import java.util.Optional;

public class Env {
  private static Optional<String> getValue(Variables var) {
    String value = System.getenv(var.getName());
    return value == null || value.isEmpty() ? Optional.empty() : Optional.of(value);
  }

  public static Optional<String> getUsersFilename() {
    return getValue(Variables.USER_FILENAME);
  }

  public static Optional<Integer> getUserPersistenceTimeMs() {
    try {
      return getValue(Variables.USER_PERSISTENCE_TIME_MS)
          .map(Integer::parseInt);
    } catch(NumberFormatException numberFormatException) {
      return Optional.empty();
    }
  }
}
