/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webrelease.beans.Session;

import com.webrelease.beans.entity.ReleaseApplication;
import com.webrelease.beans.entity.ReleaseTypeQuestions;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author  Kevin Daly 
 */
@Stateless
public class ReleaseTypeQuestionsFacade extends AbstractFacade<ReleaseTypeQuestions> {

    @PersistenceContext(unitName = "WebRelease2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReleaseTypeQuestionsFacade() {
        super(ReleaseTypeQuestions.class);
    }

    public List<ReleaseTypeQuestions> getQuestions(String type) {

        Query application = getEntityManager().createNamedQuery("ReleaseApplication.findByApplicationName");
        application.setParameter("applicationName", type);
        List<ReleaseApplication> app = application.getResultList();

        Query q = getEntityManager().createNamedQuery("ReleaseTypeQuestions.findByQuestionType");
        q.setParameter("questionType", app.get(0));
        List<ReleaseTypeQuestions> result = q.getResultList();

        return result;
    }

    /**
     *  get a single question
     * 
     * @param id
     * @return ReleaseTypeQuestions
     */
    public ReleaseTypeQuestions getQuestion(String id) {

        Query application = getEntityManager().createNamedQuery("ReleaseTypeQuestions.findByIdQuestion");
        application.setParameter("idQuestion", id);
        return (ReleaseTypeQuestions) application.getSingleResult();

    }

}
