package com.kiwi.monthlymoneymanager.models;

/**
 *
 * @authors lsierro, lfabbian
 */

public class User 
{

    private String      name;
    private String      username;
    private String      email;
    private int         password;

    public User(String name, String username, String email, String password) 
    {
        this.name =     name;
        this.username = username;
        this.email =    email;
        this.password = password.hashCode();

    }

    public String getUsername() {
        return username;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    public int getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }

    @Override
    public boolean equals(Object obj) {
        return ((User) obj).getUsername().equals(this.username);
    }

    @Override
    public String toString() {
        return "Username: " + this.username + "\nEmail: " + this.email;
    }

}