package com.webrelease.beans.ui.jsf;

import com.webrelease.beans.Session.AttachmentsFacade;
import com.webrelease.beans.entity.Attachments;
import com.webrelease.beans.entity.ReleaseManager;
import com.webrelease.beans.ui.jsf.util.JsfUtil;
import com.webrelease.beans.ui.jsf.util.JsfUtil.PersistAction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.System;
import java.util.List;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * Upload documents and attach to database and store in sharepoint folder
 *
 *
 *
 * @author  Kevin Daly 
 */
@Named("attachmentsController")
@SessionScoped
public class AttachmentsController implements Serializable {

    @EJB
    private com.webrelease.beans.Session.AttachmentsFacade ejbFacade;
    private List<Attachments> items = null;
    private Attachments selected;

    private ReleaseManagerController release;

    public AttachmentsController() {
    }

  
    @PostConstruct
    private void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        release = (ReleaseManagerController) context.getApplication().evaluateExpressionGet(context, "#{releaseManagerController}", ReleaseManagerController.class);
    }

    public Attachments getSelected() {
        return selected;
    }

    public void setSelected(Attachments selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AttachmentsFacade getFacade() {
        return ejbFacade;
    }

    public Attachments prepareCreate() {
        selected = new Attachments();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/WebRelease").getString("AttachmentsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/WebRelease").getString("AttachmentsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/WebRelease").getString("AttachmentsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Attachments> getItems() {
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

    public Attachments getAttachments(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Attachments> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Attachments> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        String fileName = null;
        try {
            fileName = event.getFile().getFileName();
            InputStream stream = event.getFile().getInputstream();
            addFileToExistingDirectory(fileName, stream);

          
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getFile().getFileName(), " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, event.getFile().getFileName(), " failed to upload.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(AttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Sharepoint is mounted on the s: drive
     *
     */
    private String createDirectoryOnSharepoint(ReleaseManager releaseManager) {
        String path = "c:\\sharepoint_local\\release_management\\";
        if (releaseManager != null) {
            path += releaseManager.getReleaseId();
            File file = new File(path);

            if (!file.exists()) {
                file.mkdir();
            }
        }
        return path;

    }

    /**
     *
     * @param filePath
     * @param fileName
     * @param file
     */
    private void addFileToExistingDirectory(String fileName, InputStream in) throws Exception {
        OutputStream out = null;
        String location = null;
        try {
            ReleaseManager releaseId = release.getSelected();
            String app = releaseId.getApplicationName().getApplicationName();
            String directory = createDirectoryOnSharepoint(releaseId);
            // write file to directory
            location = directory + "\\" + fileName;
            File file = new File(location);
            out = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            // create db record     
            // what goes here? need to create a new Attachments entity these are normally created by the 
            // browser using attachmentsController.preparecreate
            // this is created already!
           
            
            prepareCreate(); // new instance of selected
            
            selected.setAttachmentLocation(location);
            // release may not be null
            selected.setReleaseId(release.getSelected());
            create();

        } finally {
            in.close();
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     *
     *
     *
     *
     *
     */
    @FacesConverter(forClass = Attachments.class)
    public static class AttachmentsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AttachmentsController controller = (AttachmentsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "attachmentsController");
            return controller.getAttachments(getKey(value));
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
            if (object instanceof Attachments) {
                Attachments o = (Attachments) object;
                return getStringKey(o.getIdAttachment());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Attachments.class.getName()});
                return null;
            }
        }

    }

}
