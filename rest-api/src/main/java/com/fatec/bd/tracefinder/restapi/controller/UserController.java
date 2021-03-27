package com.fatec.bd.tracefinder.restapi.controller;

import java.util.List;
import java.util.Map;

import com.fatec.bd.tracefinder.restapi.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostMapping("/validateuser")
	public ResponseEntity<Boolean> validateCredentials(@RequestBody Map<String, Usuario> payload) {
		try {
			String sql = "SELECT usr_id, usr_nome, usr_login, usr_senha, usr_email FROM usuario WHERE usr_email = ? AND usr_senha = ?";
        	List<Usuario> usuarios = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class), payload.get("data").getUsrEmail(), payload.get("data").getUsrSenha());

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

	@CrossOrigin
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
