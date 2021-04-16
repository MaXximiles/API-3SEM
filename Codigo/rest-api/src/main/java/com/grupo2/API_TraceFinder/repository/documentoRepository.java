package com.grupo2.API_TraceFinder.repository;

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
	
	@Query(value = "SELECT  bloco.bloco_id, bloco_secao, bloco_subsecao, bloco_nomebloco, bloco_codebloco, bloco_caminho, "
			+ "	documento.documento_id, documento_nome, documento_pn, documento_caminho FROM documento "
			+ " INNER JOIN codelist ON codelist.documento_id = documento.documento_id "
			+ " INNER JOIN bloco ON codelist.bloco_id = bloco.bloco_id "
			+ " WHERE documento.documento_id = ?1  ", nativeQuery = true)
	List<Documento> SelectJoinBloco(Long id);
	
	@Query(value = "SELECT documento.documento_id, documento_nome, documento_pn, documento_caminho "
			+ "	FROM codelist "
			+ " INNER JOIN documento ON documento.documento_id = codelist.documento_id "
			+ " WHERE codelist.bloco_id = ?1  ", nativeQuery = true)
	List<Documento> SelectDocBloco(Long id);

}
