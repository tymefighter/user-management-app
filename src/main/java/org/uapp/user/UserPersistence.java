package org.uapp.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.uapp.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * This class provides methods for reading user data from a file (persistent data
 * source) and for writing user data to a file.
 *
 * Note: This class should NOT mutate the user data it receives in its methods.
 * The user data is not owned by this class, it is owned and managed by
 * {@link org.uapp.user.UserManager}
 */
class UserPersistence {
  private final File file;

  public UserPersistence(String filepath) {
    file = new File(filepath);
  }

  public void writeUsers(ArrayList<User> users) {
    try {
      Logger.info("Writing users to file: " + file.getAbsolutePath());

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
      if(!file.exists()) {
        Logger.info("Not reading users as file does not exist: " + file.getAbsolutePath());
        return Optional.empty();
      }

      Logger.info("Reading users from file: " + file.getAbsolutePath());

      ObjectMapper objectMapper = new ObjectMapper();
      JsonParser parser = objectMapper.createParser(file);

      User[] usersArr = parser.readValueAs(User[].class);
      ArrayList users = new ArrayList<User>();
      Collections.addAll(users, usersArr);

      return Optional.of(users);
    } catch(IOException ioException) {
      Logger.error(
          "Failed to read users from file",
          ioException
      );

      return Optional.empty();
    }
  }
}
