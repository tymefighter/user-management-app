package org.uapp.logger;

public class Logger {
  public void log(LogLevel level, String message) {
    String logMessage = String.format(
        "%s: %s",
        level,
        message
    );

    switch(level) {
      case ERROR:
        System.err.println(logMessage);
        break;

      case INFO:
        System.out.println(logMessage);
        break;

      default:
        break;
    }
  }

  public void error(String message) {
    log(LogLevel.ERROR, message);
  }

  public void info(String message) {
    log(LogLevel.INFO, message);
  }
}
