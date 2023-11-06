package ru.ssau.tk.oop.practice.exceptions;

public class InterpolationException extends RuntimeException {
    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}