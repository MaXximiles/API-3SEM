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
	private Long usuarioid;
	
	@Column(name="usuario_nome")
	private String usuarionome;
	
	@Column(name="usuario_email")
	private String usuarioemail;

	@Column(name="usuario_senha")
	private String usuariosenha;
	
	@Column(name="usuario_nivel")
	private String usuarionivel;
	
	@Column(name="usuario_login")
	private String usuariologin;

	
	public Long getUsuarioid() {return usuarioid;}

	public void setUsuarioid(Long usuarioid) {this.usuarioid = usuarioid;}

	public String getUsuarionome() {return usuarionome;}

	public void setUsuarionome(String usuarionome) {this.usuarionome = usuarionome;}

	public String getUsuarioemail() { return usuarioemail;}

	public void setUsuarioemail(String usuarioemail) { this.usuarioemail = usuarioemail; }

	public String getUsuariosenha() { return usuariosenha; }

	public void setUsuariosenha(String usuariosenha) {this.usuariosenha = usuariosenha;}

	public String getUsuarionivel() { return usuarionivel;}

	public void setUsuarionivel(String usuarionivel) { this.usuarionivel = usuarionivel;}

	public String getUsuariologin() { return usuariologin;}

	public void setUsuariologin(String usuariologin) { this.usuariologin = usuariologin;}
	
	

	

}
