/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.Session;

import com.webrelease.beans.entity.ReleaseProperties;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author  Kevin Daly 
 */
@Stateless
public class ReleasePropertiesFacade extends AbstractFacade<ReleaseProperties> {
    @PersistenceContext(unitName = "WebRelease2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReleasePropertiesFacade() {
        super(ReleaseProperties.class);
    }
    
}
