/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project2.view;

import com.project2.model.Accounts;
import com.project2.model.AccountsFacade;
import com.project2.model.CurrentAccountManager;
import com.project2.model.Fruits;
import com.project2.view.util.PaginationHelper;
import java.io.Serializable;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

/**
 *
 * @author rackarmattan
 */

@Named("aflController")
@SessionScoped
public class AccountFruitListController implements Serializable {

    private CurrentAccountManager current = CurrentAccountManager.getInstance();
    @EJB
    private AccountsFacade ejbFacade;
    private DataModel items = null;

    public Accounts getCurrent() {
        return current.getCurrentAccount();
    }

    public void setCurrent(Accounts account) {
        current.setCurrentAccount(account);
    }
    
    public void showFruitList(){
        //Set<Fruits> tmp = ejbFacade.findAccountFruits(getCurrent().getLogin());
        //System.out.println("Testar setter av current: " +getCurrent().getLogin());
        Set<Fruits> tmp = getCurrent().getFruits();
        System.out.println(tmp);
    }

}
