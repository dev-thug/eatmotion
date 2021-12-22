package com.one.eatmotion.advice.exception;

public class PostBlankException extends RuntimeException {
  public PostBlankException() {
    super();
  }

  public PostBlankException(String message) {
    super(message);
  }

  public PostBlankException(String message, Throwable cause) {
    super(message, cause);
  }
}
