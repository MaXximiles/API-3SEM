package com.grupo2.API_TraceFinder.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Long usuario_id;
	
	@Column(name="usuario_nome")
	private String usuario_nome;
	
	@Column(name="usuario_email")
	private String usuario_email;
	
	@Column(name="usuario_senha")
	private String usuario_senha;
	
	@Column(name="usuario_nivel")
	private String usuario_nivel;
	
	@Column(name="usuario_login")
	private String usuario_login;

	
	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getUsuario_nome() {
		return usuario_nome;
	}

	public void setUsuario_nome(String usuario_nome) {
		this.usuario_nome = usuario_nome;
	}

	public String getUsuario_email() {
		return usuario_email;
	}

	public void setUsuario_email(String usuario_email) {
		this.usuario_email = usuario_email;
	}

	public String getUsuario_senha() {
		return usuario_senha;
	}

	public void setUsuario_senha(String usuario_senha) {
		this.usuario_senha = usuario_senha;
	}

	public String getUsuario_nivel() {
		return usuario_nivel;
	}

	public void setUsuario_nivel(String usuario_nivel) {
		this.usuario_nivel = usuario_nivel;
	}

	public String getUsuario_login() {
		return usuario_login;
	}

	public void setUsuario_login(String usuario_login) {
		this.usuario_login = usuario_login;
	}
	
	
	
	
	
	
	
	

}
