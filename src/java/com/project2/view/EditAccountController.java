/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.view;

import com.project2.model.Accounts;
import com.project2.model.AccountsFacade;
import com.project2.model.CurrentAccountManager;
import com.project2.view.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author rackarmattan Controller class for editing accounts info
 */
@Named("editAccountController")
@SessionScoped
public class EditAccountController implements Serializable {

    CurrentAccountManager current = CurrentAccountManager.getInstance();
    @EJB
    private AccountsFacade ejbFacade;
    private String tmpPassword;
    private String tmpLogin;

    public EditAccountController() {

    }

    public void setTmpLogin(String tmpLogin) {
        this.tmpLogin = tmpLogin;
    }

    public String getTmpLogin() {
        return tmpLogin;
    }

    public String getTmpPassword() {
        return tmpPassword;
    }

    public void setTmpPassword(String password) {
        this.tmpPassword = password;
    }

    public Accounts getCurrent() {
        return current.getCurrentAccount();
    }

    public void setCurrent(Accounts account) {
        current.setCurrentAccount(account);
    }

    public void checkPasswordChange() {
        //Try to get a user by the entered name.
        Accounts tmp = ejbFacade.findByLogin(tmpLogin);

        if (tmpPassword.length() < 3) {
            JsfUtil.addErrorMessage("Password must be at least 3 characters long.");
        }
        //Checks if the user exists. 
        else if (tmp != null) {
            tmp.setPassword(tmpPassword);
            ejbFacade.edit(tmp);
            JsfUtil.addSuccessMessage("Password changed for: " + tmpLogin);
        } //Not a vaild name - send error msg. 
         else {
            JsfUtil.addErrorMessage("Invaild user name, please try again.");
        }
    }

    //Supposed to be called by commandbutton if user wants to update its own info
    public void updateAccountInfo() {
        ejbFacade.edit(getCurrent());
    }

}
