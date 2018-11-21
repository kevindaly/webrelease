package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.Session.UnixSaFacade;
import com.webrelease.beans.entity.ReleaseManager;
import com.webrelease.beans.entity.UnixSa;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named("unixSaController")
@SessionScoped
public class UnixSaController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.UnixSaFacade ejbFacade;
    private List<UnixSa> items = null;
    private UnixSa selected;

       private ReleaseManagerController release;

    public UnixSaController() {
    }

    public UnixSa getSelected() {
        return selected;
    }

    public void setSelected(UnixSa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UnixSaFacade getFacade() {
        return ejbFacade;
    }
    private void initProperties(){
        FacesContext context = FacesContext.getCurrentInstance();
        release = (ReleaseManagerController) context.getApplication().evaluateExpressionGet(context, "#{releaseManagerController}", ReleaseManagerController.class);
    
    }

    


     @PostConstruct
    private void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        release = (ReleaseManagerController) context.getApplication().evaluateExpressionGet(context, "#{release}", ReleaseManagerController.class);
   }
    
    public UnixSa prepareCreate() {

        selected = new UnixSa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        initProperties();
        selected.setReleaseId(release.getSelected());

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("UnixSaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("UnixSaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("UnixSaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UnixSa> getItems() {
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
                    if(persistAction != PersistAction.CREATE){
                    getFacade().edit(selected);
                    }
                    else{
                        getFacade().create(selected);
                    }
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

    public UnixSa getUnixSa(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<UnixSa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UnixSa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UnixSa.class)
    public static class UnixSaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UnixSaController controller = (UnixSaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "unixSaController");
            return controller.getUnixSa(getKey(value));
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
            if (object instanceof UnixSa) {
                UnixSa o = (UnixSa) object;
                return getStringKey(o.getIdTask());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UnixSa.class.getName()});
                return null;
            }
        }

    }

}
