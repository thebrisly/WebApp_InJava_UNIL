package com.kiwi.monthlymoneymanager.models;

/**
 *
 * @author lsierro, lfabbian
 */

public class XOLDCategory {
    
    private String  name;
    
    public XOLDCategory(String name){
        this.name = name;       
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }   
}