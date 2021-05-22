package com.grupo2.API_TraceFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.classes.RelacaoBlocoTraco;

@Repository
public interface RelacaoBlocoTracoRepository extends JpaRepository<RelacaoBlocoTraco, Long>{
	
}
