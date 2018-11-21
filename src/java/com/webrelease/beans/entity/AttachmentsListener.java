/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;;

import com.webrelease.beans.entity.Attachments;
import java.util.Calendar;
import java.util.UUID;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author  Kevin Daly 
 */
public class AttachmentsListener {

    @PrePersist
    public void prePersist(Object object) {
       Attachments  attachments = (Attachments) object;
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        attachments.setIdAttachment(randomUUIDString);
 }

    @PreUpdate
    public void preUpdate(Object object) {
        Attachments  attachments = (Attachments) object;
    }

}
