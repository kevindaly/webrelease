package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.Session.ReleaseTypeQuestionsFacade;
import com.webrelease.beans.entity.ReleaseTypeQuestions;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.Collection;
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
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named("releaseTypeQuestionsController")
@SessionScoped
public class ReleaseTypeQuestionsController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.ReleaseTypeQuestionsFacade ejbFacade;
    private List<ReleaseTypeQuestions> items = null;
    private ReleaseTypeQuestions selected;

    private String selectedValue;

    private ReleaseManagerController release;


    @PostConstruct
    private void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        release = (ReleaseManagerController) context.getApplication().evaluateExpressionGet(context, "#{releaseManagerController}", ReleaseManagerController.class);
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public ReleaseTypeQuestionsController() {
    }

    public ReleaseTypeQuestions getSelected() {
        return selected;
    }

    public void setSelected(ReleaseTypeQuestions selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReleaseTypeQuestionsFacade getFacade() {
        return ejbFacade;
    }

    public ReleaseTypeQuestions prepareCreate() {
        selected = new ReleaseTypeQuestions();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseTypeQuestionsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseTypeQuestionsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("ReleaseTypeQuestionsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ReleaseTypeQuestions> getItems() {
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

    public ReleaseTypeQuestions getReleaseTypeQuestions(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ReleaseTypeQuestions> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ReleaseTypeQuestions> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public Collection<ReleaseTypeQuestions> getQuestions() {
        String application = release.getSelected().getApplicationName().getApplicationName();
        return ejbFacade.getQuestions(application);
    }

    public void selectedChanged(ValueChangeEvent e) {
        Object value = e.getNewValue();
        value.toString();
    }

    @FacesConverter(forClass = ReleaseTypeQuestions.class)
    public static class ReleaseTypeQuestionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReleaseTypeQuestionsController controller = (ReleaseTypeQuestionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "releaseTypeQuestionsController");
            return controller.getReleaseTypeQuestions(getKey(value));
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
            if (object instanceof ReleaseTypeQuestions) {
                ReleaseTypeQuestions o = (ReleaseTypeQuestions) object;
                return getStringKey(o.getIdQuestion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ReleaseTypeQuestions.class.getName()});
                return null;
            }
        }

    }

}
