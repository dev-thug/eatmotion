package com.one.eatmotion.advice.exception;

public class ReserveTimeOverException extends RuntimeException {
  public ReserveTimeOverException() {
    super();
  }

  public ReserveTimeOverException(String message) {
    super(message);
  }

  public ReserveTimeOverException(String message, Throwable cause) {
    super(message, cause);
  }
}
