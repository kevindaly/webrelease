/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author  Kevin Daly 
 */
@Entity
@Cacheable(true)
@Table(name = "RELEASE_TYPE_QUESTIONS", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReleaseTypeQuestions.findAll", query = "SELECT r FROM ReleaseTypeQuestions r order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByIdQuestion", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.idQuestion = :idQuestion order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByQuestionOrder", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.questionOrder = :questionOrder order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByQuestionName", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.questionName = :questionName order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByQuestionDescription", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.questionDescription = :questionDescription order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByBooleanYesNo", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.booleanYesNo = :booleanYesNo order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByMandatoryYesNo", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.mandatoryYesNo = :mandatoryYesNo order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByTextTemplate", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.textTemplate = :textTemplat order by r.questionOrder"),
    @NamedQuery(name = "ReleaseTypeQuestions.findByQuestionType", query = "SELECT r FROM ReleaseTypeQuestions r WHERE r.questionType =:questionType  ORDER BY r.questionOrder")})


public class ReleaseTypeQuestions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "id_question", nullable = false, length = 48)
    private String idQuestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "question_order", nullable = false)
    private int questionOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "question_name", nullable = false, length = 48)
    private String questionName;
    @Size(max = 255)
    @Column(name = "question_description", length = 255)
    private String questionDescription;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "boolean_yes_no", nullable = false, length = 3)
    private String booleanYesNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "mandatory_yes_no", nullable = false, length = 3)
    private String mandatoryYesNo;
    @Size(max = 2048)
    @Column(name = "text_template", length = 2048)
    private String textTemplate;
    @OneToMany(mappedBy = "questionId", fetch = FetchType.LAZY)
    private List<TextArea> textAreaList;
    @JoinColumn(name = "question_property", referencedColumnName = "id_properties", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ReleaseProperties questionProperty;
    @JoinColumn(name = "question_type", referencedColumnName = "application_name", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ReleaseApplication questionType;

    public ReleaseTypeQuestions() {
    }

    public ReleaseTypeQuestions(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public ReleaseTypeQuestions(String idQuestion, int questionOrder, String questionName, String booleanYesNo, String mandatoryYesNo) {
        this.idQuestion = idQuestion;
        this.questionOrder = questionOrder;
        this.questionName = questionName;
        this.booleanYesNo = booleanYesNo;
        this.mandatoryYesNo = mandatoryYesNo;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getBooleanYesNo() {
        return booleanYesNo;
    }

    public void setBooleanYesNo(String booleanYesNo) {
        this.booleanYesNo = booleanYesNo;
    }

    public String getMandatoryYesNo() {
        return mandatoryYesNo;
    }

    public void setMandatoryYesNo(String mandatoryYesNo) {
        this.mandatoryYesNo = mandatoryYesNo;
    }

    public String getTextTemplate() {
        return textTemplate;
    }

    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }

    @XmlTransient
    public List<TextArea> getTextAreaList() {
        return textAreaList;
    }

    public void setTextAreaList(List<TextArea> textAreaList) {
        this.textAreaList = textAreaList;
    }

    public ReleaseProperties getQuestionProperty() {
        return questionProperty;
    }

    public void setQuestionProperty(ReleaseProperties questionProperty) {
        this.questionProperty = questionProperty;
    }

    public ReleaseApplication getQuestionType() {
        return questionType;
    }

    public void setQuestionType(ReleaseApplication questionType) {
        this.questionType = questionType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestion != null ? idQuestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReleaseTypeQuestions)) {
            return false;
        }
        ReleaseTypeQuestions other = (ReleaseTypeQuestions) object;
        if ((this.idQuestion == null && other.idQuestion != null) || (this.idQuestion != null && !this.idQuestion.equals(other.idQuestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.ReleaseTypeQuestions[ idQuestion=" + idQuestion + " ]";
    }
    
}
