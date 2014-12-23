package com.iCo6.command.exceptions;


public class InvalidUsage extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidUsage(String message) {
        super("&cInvalid Command Usage: " + message);
    }
}
