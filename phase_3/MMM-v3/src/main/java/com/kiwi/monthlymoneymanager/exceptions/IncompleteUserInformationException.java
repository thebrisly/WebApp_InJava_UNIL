package com.kiwi.monthlymoneymanager.exceptions;

/**
 *
 * @author laurentsierro
 */

/**
 * Exception to be thrown when any required input field for user creation is empty.
 */
public class IncompleteUserInformationException extends Exception {

    private String message;

    public IncompleteUserInformationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

