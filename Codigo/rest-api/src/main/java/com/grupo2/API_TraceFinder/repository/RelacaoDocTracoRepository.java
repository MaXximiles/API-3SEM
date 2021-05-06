package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.RelacaoDocTraco;

@Repository
public interface RelacaoDocTracoRepository extends JpaRepository<RelacaoDocTraco, Long>{

	@Query(value = "SELECT relacao_documento_traco_id, traco_id, doc_id, traco_doc_nome, traco_doc_descricao, traco_doc_codigo "
			+ "	FROM  relacao_documento_traco "
			+ " INNER JOIN traco_doc ON traco_doc.traco_doc_id = relacao_documento_traco.traco_id "
			+ " WHERE doc_id = ?1  ", nativeQuery = true)
	List<RelacaoDocTraco> SelectTracosDoc(Long id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM relacao_documento_traco WHERE doc_id = ?1 AND traco_id = ?2 ", nativeQuery = true)
	void DeleteTracosDoc(Long docid, Long tracoid);
	
}
