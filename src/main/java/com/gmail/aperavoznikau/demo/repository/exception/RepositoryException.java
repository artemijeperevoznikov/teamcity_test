package com.gmail.aperavoznikau.demo.repository.exception;

public class RepositoryException extends RuntimeException {

    public RepositoryException(String message, Exception e) {
        super(message, e);
    }

}
