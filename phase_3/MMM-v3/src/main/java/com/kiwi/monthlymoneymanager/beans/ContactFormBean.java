package com.kiwi.monthlymoneymanager.beans;

import com.kiwi.monthlymoneymanager.exceptions.EmailSendingException;
import com.kiwi.monthlymoneymanager.models.ContactForm;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 *
 * @author lfabbian
 */
@Named(value = "contactformBean")
@SessionScoped
public class ContactFormBean implements Serializable 
{
    
    private String name;
    private String email;
    private String message;

    
    public void createForm() 
    {
        //Database.saveContactForm(new ContactForm(name, email, message));
    }
    
    public void sendEmail(String to, String from, String subject, String content) throws EmailSendingException 
    {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(props, null);

        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(content);

            Transport.send(msg);

        } catch (MessagingException exc) {
            exc.printStackTrace();
            throw new EmailSendingException("Failed to send the email. Please check your email and password configuration.");
        }
    }
        
    public void setCFEmail(String newEmail) {
    email = newEmail;
    }

    public void setCFName(String newName) {
        name = newName;
    }

    public void setCFMessage(String newMessage) {
        message = newMessage;
    }

    public String getCFEmail() {
        return email;
    }

    public String getCFName() {
        return name;
    }

    public String getCFMessage() {
        return message;
    }
 
} ;
