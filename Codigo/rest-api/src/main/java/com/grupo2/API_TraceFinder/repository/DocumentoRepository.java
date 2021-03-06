package com.grupo2.API_TraceFinder.repository;

import com.grupo2.API_TraceFinder.classes.Documento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

	
	@Query(value = "SELECT documento_id, documento_nome, documento_pn, documento_caminho "
			+ "	FROM documento WHERE documento_id = ?1  ", nativeQuery = true)
	List<Documento> pesquisaDoc(Long id);
	
	
  // Contains traz tudo que contem substituindo o LIKE %% do mysql
  List<Documento> findBydocumentonomeContains(String documentonome);

  
  
  @Query(value = "SELECT documento_id, documento_nome, documento_pn, documento_caminho, documento_cdlistlep FROM documento"
      + " WHERE documento_nome LIKE %?1% AND documento_pn LIKE %?2% ", nativeQuery = true)
  List<Documento> SelectDocumentoLikeNomePn(String docnome, String docpn);

  
  
  @Query(value = "SELECT documento.documento_id, documento_nome, documento_pn, documento_caminho " + "	FROM codelist "
      + " INNER JOIN documento ON documento.documento_id = codelist.documento_id "
      + " WHERE codelist.bloco_id = ?1  ", nativeQuery = true)
  List<Documento> SelectDocBloco(Long id);
  
  
  
  @Query(value = "SELECT documento.documento_id, documento_nome, documento_pn, documento_caminho "
		  + " FROM documento "
	      + " INNER JOIN relacao_documento_traco ON relacao_documento_traco.doc_id = documento.documento_id "
	      + " WHERE relacao_documento_traco.traco_id = ?1  ", nativeQuery = true)
	  List<Documento> SelectDocTraco(Long id);
  
  

}