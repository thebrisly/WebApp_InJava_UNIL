/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.exceptions;

/**
 *
 * @author lfabbian
 */

public class EmailSendingException extends Exception 
{
    private String message;
    
    public EmailSendingException(String message) 
    {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
