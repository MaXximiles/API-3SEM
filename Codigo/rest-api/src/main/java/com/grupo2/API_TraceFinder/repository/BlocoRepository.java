package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.grupo2.API_TraceFinder.classes.Bloco;
import com.grupo2.API_TraceFinder.controller.dto.BlocoRs;


@Repository
public interface BlocoRepository extends JpaRepository<Bloco, Long> {
	
	@Query(value = "SELECT * FROM bloco B WHERE B.bloco_id = ?1", nativeQuery = true)
	List<BlocoRs> SelectAll(Long id);
}
