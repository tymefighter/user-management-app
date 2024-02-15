package org.uapp.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {
  @Test
  public void testUserCreation() {
    User user = new User(
        "Ahmed",
        "ahmed@mock.com",
        "ahmed_username",
        "Password@123"
    );

    Assertions.assertEquals(
        user.getName(),
        "Ahmed"
    );

    Assertions.assertEquals(
        user.getEmail(),
        "ahmed@mock.com"
    );

    Assertions.assertEquals(
        user.getUsername(),
        "ahmed_username"
    );

    Assertions.assertFalse(
        user.checkPassword("Sample")
    );

    Assertions.assertTrue(
        user.checkPassword("Password@123")
    );
  }

  @Test
  public void testUserModification() {
    User user = new User(
        "Ahmed",
        "ahmed@mock.com",
        "ahmed_username",
        "Password@123"
    );

    Assertions.assertEquals(
        user.getName(),
        "Ahmed"
    );

    user.setName("Ahmed Z");

    Assertions.assertEquals(
        user.getName(),
        "Ahmed Z"
    );

    Assertions.assertEquals(
        user.getEmail(),
        "ahmed@mock.com"
    );

    user.setEmail("ahmed.z@mock.com");

    Assertions.assertEquals(
        user.getEmail(),
        "ahmed.z@mock.com"
    );

    Assertions.assertFalse(
        user.checkPassword("Sample")
    );

    Assertions.assertTrue(
        user.checkPassword("Password@123")
    );

    user.setPassword("Sample");

    Assertions.assertTrue(
        user.checkPassword("Sample")
    );

    Assertions.assertFalse(
        user.checkPassword("Password@123")
    );
  }

  @Test
  public void testJsonSerializationAndDeserialization() throws JsonProcessingException {
    User user = new User(
        "Ahmed",
        "ahmed@mock.com",
        "ahmed_username",
        "Password@123"
    );

    ObjectMapper objectMapper = new ObjectMapper();
    String userJson = objectMapper.writeValueAsString(user);

    User parsedUser = objectMapper.readValue(userJson, User.class);

    Assertions.assertEquals(
        parsedUser.getName(),
        "Ahmed"
    );

    Assertions.assertEquals(
        parsedUser.getEmail(),
        "ahmed@mock.com"
    );

    Assertions.assertEquals(
        parsedUser.getUsername(),
        "ahmed_username"
    );

    Assertions.assertFalse(
        parsedUser.checkPassword("Sample")
    );

    Assertions.assertTrue(
        parsedUser.checkPassword("Password@123")
    );
  }
}
