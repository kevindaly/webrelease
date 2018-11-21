package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.Session.TextAreaFacade;
import com.webrelease.beans.entity.ReleaseManager;
import com.webrelease.beans.entity.ReleaseTypeQuestions;
import com.webrelease.beans.entity.TextArea;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;


@Named(value = "textAreaController")

@SessionScoped
public class TextAreaController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.TextAreaFacade ejbFacade;
    private List<TextArea> items = null;
    private TextArea selected;

    @ManagedProperty(value = "#{releaseManagerController}")
    private ReleaseManagerController release;
 
    
    private  ReleaseTypeQuestions thisQuestion = null;

    public ReleaseTypeQuestions getThisQuestion() {
        return thisQuestion;
    }

    public void setThisQuestion(ReleaseTypeQuestions thisQuestion) {
        this.thisQuestion = thisQuestion;
    }

    private String questionCurrent;

    public String getQuestionCurrent() {
        return questionCurrent;
    }

    public void setQuestionCurrent(String questionCurrent) {
        this.questionCurrent = questionCurrent;
    }
    
    
    @PostConstruct
    private void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        release = (ReleaseManagerController) context.getApplication().evaluateExpressionGet(context, "#{releaseManagerController}", ReleaseManagerController.class);
   }

    public ReleaseManagerController getRelease() {
        return release;
    }

    public void setRelease(ReleaseManagerController release) {
        this.release = release;
    }
    
    public TextAreaController() {
    }

    public TextArea getSelected() {
        return selected;
    }

    public void setSelected(TextArea selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TextAreaFacade getFacade() {
        return ejbFacade;
    }

     public TextArea prepareCreate(ReleaseTypeQuestions question) {
        this.thisQuestion = question;
        return prepareCreate();
    }
   
    public TextArea prepareCreate() {
        selected = new TextArea();
        initializeEmbeddableKey();
       return selected;
    }

    public void create() {
        selected.setReleaseId(release.getSelected());
        selected.setQuestionId(thisQuestion);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("TextAreaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("TextAreaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("TextAreaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TextArea> getItems() {
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

    public TextArea getTextArea(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<TextArea> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TextArea> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TextArea.class)
    public static class TextAreaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TextAreaController controller = (TextAreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "textAreaController");
            return controller.getTextArea(getKey(value));
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
            if (object instanceof TextArea) {
                TextArea o = (TextArea) object;
                return getStringKey(o.getIdTextArea());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TextArea.class.getName()});
                return null;
            }
        }

    }

}
