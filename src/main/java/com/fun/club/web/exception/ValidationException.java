package com.fun.club.web.exception;

public class ValidationException extends RuntimeException {

	  private static final long serialVersionUID = -2800097195552092838L;

	  public ValidationException(String message) {
	    super(message);
	  }

	  public ValidationException(Throwable cause) {
	    super(cause);
	  }

	  public ValidationException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  @Override
	  public synchronized Throwable fillInStackTrace() {
	    return this;
	  }

	}