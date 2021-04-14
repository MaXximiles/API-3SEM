package com.grupo2.API_TraceFinder.controller.dto;

import com.grupo2.API_TraceFinder.classes.Usuario;

public class UsuarioRs {
	private Long usuarioid;
	private String usuarionome;
	private String usuarioemail;
	private String usuariosenha;
	private String usuarionivel;
	private String usuariologin;
	
	public static UsuarioRs converter(Usuario user) {
		var usuario = new UsuarioRs();
		usuario.setUsuarioid(user.getUsuarioid());
		usuario.setUsuarionome(user.getUsuarionome());
		usuario.setUsuariologin(user.getUsuariologin());
		usuario.setUsuariosenha(user.getUsuariosenha());
		usuario.setUsuarioemail(user.getUsuarioemail());
		usuario.setUsuarionivel(user.getUsuarionivel());
		return usuario;
	}

	public Long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public String getUsuarionome() {
		return usuarionome;
	}

	public void setUsuarionome(String usuarionome) {
		this.usuarionome = usuarionome;
	}

	public String getUsuarioemail() {
		return usuarioemail;
	}

	public void setUsuarioemail(String usuarioemail) {
		this.usuarioemail = usuarioemail;
	}

	public String getUsuariosenha() {
		return usuariosenha;
	}

	public void setUsuariosenha(String usuariosenha) {
		this.usuariosenha = usuariosenha;
	}

	public String getUsuarionivel() {
		return usuarionivel;
	}

	public void setUsuarionivel(String usuarionivel) {
		this.usuarionivel = usuarionivel;
	}

	public String getUsuariologin() {
		return usuariologin;
	}

	public void setUsuariologin(String usuariologin) {
		this.usuariologin = usuariologin;
	}
	
	
}
