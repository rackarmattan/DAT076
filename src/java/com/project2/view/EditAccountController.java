package com.project2.view;

import com.project2.model.Accounts;
import com.project2.model.AccountsFacade;
import com.project2.model.CurrentAccountManager;
import com.project2.view.util.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/***
 * This class handles the communication between the user and the database
 * when the user wants to edit its information.
 * @author rackarmattan
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

    /***
     * Checks if a user with the requested login exists, checks if the password
     * is shorter than three, and if the checks is passed the user's password
     * is changed.
     */
    public void checkPasswordChange() {
        Accounts tmp = ejbFacade.findByLogin(tmpLogin);
        if (tmpPassword.length() < 3) {
            JsfUtil.addErrorMessage("Password must be at least 3 characters long.");
        } else if (tmp != null) {
            tmp.setPassword(tmpPassword);
            ejbFacade.edit(tmp);
            JsfUtil.addSuccessMessage("Password changed for: " + tmpLogin);
        } else {
            JsfUtil.addErrorMessage("Invaild user name, please try again.");
        }
    }

    public void updateAccountInfo() {
        ejbFacade.edit(getCurrent());
    }

}
