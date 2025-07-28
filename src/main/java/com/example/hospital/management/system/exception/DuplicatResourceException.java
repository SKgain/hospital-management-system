package com.example.hospital.management.system.exception;

public class DuplicatResourceException extends RuntimeException {
    public DuplicatResourceException(String message) {
        super(message);
    }
}
