/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.beans;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
/**
 *
 * @author lfabbian
 */
@Named(value = "utilsBean")
@SessionScoped
public class UtilsBean implements Serializable
{
    public String maskPassword(String password) 
    {
        return "*".repeat(password.length());
    }
}
