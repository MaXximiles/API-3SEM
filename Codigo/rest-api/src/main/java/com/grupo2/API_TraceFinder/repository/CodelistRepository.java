package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.classes.Documento;


@Repository
public interface CodelistRepository extends JpaRepository<Codelist, Long> {
	
	@Query(value = "SELECT codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist WHERE documento_id = ?1  ", nativeQuery = true)
	List<Codelist> SelectCodelistDoc(Long id);
	
	@Query(value = "SELECT codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist "
			+ " INNER JOIN relacao_traco_bloco ON relacao_traco_bloco.bloco_id = codelist.codelist_id"
			+ " WHERE traco_id = ?1  ", nativeQuery = true)
	List<Codelist> SelectTracoCodelist(Long id);
	
	@Query(value = "SELECT codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist WHERE codelist_id = ?1  ", nativeQuery = true)
	List<Codelist> SelectCodelist(Long id);
	
}
