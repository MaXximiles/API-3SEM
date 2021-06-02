package com.grupo2.API_TraceFinder.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.classes.TagBloco;
import com.grupo2.API_TraceFinder.classes.TagTraco;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRq;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRs;
import com.grupo2.API_TraceFinder.controller.dto.TagTracoRq;
import com.grupo2.API_TraceFinder.controller.dto.TagTracoRs;
import com.grupo2.API_TraceFinder.repository.TagTracoRepository;

@RestController
@RequestMapping("/tagtraco")
public class TagTracoController {

  private TagTracoRepository tagTracoRepository = null;

  public TagTracoController(TagTracoRepository TagTracoRepository) {

    this.tagTracoRepository = TagTracoRepository;

  }

  // SELECT de todos//
  @GetMapping("/")
  public List<TagTracoRs> selectAll() {
    var tagTraco = tagTracoRepository.findAll();
    return tagTraco.stream().map((tag) -> TagTracoRs.converter(tag)).collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public TagTracoRs selectID(@PathVariable("id") Long id) {
    var tagTraco = tagTracoRepository.getOne(id);
    return TagTracoRs.converter(tagTraco);
  }

  // INSERT //
  @PostMapping("/")
  public void insertTagTraco(@RequestBody TagTracoRq tagTraco) {
    var tag = new TagTraco();
    tag.setTracoId(tagTraco.getTracoId());
    tag.setTagId(tagTraco.getTagId());
    tagTracoRepository.save(tag);
  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateTagTraco(@PathVariable Long id, @RequestBody TagTracoRq tagTraco) throws Exception {
    var tag = tagTracoRepository.findById(id);

    if (tag.isPresent()) {
      var tag2 = tag.get();
      tag2.setTracoId(tagTraco.getTracoId());
      tag2.setTracoId(tagTraco.getTagId());
      tagTracoRepository.save(tag2);

    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @PostMapping("/delete")
  public void deleteTagDocument(@RequestBody TagTracoRq tagDocumento) {
    tagTracoRepository.DeleteTracosDoc(tagDocumento.getTracoId(), tagDocumento.getTagId());
  }

  // DELETE
  @DeleteMapping("/{tagtracoid}")
  public void deleteTagTraco(@PathVariable Long tagTracoid) {
    tagTracoRepository.deleteById(tagTracoid);
  }

}
