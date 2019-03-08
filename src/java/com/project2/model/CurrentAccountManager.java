/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.model;

/**
 *
 * @author rackarmattan
 */
public class CurrentAccountManager {
    private static CurrentAccountManager currentAccounts = null;
    private Accounts account;
    private boolean isLoggedIn = false;
    
    private CurrentAccountManager(){
        this.account = new Accounts();
    }
    
    public static CurrentAccountManager getInstance(){
        if(currentAccounts == null)currentAccounts = new CurrentAccountManager();
        
        return currentAccounts;
    }
    
    public void setCurrentAccount(Accounts account){
        this.account = account;
    }
    
    public Accounts getCurrentAccount(){
        return account;
    }
    
    public void setLoggedIn(boolean value){
        this.isLoggedIn = value;
    }
    
    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }
}
