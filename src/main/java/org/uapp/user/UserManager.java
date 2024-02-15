package org.uapp.user;

import org.uapp.env.Env;
import org.uapp.logger.Logger;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class UserManager {
  private final Timer persistenceTimer;
  private final Optional<UserPersistence> userPersistence;
  private final ArrayList<User> users;

  public UserManager() {
    persistenceTimer = new Timer("User Persistence Timer");

    userPersistence = Env.getUsersFilename().map(
        UserPersistence::new
    );

    Optional<ArrayList<User>> optionalUsers = userPersistence.flatMap(
        UserPersistence::readUsers
    );

    users = optionalUsers.orElse(new ArrayList<User>());
  }

  private UserDTO getUserDTO(User user) {
    return new UserDTO(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getUsername()
    );
  }

  private void persistUsers() {
    userPersistence.ifPresent(
        _userPersistence -> _userPersistence.writeUsers(users)
    );
  }

  class UserPersistenceTimerTask extends TimerTask {
    @Override
    public void run() {
      persistUsers();
    }
  }

  public void setup() {
    Env
        .getUserPersistenceTimeMs()
        .ifPresent(
            userPersistenceTimeMs -> {
              Logger.info(
                  String.format(
                      "Setting up a periodic task for persisting user data with time period: %d milliseconds",
                      userPersistenceTimeMs
                  )
              );

              UserPersistenceTimerTask userPersistenceTimerTask = new UserPersistenceTimerTask();

              persistenceTimer.schedule(
                  userPersistenceTimerTask,
                  0,
                  userPersistenceTimeMs
              );
            }
        );
  }

  public synchronized UserDTO[] getUsers() {
    return users
        .stream()
        .map(
            this::getUserDTO
        ).toArray(
            UserDTO[]::new
        );
  }

  public synchronized UserDTO updateUser(UserUpdationRequest userUpdationRequest) {
    String updationUserId = userUpdationRequest.getId();
    Optional<User> optionalUser = users
        .stream()
        .filter(
            _user -> _user.getId().equals(updationUserId)
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
        userCreationRequest.getUsername(),
        userCreationRequest.getPassword()
    );

    users.add(newUser);

    return getUserDTO(newUser);
  }

  public synchronized void cleanup() {
    persistenceTimer.cancel();
    persistUsers();
  }
}
