package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Usuario;

public class UsuarioRs {
	private Long usuario_id;
	private String usuario_nome;
	private String usuario_email;
	private String usuario_senha;
	private String usuario_nivel;
	private String usuario_login;
	
	public static UsuarioRs converter(Usuario user) {
		var usuario = new UsuarioRs();
		usuario.setUsuario_id(user.getUsuario_id());
		usuario.setUsuario_nome(user.getUsuario_nome());
		usuario.setUsuario_login(user.getUsuario_login());
		usuario.setUsuario_senha(user.getUsuario_senha());
		usuario.setUsuario_email(user.getUsuario_email());
		usuario.setUsuario_nivel(user.getUsuario_nivel());
		return usuario;
	}
	
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
