package com.iCo6.command.exceptions;

public class MissingFile extends Exception {

	private static final long serialVersionUID = 1L;

	public MissingFile(String file) {
        super("<rose>File could not be found: <white>" + file);
    }
}
