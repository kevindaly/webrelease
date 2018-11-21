package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.entity.DbaTaskDetails;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import com.webrelease.beans.Session.DbaTaskDetailsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("dbaTaskDetailsController")
@SessionScoped
public class DbaTaskDetailsController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.DbaTaskDetailsFacade ejbFacade;
    private List<DbaTaskDetails> items = null;
    private DbaTaskDetails selected;

    
      private ReleaseManagerController release;
    
    public DbaTaskDetailsController() {
    }

       @PostConstruct
    private void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        release = (ReleaseManagerController) context.getApplication().evaluateExpressionGet(context, "#{release}", ReleaseManagerController.class);
   }
    
    
    public DbaTaskDetails getSelected() {
        return selected;
    }

    public void setSelected(DbaTaskDetails selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DbaTaskDetailsFacade getFacade() {
        return ejbFacade;
    }
 private void initProperties(){
        FacesContext context = FacesContext.getCurrentInstance();
        release = (ReleaseManagerController) context.getApplication().evaluateExpressionGet(context, "#{releaseManagerController}", ReleaseManagerController.class);
    
    }
    public DbaTaskDetails prepareCreate() {
        selected = new DbaTaskDetails();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
         initProperties();
        selected.setReleaseId(release.getSelected());

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("DbaTaskDetailsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("DbaTaskDetailsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("DbaTaskDetailsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DbaTaskDetails> getItems() {
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

    public DbaTaskDetails getDbaTaskDetails(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<DbaTaskDetails> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DbaTaskDetails> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DbaTaskDetails.class)
    public static class DbaTaskDetailsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DbaTaskDetailsController controller = (DbaTaskDetailsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "dbaTaskDetailsController");
            return controller.getDbaTaskDetails(getKey(value));
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
            if (object instanceof DbaTaskDetails) {
                DbaTaskDetails o = (DbaTaskDetails) object;
                return getStringKey(o.getPhasedId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DbaTaskDetails.class.getName()});
                return null;
            }
        }

    }

}
