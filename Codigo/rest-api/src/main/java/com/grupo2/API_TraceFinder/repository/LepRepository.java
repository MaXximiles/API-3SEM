package com.grupo2.API_TraceFinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.classes.Lep;
import com.grupo2.API_TraceFinder.controller.dto.LepRs;

@Repository
public interface LepRepository extends JpaRepository<Lep, Long>{

	 @Query(value = "SELECT lep_id, lep_bloco, lep_code, lep_pagina, lep_modificacao, lep_revisao, lep.arquivo_id, documento_id" 
	 		  + " FROM lep "
	 		  + " INNER JOIN arquivo ON arquivo.arquivo_id = lep.arquivo_id "
		      + " WHERE documento_id = ?1 ", nativeQuery = true)
	 List<Lep> SelectLepArquivo(Long arqid);
	
	 @Query(value = "SELECT COUNT(documento_id) "
	 				+ "FROM lep WHERE documento_id = ?1 ", nativeQuery = true)
	 List<Lep> SelectCountLepArquivo(Long arqid);
	

}
