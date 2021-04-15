package com.grupo2.API_TraceFinder.repository;

import com.grupo2.API_TraceFinder.classes.Documento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

  // Contains traz tudo que contem substituindo o LIKE %% do mysql
  List<Documento> findBydocumentonomeContains(String documentonome);

}
