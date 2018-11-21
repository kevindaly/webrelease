/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.ui.jsf;

/**
 *
 * @author  Kevin Daly 
 */
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
  
import org.primefaces.event.FileUploadEvent;  
import org.primefaces.model.UploadedFile;  
  
public class FileUploadController {  
  
    
     @EJB
    private com.webrelease.beans.Session.AttachmentsFacade ejbFacade;
    
    
    
    
    public void handleFileUpload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}  
                      