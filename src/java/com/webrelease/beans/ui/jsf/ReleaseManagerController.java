package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.Session.ReleaseManagerFacade;
import com.webrelease.beans.entity.ReleaseManager;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named(value = "releaseManagerController")
@SessionScoped
public class ReleaseManagerController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.ReleaseManagerFacade ejbFacade;
    private List<ReleaseManager> items = null;
    private ReleaseManager selected;

    private boolean dba;

    private boolean unixsa;

    private Map<String,String> availableRegions;

  
    public ReleaseManagerController() {
    }

     @PostConstruct
    private void init() {
        availableRegions = new HashMap<String,String>();
        availableRegions.put("EMEA","EMEA");
        availableRegions.put("APAC","APAC");   
        availableRegions.put("NA","NA");   
    }
    
    
    public ReleaseManager getSelected() {
        return selected;
    }

    public void setSelected(ReleaseManager selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReleaseManagerFacade getFacade() {
        return ejbFacade;
    }

    public ReleaseManager prepareCreate() {
        selected = new ReleaseManager();
        initializeEmbeddableKey();
        return selected;
    }

    public String create() {
        try {
            if (dba) {
                selected.setDbaTask("Yes");
            } else {
                selected.setDbaTask("No");
            }

            if (unixsa) {
                //selected.set("Yes");
            } else {
                //selected.setDbaTask("No");
            }

            persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseManagerCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        } catch (Throwable t) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, t.getMessage(), " failed to save new release.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(AttachmentsController.class.getName()).log(Level.SEVERE, null, t);
            return "failure";
        }
        return "/questionForm/Answer_questions";
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseManagerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseManagerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ReleaseManager> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {

                if (persistAction == PersistAction.UPDATE) {
                    getFacade().edit(selected);
                } else if (persistAction == PersistAction.CREATE) {
                    getFacade().create(selected);
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

    public ReleaseManager getReleaseManager(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ReleaseManager> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ReleaseManager> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     *
     * @return
     */
    public String nextPage() {
        return "/questionForm/index";
    }

      public Map<String, String> getAvailableRegions() {
        return availableRegions;
    }

    public void setAvailableRegions(Map<String, String> availableRegions) {
        this.availableRegions = availableRegions;
    }
    
    private ArrayList<String> regions;

    public ArrayList<String> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<String> regions) {
        StringBuilder regionStr = new StringBuilder();
        boolean first = true;
        for (String region : regions) {
            if (first) {
                regionStr.append(region);
                first=false;
            } else {
                regionStr.append("," + region);
            }
        }
        selected.setRegionsImpacted(regionStr.toString());
        this.regions = regions;
    }

    public boolean isUnixsa() {
        return unixsa;
    }

    public void setUnixsa(boolean unixsa) {
        this.unixsa = unixsa;
    }

    public boolean isDba() {
        return dba;
    }

    public void setDba(boolean dba) {
        this.dba = dba;
    }

    
    
    /**
     * 
     * 
     * 
     * 
     */
    
    
    @FacesConverter(forClass = ReleaseManager.class)
    public static class ReleaseManagerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReleaseManagerController controller = (ReleaseManagerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "releaseManagerController");
            return controller.getReleaseManager(getKey(value));
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
            if (object instanceof ReleaseManager) {
                ReleaseManager o = (ReleaseManager) object;
                return getStringKey(o.getIdReleaseNumber());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ReleaseManager.class.getName()});
                return null;
            }
        }

    }

}
