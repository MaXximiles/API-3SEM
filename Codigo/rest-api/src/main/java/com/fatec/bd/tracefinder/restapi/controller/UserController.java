package com.fatec.bd.tracefinder.restapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fatec.bd.tracefinder.restapi.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostMapping("/validateuser")
	public ResponseEntity<Boolean> validateCredentials(@RequestBody Usuario payload) {
		try {
			String sql = "SELECT usr_id, usr_nome, usr_login, usr_senha, usr_email FROM usuario WHERE usr_email = ? AND usr_senha = ?";
        	List<Usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class), payload.getUsrEmail(), payload.getUsrSenha());

			if (usuarios.size() > 0) {
				return ResponseEntity.ok().body(true);
			} else {
				return ResponseEntity.ok().body(false);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/mock")
	public ResponseEntity<List<Usuario>> mockup() {
		List<Usuario> listUsuario = new ArrayList<>();
		Usuario usuario = new Usuario("Rodrigo", "radtenoradtenoradteno", "AbC0223233243", "rodrigo.tenorio1705@gmail.com");
		usuario.setUsrId(444444444);
		for (int i = 0; i < 100; i++) {
			listUsuario.add(usuario);
		}

		return ResponseEntity.ok().body(listUsuario);
	}

	@PostMapping("/createusuario")
	public ResponseEntity<Map<String, Integer>> insertUsuario(@RequestBody Map<String, String> payload) {
		try {
			String sql = "INSERT INTO usuario (usr_nome, usr_login, usr_senha, usr_email) VALUES (?,?,?,?)";

			Usuario user = new Usuario(payload.get("nome"), payload.get("login"), payload.get("senha"), payload.get("email"));
			jdbcTemplate.update(
				sql,
				user.getUsrNome(), 
				user.getUsrLogin(), 
				user.getUsrSenha(),
				user.getUsrEmail()
			);   
	
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
}
