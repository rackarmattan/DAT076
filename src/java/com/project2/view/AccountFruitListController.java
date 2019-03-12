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
import com.project2.model.FruitsFacade;
import com.project2.view.util.JsfUtil;
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

    private CurrentAccountManager currentAccount = CurrentAccountManager.getInstance();
    @EJB
    private AccountsFacade accountFacade;
    @EJB
    private FruitsFacade fruitFacade;
    private DataModel items = null;

    public Accounts getCurrentAccount() {
        return currentAccount.getCurrentAccount();
    }

    public void setCurrentAccount(Accounts account) {
        currentAccount.setCurrentAccount(account);
    }

    public void showFruitList() {
        //Set<Fruits> tmp = ejbFacade.findAccountFruits(getCurrent().getLogin());
        //System.out.println("Testar setter av current: " +getCurrent().getLogin());
        Set<Fruits> tmp = getCurrentAccount().getFruits();
        System.out.println(tmp);
    }

    public void markAsFavourite(String fruitName) {
        Fruits tmp = fruitFacade.findByFname(fruitName);
        if (tmp != null && getCurrentAccount().addFruit(tmp)) {
            accountFacade.edit(getCurrentAccount());
            fruitFacade.edit(tmp);
            JsfUtil.addSuccessMessage(fruitName + " added to your list of favourites.");
        } else {
            JsfUtil.addErrorMessage("That fruit is already in your favourite list.");
        }
    }

}
