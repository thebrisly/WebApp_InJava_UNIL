/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.exceptions;

/**
 *
 * @author laurentsierro
 */
public class InvalidEmailDomainException extends Exception {

    private String message;

    public InvalidEmailDomainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

