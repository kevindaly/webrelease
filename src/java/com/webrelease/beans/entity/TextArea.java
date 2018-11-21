/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author  Kevin Daly 
 */
@Entity
@Table(name = "TEXT_AREA", catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TextArea.findAll", query = "SELECT t FROM TextArea t"),
    @NamedQuery(name = "TextArea.findByIdTextArea", query = "SELECT t FROM TextArea t WHERE t.idTextArea = :idTextArea"),
    @NamedQuery(name = "TextArea.findByTextArea", query = "SELECT t FROM TextArea t WHERE t.textArea = :textArea")})

@EntityListeners(TextAreaListener.class)

public class TextArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "id_text_area", nullable = false, length = 48)
    private String idTextArea;
    @Size(max = 4096)
    @Column(name = "text_area", length = 4096)
    private String textArea;
    @JoinColumn(name = "question_id", referencedColumnName = "id_question")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReleaseTypeQuestions questionId;
    @JoinColumn(name = "release_id", referencedColumnName = "id_release_number")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReleaseManager releaseId;

    public TextArea() {
    }

    public TextArea(String idTextArea) {
        this.idTextArea = idTextArea;
    }

    public String getIdTextArea() {
        return idTextArea;
    }

    public void setIdTextArea(String idTextArea) {
        this.idTextArea = idTextArea;
    }

    public String getTextArea() {
        return textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public ReleaseTypeQuestions getQuestionId() {
        return questionId;
    }

    public void setQuestionId(ReleaseTypeQuestions questionId) {
        this.questionId = questionId;
    }

    public ReleaseManager getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(ReleaseManager releaseId) {
        this.releaseId = releaseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTextArea != null ? idTextArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TextArea)) {
            return false;
        }
        TextArea other = (TextArea) object;
        if ((this.idTextArea == null && other.idTextArea != null) || (this.idTextArea != null && !this.idTextArea.equals(other.idTextArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.TextArea[ idTextArea=" + idTextArea + " ]";
    }
    
}
