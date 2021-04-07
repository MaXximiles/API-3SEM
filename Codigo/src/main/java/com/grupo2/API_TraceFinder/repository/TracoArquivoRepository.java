package com.grupo2.API_TraceFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo2.API_TraceFinder.classes.TracoArquivo;

@Repository
public interface TracoArquivoRepository extends JpaRepository<TracoArquivo, Long>{

}
