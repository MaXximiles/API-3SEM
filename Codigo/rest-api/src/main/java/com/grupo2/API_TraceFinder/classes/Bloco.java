package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bloco")
public class Bloco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bloco_id")
	private Long blocoid;
	
	@Column(name="bloco_secao")
	private String blocosecao;
	
	@Column(name="bloco_subsecao")
	private String blocosubsecao;
	
	@Column(name="bloco_nomebloco")
	private String bloconomebloco;
	
	@Column(name="bloco_codebloco")
	private String blococodebloco;
	
	@Column(name="bloco_caminho")
	private String blococaminho;

	public Long getBlocoid() {
		return blocoid;
	}

	public void setBlocoid(Long blocoid) {
		this.blocoid = blocoid;
	}

	public String getBlocosecao() {
		return blocosecao;
	}

	public void setBlocosecao(String blocosecao) {
		this.blocosecao = blocosecao;
	}

	public String getBlocosubsecao() {
		return blocosubsecao;
	}

	public void setBlocosubsecao(String blocosubsecao) {
		this.blocosubsecao = blocosubsecao;
	}

	public String getBloconomebloco() {
		return bloconomebloco;
	}

	public void setBloconomebloco(String bloconomebloco) {
		this.bloconomebloco = bloconomebloco;
	}

	public String getBlococodebloco() {
		return blococodebloco;
	}

	public void setBlococodebloco(String blococodebloco) {
		this.blococodebloco = blococodebloco;
	}

	public String getBlococaminho() {
		return blococaminho;
	}

	public void setBlococaminho(String blococaminho) {
		this.blococaminho = blococaminho;
	}
	
		
	
}
