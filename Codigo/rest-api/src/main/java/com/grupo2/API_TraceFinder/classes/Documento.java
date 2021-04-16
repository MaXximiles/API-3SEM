package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class Documento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "documento_id")
  private Long documentoid;

  @Column(name = "documento_nome")
  private String documentonome;

  @Column(name = "documento_pn")
  private String documentopn;

  @Column(name = "documento_caminho")
  private String documentocaminho;

  public Long getDocumentoid() {
    return documentoid;
  }

  public void setDocumentoid(Long documentoid) {
    this.documentoid = documentoid;
  }

  public String getDocumentonome() {
    return documentonome;
  }

  public void setDocumentonome(String documentonome) {
    this.documentonome = documentonome;
  }

  public String getDocumentopn() {
    return documentopn;
  }

  public void setDocumentopn(String documentopn) {
    this.documentopn = documentopn;
  }

  public String getDocumentocaminho() {
    return documentocaminho;
  }

  public void setDocumentocaminho(String documentocaminho) {
    this.documentocaminho = documentocaminho;
  }

  }

  

  

  

    
  

  

  

  

  
    
  

  
    
  

  
    
  

  
    
  

  

  