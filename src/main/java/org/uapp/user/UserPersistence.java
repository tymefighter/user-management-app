package org.uapp.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.uapp.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

class UserPersistence {
  private final File file;

  public UserPersistence(String filepath) {
    file = new File(filepath);
  }

  public void writeUsers(ArrayList<User> users) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.writeValue(
          file,
          users.toArray(new User[0])
      );
    } catch(IOException ioException) {
      Logger.error(
          "Failed to write users to file",
          ioException
      );
    }
  }

  public Optional<ArrayList<User>> readUsers() {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JsonParser parser = objectMapper.createParser(file);

      User[] usersArr = parser.readValueAs(User[].class);
      ArrayList users = new ArrayList<User>();
      Collections.addAll(users, usersArr);

      return Optional.of(users);
    } catch(IOException ioException) {
      Logger.error(
          "Failed to write users from file",
          ioException
      );

      return Optional.empty();
    }
  }
}
