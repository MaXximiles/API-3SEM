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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.classes.Usuario;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRs;
import com.grupo2.API_TraceFinder.controller.dto.UsuarioRq;
import com.grupo2.API_TraceFinder.controller.dto.UsuarioRs;
import com.grupo2.API_TraceFinder.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
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
	
	// SELECT por Nome
	@GetMapping("/filtronome")
	public List<UsuarioRs> findUsuarioByusuarionome(@RequestParam("usuarionome") String usuarionome)
	{
		return this.usuarioRepository.findByusuarionomeContains(usuarionome)
			.stream()
			.map(UsuarioRs::converter)
			.collect(Collectors.toList());
	}
	
	// SELECT por Nome
	@GetMapping("/filtrologin")
	public List<UsuarioRs> findUsuarioByusuariologin(@RequestParam("usuariologin") String usuariologin)
	{
		return this.usuarioRepository.findByusuariologinContains(usuariologin)
			.stream()
			.map(UsuarioRs::converter)
			.collect(Collectors.toList());
	}
	
	// INSERT //
	@PostMapping("/")
	public void insertUsuario(@RequestBody UsuarioRq usuario)
	{
		var user = new Usuario();
		
		user.setUsuarionome(usuario.getUsuarionome());
		user.setUsuariologin(usuario.getUsuariologin());
		user.setUsuariosenha(usuario.getUsuariosenha());
		user.setUsuarioemail(usuario.getUsuarioemail());
		user.setUsuarionivel(usuario.getUsuarionivel());
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
			
			user2.setUsuarionome(usuario.getUsuarionome());
			user2.setUsuariologin(usuario.getUsuariologin());
			user2.setUsuariosenha(usuario.getUsuariosenha());
			user2.setUsuarioemail(usuario.getUsuarioemail());
			user2.setUsuarionivel(usuario.getUsuarionivel());
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
