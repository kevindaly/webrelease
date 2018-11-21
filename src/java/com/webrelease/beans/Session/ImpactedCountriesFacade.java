/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.Session;

import com.webrelease.beans.entity.ImpactedCountries;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author  Kevin Daly 
 */
@Stateless
public class ImpactedCountriesFacade extends AbstractFacade<ImpactedCountries> {
    @PersistenceContext(unitName = "WebRelease2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpactedCountriesFacade() {
        super(ImpactedCountries.class);
    }
    
}
