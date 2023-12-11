/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.monthlymoneymanager.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lfabbian
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByMonthlyIncome", query = "SELECT u FROM Users u WHERE u.monthlyIncome = :monthlyIncome"),
    @NamedQuery(name = "Users.findByBalance", query = "SELECT u FROM Users u WHERE u.balance = :balance"),
    @NamedQuery(name = "Users.findBySelectedAmount", query = "SELECT u FROM Users u WHERE u.selectedAmount = :selectedAmount"),
    @NamedQuery(name = "Users.findByPictureProfile", query = "SELECT u FROM Users u WHERE u.pictureProfile = :pictureProfile")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private int password;
    @Basic(optional = false)
    @Column(name = "monthly_income")
    private double monthlyIncome;
    @Basic(optional = false)
    @Column(name = "balance")
    private double balance;
    @Basic(optional = false)
    @Column(name = "selected_amount")
    private double selectedAmount;
    @Basic(optional = false)
    @Column(name = "picture_profile")
    private String pictureProfile;
    @JoinTable(name = "Budgets", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "selected_category_id", referencedColumnName = "category_id"),
        @JoinColumn(name = "selected_category_id", referencedColumnName = "category_id")})
    @ManyToMany
    private Collection<Categories> categoriesCollection;
    @JoinTable(name = "Operations", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "category_id", referencedColumnName = "category_id"),
        @JoinColumn(name = "category_id", referencedColumnName = "category_id")})
    @ManyToMany
    private Collection<Categories> categoriesCollection1;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String name, String username, String email, int password, double monthlyIncome, double balance, double selectedAmount, String pictureProfile) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.monthlyIncome = monthlyIncome;
        this.balance = balance;
        this.selectedAmount = selectedAmount;
        this.pictureProfile = pictureProfile;
    }
    
    
    public boolean isPasswordCorrect(String password) {
        return password.hashCode() == this.password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getSelectedAmount() {
        return selectedAmount;
    }

    public void setSelectedAmount(double selectedAmount) {
        this.selectedAmount = selectedAmount;
    }

    public String getPictureProfile() {
        return pictureProfile;
    }

    public void setPictureProfile(String pictureProfile) {
        this.pictureProfile = pictureProfile;
    }

    public Collection<Categories> getCategoriesCollection() {
        return categoriesCollection;
    }

    public void setCategoriesCollection(Collection<Categories> categoriesCollection) {
        this.categoriesCollection = categoriesCollection;
    }

    public Collection<Categories> getCategoriesCollection1() {
        return categoriesCollection1;
    }

    public void setCategoriesCollection1(Collection<Categories> categoriesCollection1) {
        this.categoriesCollection1 = categoriesCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kiwi.monthlymoneymanager.models.Users[ userId=" + userId + " ]";
    }
    
    
    public double calculateRemainingBalance() 
    {
        return getBalance();
    }
    
    
}