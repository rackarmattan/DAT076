/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author rackarmattan
 */
@Embeddable
public class AccountsFruitsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "FNAME")
    private String fname;
    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;

    public AccountsFruitsPK() {
    }

    public AccountsFruitsPK(String fname, String login) {
        this.fname = fname;
        this.login = login;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fname != null ? fname.hashCode() : 0);
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountsFruitsPK)) {
            return false;
        }
        AccountsFruitsPK other = (AccountsFruitsPK) object;
        if ((this.fname == null && other.fname != null) || (this.fname != null && !this.fname.equals(other.fname))) {
            return false;
        }
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project2.model.AccountsFruitsPK[ fname=" + fname + ", login=" + login + " ]";
    }
    
}
