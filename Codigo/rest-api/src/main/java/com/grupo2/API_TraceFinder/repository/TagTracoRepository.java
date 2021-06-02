package com.grupo2.API_TraceFinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import com.grupo2.API_TraceFinder.classes.TagTraco;

@Repository
public interface TagTracoRepository extends JpaRepository<TagTraco, Long> {
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM tag_traco WHERE traco_id = ?1 AND tag_id = ?2 ", nativeQuery = true)
  void DeleteTracosDoc(Long tracoid, Long tagid);
}
