package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.entity.ImpactedCountries;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import com.webrelease.beans.Session.ImpactedCountriesFacade;

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

@Named("impactedCountriesController")
@SessionScoped
public class ImpactedCountriesController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.ImpactedCountriesFacade ejbFacade;
    private List<ImpactedCountries> items = null;
    private ImpactedCountries selected;

    public ImpactedCountriesController() {
    }

    public ImpactedCountries getSelected() {
        return selected;
    }

    public void setSelected(ImpactedCountries selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImpactedCountriesFacade getFacade() {
        return ejbFacade;
    }

    public ImpactedCountries prepareCreate() {
        selected = new ImpactedCountries();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("ImpactedCountriesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("ImpactedCountriesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("ImpactedCountriesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ImpactedCountries> getItems() {
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

    public ImpactedCountries getImpactedCountries(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ImpactedCountries> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ImpactedCountries> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ImpactedCountries.class)
    public static class ImpactedCountriesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImpactedCountriesController controller = (ImpactedCountriesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "impactedCountriesController");
            return controller.getImpactedCountries(getKey(value));
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
            if (object instanceof ImpactedCountries) {
                ImpactedCountries o = (ImpactedCountries) object;
                return getStringKey(o.getCountryIsoId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImpactedCountries.class.getName()});
                return null;
            }
        }

    }

}
