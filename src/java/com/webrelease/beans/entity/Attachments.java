/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webrelease.beans.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Cacheable(false)
@Table(catalog = "", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attachments.findAll", query = "SELECT a FROM Attachments a"),
    @NamedQuery(name = "Attachments.findByIdAttachment", query = "SELECT a FROM Attachments a WHERE a.idAttachment = :idAttachment"),
    @NamedQuery(name = "Attachments.findByAttachmentLocation", query = "SELECT a FROM Attachments a WHERE a.attachmentLocation = :attachmentLocation")})

@EntityListeners(AttachmentsListener.class)

public class Attachments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 48)
    @Column(name = "id_attachment", nullable = false, length = 48)
    private String idAttachment;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "attachment_location", nullable = false, length = 1024)
    private String attachmentLocation;
    @JoinColumn(name = "release_id", referencedColumnName = "id_release_number")
    @ManyToOne(fetch = FetchType.LAZY)
    private ReleaseManager releaseId;

    public Attachments() {
    }

    public Attachments(String idAttachment) {
        this.idAttachment = idAttachment;
    }

    public Attachments(String idAttachment, String attachmentLocation) {
        this.idAttachment = idAttachment;
        this.attachmentLocation = attachmentLocation;
    }

    public String getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(String idAttachment) {
        this.idAttachment = idAttachment;
    }

    public String getAttachmentLocation() {
        return attachmentLocation;
    }

    public void setAttachmentLocation(String attachmentLocation) {
        this.attachmentLocation = attachmentLocation;
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
        hash += (idAttachment != null ? idAttachment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attachments)) {
            return false;
        }
        Attachments other = (Attachments) object;
        if ((this.idAttachment == null && other.idAttachment != null) || (this.idAttachment != null && !this.idAttachment.equals(other.idAttachment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jpmorgan.beans.entity.Attachments[ idAttachment=" + idAttachment + " ]";
    }
    
}
