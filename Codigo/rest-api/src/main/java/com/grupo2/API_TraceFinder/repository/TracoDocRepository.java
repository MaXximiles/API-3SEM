package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.TracoDoc;

@Repository
public interface TracoDocRepository extends JpaRepository<TracoDoc, Long>{

	
	@Query(value = "SELECT traco_doc_id, traco_doc_nome, traco_doc_descricao, traco_doc_codigo "
			+ "	FROM traco_doc "
			+ " INNER JOIN relacao_documento_traco ON relacao_documento_traco.traco_id = traco_doc.traco_doc_id "
			+ " WHERE doc_id = ?1  ", nativeQuery = true)
	List<TracoDoc> SelectTracoDocumento(Long docid);
	
}
