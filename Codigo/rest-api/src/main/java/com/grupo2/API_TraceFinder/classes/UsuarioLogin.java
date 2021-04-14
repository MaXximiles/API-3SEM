package com.grupo2.API_TraceFinder.classes;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
public class UsuarioLogin implements UserDetails{

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
	
	@Id
	@Column(name="usuario_login")
	private String usuariologin;

	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.usuariosenha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.usuariologin;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
