/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webrelease.beans.entity;

import java.util.Map;
import java.util.UUID;
import javax.faces.context.FacesContext;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author  Kevin Daly 
 */
public class UnixSaListener {

    
    
    @PrePersist
    public void prePersist(Object object) {
        UnixSa unixSa = (UnixSa) object;
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        unixSa.setIdTask(randomUUIDString);
 
        
    }

    @PreUpdate
    public void preUpdate(Object object) {
         UnixSa unixSa = ( UnixSa ) object;

    }

}
