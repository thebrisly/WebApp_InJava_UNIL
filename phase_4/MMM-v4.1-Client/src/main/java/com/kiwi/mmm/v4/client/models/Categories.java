/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.client.models;

/**
 *
 * @author laura
 */



public class Categories 
{
    private Integer         categoryId;
    private String          name;
    
    // ------------------------------------------------
    // ---------------- Constructors ------------------
    // ------------------------------------------------
    
    public Categories() {
    }

    public Categories(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Categories(Integer categoryId, String name) {
        this.categoryId =   categoryId;
        this.name =         name;
    }
    
    
    // ------------------------------------------------
    // ---------------- Getters and Setters -----------
    // ------------------------------------------------
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }   
}
