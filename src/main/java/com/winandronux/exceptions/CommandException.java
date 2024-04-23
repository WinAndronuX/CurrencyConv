package com.winandronux.exceptions;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message + ". Type \"help\" or \"?\" for help");
    }
}
