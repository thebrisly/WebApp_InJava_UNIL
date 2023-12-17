/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.controllers;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kiwi.monthlymoneymanager.exceptions.EmailSendingException;

/**
 *
 * @author lfabbian
 */

public class EmailSenderController {
    public static void sendEmail(String to, String from, String subject, String content) throws EmailSendingException {
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
}