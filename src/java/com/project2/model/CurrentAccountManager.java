package com.project2.model;

/**
 * Singleton class for keeping track of which user is the user that
 * is logged in and if a user is logged in.
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
