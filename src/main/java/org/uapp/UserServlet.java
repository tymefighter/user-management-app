package org.uapp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.uapp.context.ContextAttributes;
import org.uapp.logger.Logger;
import org.uapp.user.UserCreationRequest;
import org.uapp.user.UserDTO;
import org.uapp.user.UserManager;
import org.uapp.user.UserUpdationRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
  private UserManager getUsers(HttpServletRequest request) {
    return (UserManager) request
        .getServletContext()
        .getAttribute(
            ContextAttributes.USERS.getAttribute()
        );
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.setContentType("application/json");
      response.setStatus(200);

      UserManager userManager = getUsers(request);
      UserDTO[] userDTOs = userManager.getUsers();

      ObjectMapper objectMapper = new ObjectMapper();
      String usersJson = objectMapper.writeValueAsString(userDTOs);

      PrintWriter printWriter = response.getWriter();
      printWriter.println(
          usersJson
      );
    } catch(IOException ioException) {
      Logger.error("Failed to handle GET request", ioException);
      response.setStatus(500);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      response.setContentType("application/json");
      response.setStatus(200);

      BufferedReader reader = request.getReader();

      ObjectMapper objectMapper = new ObjectMapper();
      JsonParser parser = objectMapper.createParser(reader);

      UserCreationRequest userCreationRequest = parser.readValueAs(UserCreationRequest.class);

      UserManager userManager = getUsers(request);
      UserDTO userDTO = userManager.createUser(userCreationRequest);

      String userJson = objectMapper.writeValueAsString(userDTO);

      PrintWriter printWriter = response.getWriter();
      printWriter.println(
          userJson
      );
    } catch(IOException ioException) {
      Logger.error("Failed to handle POST request", ioException);

      response.setStatus(500);
    }
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      response.setContentType("application/json");
      response.setStatus(200);

      BufferedReader reader = request.getReader();

      ObjectMapper objectMapper = new ObjectMapper();
      JsonParser parser = objectMapper.createParser(reader);

      UserUpdationRequest userUpdationRequest = parser.readValueAs(UserUpdationRequest.class);

      UserManager userManager = getUsers(request);
      UserDTO userDTO = userManager.updateUser(userUpdationRequest);

      String userJson = objectMapper.writeValueAsString(userDTO);

      PrintWriter printWriter = response.getWriter();
      printWriter.println(
          userJson
      );
    } catch(IOException ioException) {
      Logger.error("Failed to handle PUT request", ioException);

      response.setStatus(500);
    }
  }
}
