package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.classes.Lep;
import com.grupo2.API_TraceFinder.classes.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
		// Contains traz tudo que contem substituindo o LIKE %% do mysql
		List<Usuario> findByusuarionomeContains(String usuarionome);
		
		List<Usuario> findByusuariologinContains(String usuariologin);
		
		@Query(value = "SELECT  usuario_id, usuario_senha, usuario_email, usuario_nome, usuario_nivel,  usuario_login "
 				+ "FROM usuario WHERE usuario_email = ?1 ", nativeQuery = true)
		List<Usuario> SelectUsuarioEmail(String UserEmail);
		
}
