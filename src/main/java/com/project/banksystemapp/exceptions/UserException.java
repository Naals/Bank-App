package com.project.banksystemapp.exceptions;

public class UserException extends Throwable {
    public UserException(String emailAlreadyInUse) {
        super(emailAlreadyInUse);
    }
}
