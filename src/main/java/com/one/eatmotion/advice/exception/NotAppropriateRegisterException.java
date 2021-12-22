package com.one.eatmotion.advice.exception;

public class NotAppropriateRegisterException extends RuntimeException {
  public NotAppropriateRegisterException() {
    super();
  }

  public NotAppropriateRegisterException(String message) {
    super(message);
  }

  public NotAppropriateRegisterException(String message, Throwable cause) {
    super(message, cause);
  }
}
