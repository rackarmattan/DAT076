/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rackarmattan
 */
@Entity
@Table(name = "ACCOUNTS_FRUITS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountsFruits.findAll", query = "SELECT a FROM AccountsFruits a")
    , @NamedQuery(name = "AccountsFruits.findByFname", query = "SELECT a FROM AccountsFruits a WHERE a.accountsFruitsPK.fname = :fname")
    , @NamedQuery(name = "AccountsFruits.findByLogin", query = "SELECT a FROM AccountsFruits a WHERE a.accountsFruitsPK.login = :login")})
public class AccountsFruits implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountsFruitsPK accountsFruitsPK;
    @JoinColumn(name = "LOGIN", referencedColumnName = "LOGIN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Accounts accounts;
    @JoinColumn(name = "FNAME", referencedColumnName = "FNAME", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fruits fruits;

    public AccountsFruits() {
    }

    public AccountsFruits(AccountsFruitsPK accountsFruitsPK) {
        this.accountsFruitsPK = accountsFruitsPK;
    }

    public AccountsFruits(String fname, String login) {
        this.accountsFruitsPK = new AccountsFruitsPK(fname, login);
    }

    public AccountsFruitsPK getAccountsFruitsPK() {
        return accountsFruitsPK;
    }

    public void setAccountsFruitsPK(AccountsFruitsPK accountsFruitsPK) {
        this.accountsFruitsPK = accountsFruitsPK;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Fruits getFruits() {
        return fruits;
    }

    public void setFruits(Fruits fruits) {
        this.fruits = fruits;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountsFruitsPK != null ? accountsFruitsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountsFruits)) {
            return false;
        }
        AccountsFruits other = (AccountsFruits) object;
        if ((this.accountsFruitsPK == null && other.accountsFruitsPK != null) || (this.accountsFruitsPK != null && !this.accountsFruitsPK.equals(other.accountsFruitsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.project2.model.AccountsFruits[ accountsFruitsPK=" + accountsFruitsPK + " ]";
    }
    
}
