/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kiwi.mmm.v4.services.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author laura
 */
@Entity
@Table(name = "ContactForm")
@NamedQueries({
    @NamedQuery(name = "ContactForm.findAll", query = "SELECT c FROM ContactForm c"),
    @NamedQuery(name = "ContactForm.findByFormId", query = "SELECT c FROM ContactForm c WHERE c.formId = :formId"),
    @NamedQuery(name = "ContactForm.findByName", query = "SELECT c FROM ContactForm c WHERE c.name = :name"),
    @NamedQuery(name = "ContactForm.findByEmail", query = "SELECT c FROM ContactForm c WHERE c.email = :email")})
public class ContactForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_id")
    private Integer formId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Lob
    @Column(name = "message")
    private String message;

    public ContactForm() {
    }

    public ContactForm(Integer formId) {
        this.formId = formId;
    }

    public ContactForm(Integer formId, String name, String email, String message) {
        this.formId = formId;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formId != null ? formId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactForm)) {
            return false;
        }
        ContactForm other = (ContactForm) object;
        if ((this.formId == null && other.formId != null) || (this.formId != null && !this.formId.equals(other.formId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kiwi.mmm.v4.services.models.ContactForm[ formId=" + formId + " ]";
    }
    
}
