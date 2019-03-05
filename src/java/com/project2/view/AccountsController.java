package com.project2.view;

import com.project2.model.Accounts;
import com.project2.view.util.JsfUtil;
import com.project2.view.util.PaginationHelper;
import com.project2.model.AccountsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("accountsController")
@SessionScoped
public class AccountsController implements Serializable {

    private Accounts current = new Accounts();
    private String tmpPassword;
    private DataModel items = null;
    @EJB
    private AccountsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String tmpLogin;
    private AccountFruitListController tmp = new AccountFruitListController();

    public void setTmpLogin(String tmpLogin) {
        this.tmpLogin = tmpLogin;
    }

    public String getTmpLogin() {
        return tmpLogin;
    }

    public AccountsController() {
    }

    public void setTmpPassword(String tmp) {
        tmpPassword = tmp;
        System.out.print("Password reset sucess: " + tmpPassword);
    }

    public void checkPasswordChange() {
        //Try to get a user by the entered name.
        List l = ejbFacade.findByLogin(tmpLogin);

        //Checks if the user exists. 
        if (!l.isEmpty()) {
            Accounts tmp = (Accounts) l.get(0);
            tmp.setPassword(tmpPassword);
            getFacade().edit(tmp); //Important row, needed for updating the database. 
            JsfUtil.addSuccessMessage("Password changed for: " + tmpLogin);
        } //Not a vaild name - send error msg. 
        else {
            JsfUtil.addErrorMessage("Invaild user name, please try again.");
        }
    }

    public String getTmpPassword() {
        return tmpPassword;
    }

    public Accounts getCurrent() {
        return current;
    }

    public Accounts getSelected() {
        if (current == null) {
            current = new Accounts();
            selectedItemIndex = -1;
        }
        return current;
    }

    public String prepareLogin() {

        List l = ejbFacade.findByLogin(current.getLogin());
        //throw new IllegalStateException("Inloggad!" + l);
        if (!l.isEmpty()) {
            Accounts tmp = (Accounts) l.get(0);
            if (tmp.getPassword().equals(current.getPassword())) {
                current = tmp;
                System.out.println("current i accountscontroller " + current);
                return "Startpage";
                //throw new IllegalStateException("Inloggad!" + l.toString());
            }
        }
        return "Login";
    }

    public String resetPassword() {
        if (tmpPassword.length() < 3) {
            JsfUtil.addErrorMessage("Pssword must be longer than 3");
            return "ResetPas";
        } else {
            current.setPassword(tmpPassword);
            JsfUtil.addSuccessMessage("Password changed");
            return "ResetPas";
        }
    }

    public String prepareLogout() {
        current = new Accounts();
        return "home";
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
        current = (Accounts) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Accounts();
        selectedItemIndex = -1;
        return "Create";
    }

    public String createAndLogin() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AccountsCreated"));
            tmp.setCurrent(current);
            return "Startpage.xhtml?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return "Create.xhtml?faces-redirect=true";
        }
    }

    public String prepareEdit() {
        current = (Accounts) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AccountsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Accounts) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AccountsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
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
