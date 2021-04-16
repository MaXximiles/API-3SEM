package com.grupo2.API_TraceFinder.repository;

import com.grupo2.API_TraceFinder.classes.Bloco;
import com.grupo2.API_TraceFinder.classes.Documento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	
	// Contains traz tudo que contem substituindo o LIKE %% do mysql
	List<Documento> findBydocumentonomeContains(String documentonome);
	
	@Query(value = "SELECT documento_id, documento_nome, documento_pn, documento_caminho FROM documento"
					+ " WHERE documento_nome LIKE %?1% AND documento_pn LIKE %?2% ", nativeQuery = true)
	List<Documento> SelectDocumentoLikeNomePn(String docnome, String docpn);

}