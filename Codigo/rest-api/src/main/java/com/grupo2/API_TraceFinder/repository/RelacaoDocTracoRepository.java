package com.grupo2.API_TraceFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo2.API_TraceFinder.classes.RelacaoDocTraco;

@Repository
public interface RelacaoDocTracoRepository extends JpaRepository<RelacaoDocTraco, Long>{

}