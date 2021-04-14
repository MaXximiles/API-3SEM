package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traco_arquivo")
public class TracoArquivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="traco_arquivo_id")
	private Long tracoarquivoid;
	
	@Column(name="traco_arquivo_nome")
	private String tracoarquivonome;
	
	@Column(name="traco_arquivo_descricao")
	private String tracoarquivodescricao;
	
	@Column(name="traco_arquivo_codigo")
	private String tracoarquivocodigo;

	public Long getTracoarquivoid() {
		return tracoarquivoid;
	}

	public void setTracoarquivoid(Long tracoarquivoid) {
		this.tracoarquivoid = tracoarquivoid;
	}

	public String getTracoarquivonome() {
		return tracoarquivonome;
	}

	public void setTracoarquivonome(String tracoarquivonome) {
		this.tracoarquivonome = tracoarquivonome;
	}

	public String getTracoarquivodescricao() {
		return tracoarquivodescricao;
	}

	public void setTracoarquivodescricao(String tracoarquivodescricao) {
		this.tracoarquivodescricao = tracoarquivodescricao;
	}

	public String getTracoarquivocodigo() {
		return tracoarquivocodigo;
	}

	public void setTracoarquivocodigo(String tracoarquivocodigo) {
		this.tracoarquivocodigo = tracoarquivocodigo;
	}
	
		
	
}
