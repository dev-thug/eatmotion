package com.one.eatmotion.advice.exception;

public class ReviewBlankException extends RuntimeException {
  public ReviewBlankException() {
    super();
  }

  public ReviewBlankException(String message) {
    super(message);
  }

  public ReviewBlankException(String message, Throwable cause) {
    super(message, cause);
  }
}
