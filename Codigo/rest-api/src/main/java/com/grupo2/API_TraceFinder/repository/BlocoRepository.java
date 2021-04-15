package com.grupo2.API_TraceFinder.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.grupo2.API_TraceFinder.classes.Bloco;
import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.controller.dto.BlocoRs;


@Repository
public interface BlocoRepository extends JpaRepository<Bloco, Long> {
	
	@Query(value = "SELECT  bloco.bloco_id, bloco_secao, bloco_subsecao, bloco_nomebloco, bloco_codebloco, bloco_caminho, "
			+ "	documento.documento_id documento_nome, documento_pn, documento_caminho FROM bloco "
			+ " INNER JOIN codelist ON codelist.bloco_id = bloco.bloco_id "
			+ " INNER JOIN documento ON codelist.documento_id = documento.documento_id "
			+ " WHERE bloco.bloco_id = ?1  ", nativeQuery = true)
	List<Bloco> SelectAll(Long id);
	
}
