/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.controllers;

/**
 *
 * @author lfabbian
 */
public class UtilsController 
{
    public static String maskPassword(String password) 
    {
        return "*".repeat(password.length());
    }
}
