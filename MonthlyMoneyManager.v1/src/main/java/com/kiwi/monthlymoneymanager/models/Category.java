package com.kiwi.monthlymoneymanager.models;

/**
 *
 * @author lsierro, lfabbian
 */

public class Category {
    
    private String name;
    
    public Category(String name){
        this.name = name;
        
    }
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "Category" + this.name;
    }
            
    
}