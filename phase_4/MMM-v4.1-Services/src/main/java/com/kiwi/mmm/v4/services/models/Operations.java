package com.kiwi.mmm.v4.services.models;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity class for the Operations table.
 */

@Entity
@Table(name = "Operations")
@NamedQueries({
    @NamedQuery(name = "Operations.findAll", query = "SELECT o FROM Operations o"),
    @NamedQuery(name = "Operations.findByOperationId", query = "SELECT o FROM Operations o WHERE o.operationId = :operationId"),
    @NamedQuery(name = "Operations.findByUserId", query = "SELECT o FROM Operations o WHERE o.user.userId = :userId"),
    @NamedQuery(name = "Operations.findByDate", query = "SELECT o FROM Operations o WHERE o.date = :date"),
    @NamedQuery(name = "Operations.findByCategoryId", query = "SELECT o FROM Operations o WHERE o.category.categoryId = :categoryId"),
    @NamedQuery(name = "Operations.findByAmount", query = "SELECT o FROM Operations o WHERE o.amount = :amount")
})
public class Operations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "operation_id")
    private Integer operationId;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users user;
   

    @Basic(optional = false)
    @Column(name = "date")
    private Date date;

    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private Categories category;

    @Basic(optional = false)
    @Column(name = "amount")
    private double amount;

    // Constructors, getters, setters, hashCode, equals, and toString methods

    // Constructors
    public Operations() {
    }

    public Operations(Integer operationId) {
        this.operationId = operationId;
    }
    


    public Operations(Integer operationId, Users user, Date date, Categories category, double amount) {
        this.operationId = operationId;
        this.user = user;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    // Getters and Setters
    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object object) {
        // Implement equals method
        return true;
    }

    @Override
    public String toString() {
        // Implement toString method

        return "com.kiwi.monthlymoneymanager.models.Operations";

    }
}