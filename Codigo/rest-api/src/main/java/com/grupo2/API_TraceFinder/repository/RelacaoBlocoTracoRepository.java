package com.grupo2.API_TraceFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.classes.RelacaoBlocoTraco;

@Repository
public interface RelacaoBlocoTracoRepository extends JpaRepository<RelacaoBlocoTraco, Long> {
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM relacao_bloco_traco WHERE bloco_id = ?1 AND traco_id = ?2 ", nativeQuery = true)
  void DeleteTracosDoc(Long blocoid, Long tracoid);
  
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM relacao_bloco_traco WHERE bloco_id = ?1", nativeQuery = true)
  void deleteRelacaoBlocoTraco(Long blocoid);
  
	@Query(value = "SELECT traco_id "
			+ "	FROM relacao_bloco_traco WHERE bloco_id = ?1 GROUP BY traco_id ", nativeQuery = true)
	String SelectTraco(Long blocoId);
  
}
