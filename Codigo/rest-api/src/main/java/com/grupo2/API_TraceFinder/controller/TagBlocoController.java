package com.grupo2.API_TraceFinder.controller;

import java.io.File;
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

import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.classes.TagBloco;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRq;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRs;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRq;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRs;
import com.grupo2.API_TraceFinder.repository.TagBlocoRepository;

@RestController
@RequestMapping("/tagbloco")
public class TagBlocoController {

  private TagBlocoRepository tagBlocoRepository = null;

  public TagBlocoController(TagBlocoRepository TagBlocoRepository) {

    this.tagBlocoRepository = TagBlocoRepository;

  }

  // SELECT de todos//
  @GetMapping("/")
  public List<TagBlocoRs> selectAll() {
    var tagBloco = tagBlocoRepository.findAll();
    return tagBloco.stream().map((tag) -> TagBlocoRs.converter(tag)).collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public TagBlocoRs selectID(@PathVariable("id") Long id) {
    var tagBloco = tagBlocoRepository.getOne(id);
    return TagBlocoRs.converter(tagBloco);
  }

  // INSERT //
  @PostMapping("/")
  public void insertTagBloco(@RequestBody TagBlocoRq tagBloco) {
    var tag = new TagBloco();
    tag.setBlocoId(tagBloco.getBlocoId());
    tag.setTagId(tagBloco.getTagId());
    tagBlocoRepository.save(tag);
  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateTagBloco(@PathVariable Long id, @RequestBody TagBlocoRq tagBloco) throws Exception {
    var tag = tagBlocoRepository.findById(id);

    if (tag.isPresent()) {
      var tag2 = tag.get();
      tag2.setBlocoId(tagBloco.getBlocoId());
      tag2.setTagId(tagBloco.getTagId());
      tagBlocoRepository.save(tag2);

    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @PostMapping("/delete")
  public void deleteTagDocument(@RequestBody TagBlocoRq tagDocumento) {
    tagBlocoRepository.DeleteTracosDoc(tagDocumento.getBlocoId(), tagDocumento.getTagId());
  }

  // DELETE
  @DeleteMapping("/{tagblocoid}")
  public void deleteTagBloco(@PathVariable Long tagBlocoid) {
    tagBlocoRepository.deleteById(tagBlocoid);
  }

}
