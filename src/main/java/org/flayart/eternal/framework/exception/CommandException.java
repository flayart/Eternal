package org.flayart.eternal.framework.exception;

public class CommandException extends RuntimeException {

    public CommandException(String error) {
        super(error);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
