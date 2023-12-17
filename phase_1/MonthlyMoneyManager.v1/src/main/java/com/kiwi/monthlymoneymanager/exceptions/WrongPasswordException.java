/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.exceptions;

/**
 *
 * @author lfabbian
 */

public class WrongPasswordException extends Exception 
{
    private String message;

    public WrongPasswordException() 
    {
        this.message = "Error: The password does not correspond. Try to sign up again.";
    }

    public WrongPasswordException(String message) 
    {
        this.message = message;
    }

    @Override
    public String getMessage() 
    {
        return message;
    }
}
