package ru.courses.exception;

public class LineTooLongException extends Exception {
    public LineTooLongException(String message){
        super(message);
    }
}
