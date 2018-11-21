package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.entity.ReleaseMiscInfo;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import com.webrelease.beans.Session.ReleaseMiscInfoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("releaseMiscInfoController")
@SessionScoped
public class ReleaseMiscInfoController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.ReleaseMiscInfoFacade ejbFacade;
    private List<ReleaseMiscInfo> items = null;
    private ReleaseMiscInfo selected;

    public ReleaseMiscInfoController() {
    }

    public ReleaseMiscInfo getSelected() {
        return selected;
    }

    public void setSelected(ReleaseMiscInfo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReleaseMiscInfoFacade getFacade() {
        return ejbFacade;
    }

    public ReleaseMiscInfo prepareCreate() {
        selected = new ReleaseMiscInfo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseMiscInfoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseMiscInfoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseMiscInfoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ReleaseMiscInfo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/WebRelease").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/WebRelease").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ReleaseMiscInfo getReleaseMiscInfo(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ReleaseMiscInfo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ReleaseMiscInfo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ReleaseMiscInfo.class)
    public static class ReleaseMiscInfoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReleaseMiscInfoController controller = (ReleaseMiscInfoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "releaseMiscInfoController");
            return controller.getReleaseMiscInfo(getKey(value));
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
            if (object instanceof ReleaseMiscInfo) {
                ReleaseMiscInfo o = (ReleaseMiscInfo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ReleaseMiscInfo.class.getName()});
                return null;
            }
        }

    }

}
