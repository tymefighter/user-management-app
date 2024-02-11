package org.uapp.user;

import java.util.ArrayList;
import java.util.Optional;

public class Users {
  private ArrayList<User> users;

  public Users() {
    this.users = new ArrayList<User>();
  }

  private UserDTO getUserDTO(User user) {
    return new UserDTO(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getUsername()
    );
  }

  public synchronized UserDTO[] getUsers() {
    return users
        .stream()
        .map(
          user -> getUserDTO(user)
        ).toArray(
          size -> new UserDTO[size]
        );
  }

  public synchronized UserDTO updateUser(UserUpdationRequest userUpdationRequest) {
    Optional<User> optionalUser = users
        .stream()
        .filter(
            _user -> _user.getId() == userUpdationRequest.getId()
        )
        .findFirst();

    if(!optionalUser.isPresent()) {
      return null;
    }

    User user = optionalUser.get();
    String name = userUpdationRequest.getName();
    if(name != null) {
      user.setName(name);
    }

    String email = userUpdationRequest.getEmail();
    if(email != null) {
      user.setEmail(email);
    }

    String password = userUpdationRequest.getPassword();
    if(password != null) {
      user.setPassword(password);
    }

    return getUserDTO(user);
  }

  public synchronized UserDTO createUser(UserCreationRequest userCreationRequest) {
    User newUser = new User(
        userCreationRequest.getName(),
        userCreationRequest.getEmail(),
        userCreationRequest.getUsername() ,
        userCreationRequest.getPassword()
    );

    users.add(newUser);

    return getUserDTO(newUser);
  }

  public synchronized void cleanup() {

  }
}
