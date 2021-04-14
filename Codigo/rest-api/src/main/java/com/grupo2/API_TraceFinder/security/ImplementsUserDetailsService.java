package com.grupo2.API_TraceFinder.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grupo2.API_TraceFinder.classes.UsuarioLogin;
import com.grupo2.API_TraceFinder.repository.UsuarioLoginRepository;

@Service
public class ImplementsUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioLoginRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String usuario_login) throws UsernameNotFoundException {
		Optional<UsuarioLogin> usuario = ur.findById(usuario_login);
		
		if(usuario == null)
		{
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		return usuario.get();
	}
	
	

}
