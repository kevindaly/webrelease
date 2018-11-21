/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webrelease.beans.entity;

;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author  Kevin Daly 
 */


public class ReleaseManagerListener {

    @PrePersist
    public void prePersist(Object object) {
        try {
            ReleaseManager releaseManager = (ReleaseManager) object;
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();

            String app = releaseManager.getApplicationName().getApplicationName();

            Date start_date = releaseManager.getReleaseStart();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String strDate = simpleDateFormat.format(start_date);

            if ("RTR".compareToIgnoreCase(releaseManager.getApplicationName().getApplicationName()) == 0) {
                releaseManager.setIdReleaseNumber(app + randomUUIDString);
                releaseManager.setReleaseId(app + releaseManager.getVersionNumber() + "_" + strDate + "_HB");
            } else {
                releaseManager.setIdReleaseNumber(app + randomUUIDString);
                releaseManager.setReleaseId(app + "_" + strDate + "_HB");
            }
            releaseManager.setIdReleaseNumber(randomUUIDString);
            releaseManager.setChangeType("HB");
            releaseManager.setRrmApproved("No");
            releaseManager.setRrmApprovedAutomation("No");
            releaseManager.setStatusInd("New");
            if (releaseManager.getVersionNumber() == null || releaseManager.getVersionNumber().isEmpty()) {
                releaseManager.setVersionNumber("NONE");
            }
            if (releaseManager.getDbaTask() == null) {
                releaseManager.setDbaTask("No");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @PreUpdate
    public void preUpdate(Object object) {
        ReleaseManager releaseManager = (ReleaseManager) object;
       
    }

}
