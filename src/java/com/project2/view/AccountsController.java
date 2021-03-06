package com.project2.view;

import com.project2.model.Accounts;
import com.project2.view.util.JsfUtil;
import com.project2.view.util.PaginationHelper;
import com.project2.model.AccountsFacade;
import com.project2.model.CurrentAccountManager;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;
import javax.faces.model.*;

/***
 * This class handles the communication between the user and the database when
 * creating an account and logging in/out.
 *
 * @author rackarmattan
 */
@Named("accountsController")
@RequestScoped
public class AccountsController implements Serializable {

    private CurrentAccountManager current; //= CurrentAccountManager.getInstance();
    private DataModel items = null;
    @EJB
    private AccountsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public AccountsController() {
        this.current = CurrentAccountManager.getInstance();
    }

    public boolean isLoggedIn() {
        return current.getIsLoggedIn();
    }

    public Accounts getCurrent() {
        return current.getCurrentAccount();
    }

    public void setCurrent(Accounts account) {
        current.setCurrentAccount(account);
    }

    public Accounts getSelected() {
        return current.getCurrentAccount();
    }

    public String prepareLogin() {
        return "/accounts/Login?faces-redirect=true";
    }

    /**
     * *
     * This method verifies if the user exists and if she has written the
     * correct password for that specific account.
     *
     * @return redirections to start page or null
     */
    public String login() {
        Accounts tmp = ejbFacade.findByLogin(getCurrent().getLogin());
        if (tmp != null) {
            if (tmp.getPassword().equals(getCurrent().getPassword())) {
                current.setCurrentAccount(tmp);
                current.setLoggedIn(true);
                return "/accounts/Startpage?faces-redirect=true";
            }
            JsfUtil.addErrorMessage("Wrong password!");
        } else {
            JsfUtil.addErrorMessage("An account with that login doesn't exist.");
        }
        return null;
    }

    public String prepareLogout() {
        current.setCurrentAccount(new Accounts());
        return "/index?faces-redirect=true";
    }

    public void setLoggedOut() {
        current.setLoggedIn(false);
    }

    public String prepareCreatePage() {
        return "Create";
    }

    private AccountsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current.setCurrentAccount((Accounts) getItems().getRowData());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current.setCurrentAccount(new Accounts());
        selectedItemIndex = -1;
        return "Create";
    }

    /**
     * *
     * Creates an new account acording to the input from the user, if there is
     * no account with the requested login, the user is redirected to the start
     * page.
     *
     * @return redirection for start page or null
     */
    public String createAndLogin() {
        try {
            getFacade().create(getCurrent());
            current.setCurrentAccount(getCurrent());
            current.setLoggedIn(true);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AccountsCreated"));
            return "Startpage.xhtml?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Accounts getAccounts(java.lang.String id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Accounts.class)
    public static class AccountsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AccountsController controller = (AccountsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "accountsController");
            return controller.getAccounts(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Accounts) {
                Accounts o = (Accounts) object;
                return getStringKey(o.getLogin());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Accounts.class.getName());
            }
        }

    }

}
