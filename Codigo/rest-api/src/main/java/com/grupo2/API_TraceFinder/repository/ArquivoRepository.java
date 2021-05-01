package com.grupo2.API_TraceFinder.repository;

import com.grupo2.API_TraceFinder.classes.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long>
{
	
}
