package org.uapp.logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {
  public static void log(LogLevel level, String message) {
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

  public static void error(String message) {
    log(LogLevel.ERROR, message);
  }

  public static void error(String message, Exception exception) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    exception.printStackTrace(printWriter);

    String messageWithException = String.format(
        "%s: %s",
        message,
        stringWriter.toString()
    );

    printWriter.close();

    error(messageWithException);
  }

  public static void info(String message) {
    log(LogLevel.INFO, message);
  }
}
