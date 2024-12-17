package dev.naimsulejmani.grupi1watersupplykru.exceptions;

public class UsernameExistException extends RuntimeException {
    public UsernameExistException(String message) {
        super(message);
    }

    public UsernameExistException() {
        super("Username already exists");
    }
}
