package org.uapp.context;

import org.uapp.logger.Logger;
import org.uapp.user.Users;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    ServletContext context = event.getServletContext();

    context.setAttribute(
        ContextAttributes.USERS.getAttribute(),
        new Users()
    );

    context.setAttribute(
        ContextAttributes.LOGGER.getAttribute(),
        new Logger()
    );
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    ServletContext context = event.getServletContext();
    Users users = (Users) context.getAttribute(
        ContextAttributes.USERS.getAttribute()
    );

    users.cleanup();

    context.removeAttribute(
        ContextAttributes.USERS.getAttribute()
    );

    context.removeAttribute(
        ContextAttributes.LOGGER.getAttribute()
    );
  }
}
