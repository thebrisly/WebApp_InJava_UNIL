package com.kiwi.monthlymoneymanager.controllers;

import com.kiwi.monthlymoneymanager.database.Database;
import com.kiwi.monthlymoneymanager.models.ContactForm;

/**
 *
 * @author lfabbian
 */

public class ContactFormController {
    
    private static String name;
    private static String email;
    private static String message;

    // create a method that sends an email to someone if possible

    
    public static void createForm() {
    Database.saveContactForm(new ContactForm(name, email, message));
    }
        
    public static void setCFEmail(String newEmail) {
    email = newEmail;
    }

    public static void setCFName(String newName) {
        name = newName;
    }

    public static void setCFMessage(String newMessage) {
        message = newMessage;
    }

    public static String getCFEmail() {
        return email;
    }

    public static String getCFName() {
        return name;
    }

    public static String getCFMessage() {
        return message;
    }
 
} ;
