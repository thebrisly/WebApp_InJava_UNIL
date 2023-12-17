package com.kiwi.monthlymoneymanager.models;

import com.kiwi.monthlymoneymanager.models.Categories;
import com.kiwi.monthlymoneymanager.models.Users;
import jakarta.persistence.Basic;
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

@Entity
@Table(name = "Budgets")
@NamedQueries({
    @NamedQuery(name = "Budgets.findAll", query = "SELECT b FROM Budgets b"),
    @NamedQuery(name = "Budgets.findByBudgetId", query = "SELECT b FROM Budgets b WHERE b.budgetId = :budgetId"),
    @NamedQuery(name = "Budgets.findByUserId", query = "SELECT b FROM Budgets b WHERE b.user.userId = :userId"),
    @NamedQuery(name = "Budgets.findByCategoryId", query = "SELECT b FROM Budgets b WHERE b.selectedCategory.categoryId = :categoryId")
})
public class Budgets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "budget_id")
    private Integer budgetId;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users user;

    @JoinColumn(name = "selected_category_id", referencedColumnName = "category_id")
    @ManyToOne
    private Categories selectedCategory;

    @Basic(optional = false)
    @Column(name = "selected_amount")
    private double selectedAmount;

    public Budgets() {
    }

    public Budgets(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public Budgets(Users user, Categories selectedCategory, double selectedAmount) {
        this.user = user;
        this.selectedCategory = selectedCategory;
        this.selectedAmount = selectedAmount;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Categories getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Categories selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public double getSelectedAmount() {
        return selectedAmount;
    }

    public void setSelectedAmount(double selectedAmount) {
        this.selectedAmount = selectedAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (budgetId != null ? budgetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Budgets)) {
            return false;
        }
        Budgets other = (Budgets) object;
        return !((this.budgetId == null && other.budgetId != null) || (this.budgetId != null && !this.budgetId.equals(other.budgetId)));
    }

    @Override
    public String toString() {
        return "Budget[ id=" + budgetId + " ]";
    }
}
