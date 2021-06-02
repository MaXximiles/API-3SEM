package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

	 @Query(value = "SELECT tag_nome FROM tag " 
	 		  + " INNER JOIN tag_documento ON tag_documento.tag_id = tag.tag_id "
		      + " WHERE tag_documento.documento_id = ?1 ", nativeQuery = true)
	 List<Tag> selectTagDocumento(Long docid);
	 
	 @Query(value = "SELECT tag_nome FROM tag " 
	 		  + " INNER JOIN tag_bloco ON tag_bloco.tag_id = tag.tag_id "
		      + " WHERE tag_bloco.bloco_id = ?1 ", nativeQuery = true)
	 List<Tag> selectTagBloco(Long blocoid);
	 
	 @Query(value = "SELECT tag_nome FROM tag " 
	 		  + " INNER JOIN tag_traco ON tag_traco.tag_id = tag.tag_id "
		      + " WHERE tag_traco.traco_id = ?1 ", nativeQuery = true)
	 List<Tag> selectTagTraco(Long tracoid);
}



