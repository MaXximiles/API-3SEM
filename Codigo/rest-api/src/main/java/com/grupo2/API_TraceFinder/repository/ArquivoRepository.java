package com.grupo2.API_TraceFinder.repository;

import com.grupo2.API_TraceFinder.classes.Arquivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long>
{
	//Pesquisa de revisões do documento
	@Query(value = "SELECT arquivo_revisao"
			+ "	FROM arquivo "
			+ " INNER JOIN codelist ON codelist.codelist_id = arquivo.codelist_id "
			+ " INNER JOIN documento ON documento.documento_id = codelist.documento_id "
			+ " WHERE documento.documento_id = ?1 GROUP BY arquivo_revisao ORDER BY arquivo_revisao;", nativeQuery = true)
	List selectRevisoes(Long docid);
	
	/*//Pesquisa da maior revisão do documento
	@Query(value = "SELECT MAX(arquivo_revisao) "
			+ "	FROM arquivo "
			+ " INNER JOIN codelist ON codelist.codelist_id = arquivo.codelist_id "
			+ " INNER JOIN documento ON documento.documento_id = codelist.documento_id "
			+ " WHERE documento.documento_id = ?1 ;", nativeQuery = true)
	String selectRevisoes(Long docid);*/
}
