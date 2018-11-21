package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.entity.ReleaseOptions;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import com.webrelease.beans.Session.ReleaseOptionsFacade;

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

@Named("releaseOptionsController")
@SessionScoped
public class ReleaseOptionsController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.ReleaseOptionsFacade ejbFacade;
    private List<ReleaseOptions> items = null;
    private ReleaseOptions selected;

    public ReleaseOptionsController() {
    }

    public ReleaseOptions getSelected() {
        return selected;
    }

    public void setSelected(ReleaseOptions selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReleaseOptionsFacade getFacade() {
        return ejbFacade;
    }

    public ReleaseOptions prepareCreate() {
        selected = new ReleaseOptions();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseOptionsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseOptionsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseOptionsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ReleaseOptions> getItems() {
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

    public ReleaseOptions getReleaseOptions(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ReleaseOptions> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ReleaseOptions> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ReleaseOptions.class)
    public static class ReleaseOptionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReleaseOptionsController controller = (ReleaseOptionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "releaseOptionsController");
            return controller.getReleaseOptions(getKey(value));
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
            if (object instanceof ReleaseOptions) {
                ReleaseOptions o = (ReleaseOptions) object;
                return getStringKey(o.getIdOptions());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ReleaseOptions.class.getName()});
                return null;
            }
        }

    }

}
