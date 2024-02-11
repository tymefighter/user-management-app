package org.uapp.context;

import org.uapp.logger.Logger;
import org.uapp.user.UserManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    ServletContext context = event.getServletContext();

    context.setAttribute(
        ContextAttributes.USERS.getAttribute(),
        new UserManager()
    );
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    ServletContext context = event.getServletContext();
    UserManager userManager = (UserManager) context.getAttribute(
        ContextAttributes.USERS.getAttribute()
    );

    userManager.cleanup();

    context.removeAttribute(
        ContextAttributes.USERS.getAttribute()
    );
  }
}
