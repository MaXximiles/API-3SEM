package com.fatec.bd.tracefinder.restapi.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documento_info")
public class DocumentoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int docId;
    private String docName;
    private Integer docParentId;
    private Date docLastmodified;
    private String docType;
    private List<DocumentoInfo> docChildren = new ArrayList<DocumentoInfo>();
    
    public DocumentoInfo(int docId, String docName, Date docLastmodified, String docType) {
        this.docId = docId;
        this.docName = docName;
        this.docLastmodified = docLastmodified;
        this.docType = docType;
    }

    public int getDocId() {
        return docId;
    }
    public void setDocId(int docId) {
        this.docId = docId;
    }
    public String getDocName() {
        return docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }
    public Integer getDocParentId() {
        return docParentId;
    }
    public void setDocParentId(Integer docParentId) {
        this.docParentId = docParentId;
    }
    public List<DocumentoInfo> getDocChildren() {
        return docChildren;
    }
    public void setDocChildren(List<DocumentoInfo> docChildren) {
        this.docChildren = docChildren;
    }

    public Date getDocLastmodified() {
        return docLastmodified;
    }

    public void setDocLastmodified(Date docLastmodified) {
        this.docLastmodified = docLastmodified;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
}
