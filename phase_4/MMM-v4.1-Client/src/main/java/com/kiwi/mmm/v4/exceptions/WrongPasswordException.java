/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.exceptions;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 *
 * @author lfabbian
 */

public class WrongPasswordException extends RuntimeException {

    private String message;

    public WrongPasswordException() {
        this.message = "Error: The password does not correspond. Try to sign up again.";
    }

    public WrongPasswordException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        // Ajouter le message à FacesContext
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        return message;
    }
}