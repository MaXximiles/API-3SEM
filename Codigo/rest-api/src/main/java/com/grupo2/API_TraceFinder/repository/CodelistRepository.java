package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.classes.Codelist;

@Repository
public interface CodelistRepository extends JpaRepository<Codelist, Long> {
	
	@Query(value = "SELECT codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_numbloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist WHERE documento_id = ?1 ORDER BY codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco;", nativeQuery = true)
	List<Codelist> SelectCodelistDoc(Long id);
	
	@Query(value = "SELECT codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist "
			+ " INNER JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id"
			+ " WHERE traco_id = ?1 ORDER BY codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco;", nativeQuery = true)
	List<Codelist> SelectTracoCodelist(Long id); 
	
	@Query(value = "SELECT codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist WHERE codelist_id = ?1 ORDER BY codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco;", nativeQuery = true)
	List<Codelist> SelectCodelist(Long id);
			
	//Pesquisa de blocos com o traço
	@Query(value = "SELECT traco_doc_nome, traco_doc_codigo, codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist "
			+ " INNER JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id "
			+ " INNER JOIN traco_doc ON traco_doc.traco_doc_id = relacao_bloco_traco.traco_id "
			+ " WHERE documento_id = ?1" + " AND traco_id = ?2 ORDER BY codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco;", nativeQuery = true)
	List<Codelist> SelectBlocosTraco(Long docId, Long tracoId);
	
	//Pesquisa de blocos do documento com traço
	@Query(value = "SELECT traco_doc_nome, traco_doc_codigo, codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
				+ "	FROM codelist "
				+ " LEFT JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id "
				+ " LEFT JOIN traco_doc ON traco_doc.traco_doc_id = relacao_bloco_traco.traco_id "
				+ " WHERE documento_id = ?1 GROUP BY codelist_id ORDER BY codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco;", nativeQuery = true)
	List<Codelist> SelectBlocosDocTraco(Long docId);
	
	//Pesquisa do bloco com os traços
	@Query(value = "SELECT traco_doc_nome, traco_doc_codigo, codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist "
			+ " INNER JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id "
			+ " INNER JOIN traco_doc ON traco_doc.traco_doc_id = relacao_bloco_traco.traco_id "
			+ " WHERE codelist_id = ?1 ORDER BY codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco;", nativeQuery = true)
	List<Codelist> SelectBlocoTracos(Long id);
	
	
	
	@Query(value = "SELECT codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
			+ "	FROM codelist WHERE codelist_nomebloco LIKE %?1% GROUP BY codelist_numbloco, codelist_nomebloco ORDER BY codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco;", nativeQuery = true)
	List<Codelist> SelectNomeBloco(String bloconome);

}
