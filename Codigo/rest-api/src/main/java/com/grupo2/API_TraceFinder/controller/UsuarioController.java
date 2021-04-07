package com.grupo2.API_TraceFinder.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.classes.Usuario;
import com.grupo2.API_TraceFinder.controller.dto.UsuarioRq;
import com.grupo2.API_TraceFinder.controller.dto.UsuarioRs;
import com.grupo2.API_TraceFinder.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository = null;
	
	public UsuarioController(UsuarioRepository UsuarioRepository) {	this.usuarioRepository = UsuarioRepository;	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<UsuarioRs> selectAll()
	{
		var usuarios = usuarioRepository.findAll();
		return usuarios.stream().map((user) -> UsuarioRs.converter(user)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public UsuarioRs selectID(@PathVariable("id") Long id)
	{
		var user = usuarioRepository.getOne(id);	
		return UsuarioRs.converter(user);
	}
	
	// INSERT //
	@PostMapping("/")
	public void insertUsuario(@RequestBody UsuarioRq usuario)
	{
		var user = new Usuario();
		
		user.setUsuario_nome(usuario.getUsuario_nome());
		user.setUsuario_login(usuario.getUsuario_login());
		user.setUsuario_senha(usuario.getUsuario_senha());
		user.setUsuario_email(usuario.getUsuario_email());
		user.setUsuario_nivel(usuario.getUsuario_nivel());
		usuarioRepository.save(user);
		
	}
		
	// UPDATE
	@PutMapping("/{id}")
	public void updateUsuario(@PathVariable Long id, @RequestBody UsuarioRq usuario) throws Exception
	{
		var user = usuarioRepository.findById(id);
		
		if(user.isPresent())
		{
			var user2 = user.get();
			
			user2.setUsuario_nome(usuario.getUsuario_nome());
			user2.setUsuario_login(usuario.getUsuario_login());
			user2.setUsuario_senha(usuario.getUsuario_senha());
			user2.setUsuario_email(usuario.getUsuario_email());
			user2.setUsuario_nivel(usuario.getUsuario_nivel());
			usuarioRepository.save(user2);
			
		}
		else { throw new Exception("Documento não encontrado"); }
	}
		
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable Long id)
	{	
		usuarioRepository.deleteById(id);
	}
	
}
