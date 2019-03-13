package com.project2.view;

import com.project2.model.Accounts;
import com.project2.model.AccountsFacade;
import com.project2.model.CurrentAccountManager;
import com.project2.model.Fruits;
import com.project2.model.FruitsFacade;
import com.project2.view.util.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Named;

/***
 * This class handles the communication between the user and the database when 
 * the user wants to add fruits to its list of fruits or display its list of fruits.
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

    public List<Fruits> showFruitList() {
        return getCurrentAccount().getFruits();
    }

    /***
     * Takes the fruit name that the user wants to mark as favourite and
     * add to its list of fruits. If the fruit is not in the user's list, 
     * it's added.
     * @param fruitName 
     */
    
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
