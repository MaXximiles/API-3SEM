package com.grupo2.API_TraceFinder.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

  /*
   * @OneToMany
   * 
   * @JoinTable(name = "codelist")
   * 
   * @JoinColumn(name = "documento_id") private List<Codelist> codelist;
   * 
   * 
   * 
   * public List<Codelist> getCodelist() { return codelist; }
   * 
   * public void setCodelist(List<Codelist> codelist) { this.codelist = codelist;
   * }
   */

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
