package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.classes.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	// Contains traz tudo que contem substituindo o LIKE %% do mysql
		List<Usuario> findByusuarionomeContains(String usuarionome);
		
		List<Usuario> findByusuariologinContains(String usuariologin);
}
