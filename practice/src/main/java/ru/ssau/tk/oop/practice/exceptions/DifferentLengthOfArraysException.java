package ru.ssau.tk.oop.practice.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {
    public DifferentLengthOfArraysException() {
    }

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}
