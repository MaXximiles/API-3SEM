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
import com.grupo2.API_TraceFinder.classes.TagDocumento;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRq;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRs;
import com.grupo2.API_TraceFinder.controller.dto.TagDocumentoRq;
import com.grupo2.API_TraceFinder.controller.dto.TagDocumentoRs;
import com.grupo2.API_TraceFinder.repository.TagDocumentoRepository;

@RestController
@RequestMapping("/tagdocumento")
public class TagDocumentoController {

  private TagDocumentoRepository tagDocumentoRepository = null;

  public TagDocumentoController(TagDocumentoRepository TagDocumentoRepository) {

    this.tagDocumentoRepository = TagDocumentoRepository;

  }

  // SELECT de todos//
  @GetMapping("/")
  public List<TagDocumentoRs> selectAll() {
    var tagDocumento = tagDocumentoRepository.findAll();
    return tagDocumento.stream().map((tag) -> TagDocumentoRs.converter(tag)).collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public TagDocumentoRs selectID(@PathVariable("id") Long id) {
    var tagDocumento = tagDocumentoRepository.getOne(id);
    return TagDocumentoRs.converter(tagDocumento);
  }

  // INSERT //
  @PostMapping("/")
  public void insertTagDocumento(@RequestBody TagDocumentoRq tagDocumento) {
    var tag = new TagDocumento();
    tag.setDocumentoId(tagDocumento.getDocumentoId());
    tag.setTagId(tagDocumento.getTagId());
    tagDocumentoRepository.save(tag);
  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateTagDocumento(@PathVariable Long id, @RequestBody TagDocumentoRq tagDocumento) throws Exception {
    var tag = tagDocumentoRepository.findById(id);

    if (tag.isPresent()) {
      var tag2 = tag.get();
      tag2.setDocumentoId(tagDocumento.getDocumentoId());
      tag2.setTagId(tagDocumento.getTagId());
      tagDocumentoRepository.save(tag2);

    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @PostMapping("/delete")
  public void deleteTagDocument(@RequestBody TagDocumentoRq tagDocumento) {
    tagDocumentoRepository.DeleteTracosDoc(tagDocumento.getDocumentoId(), tagDocumento.getTagId());
  }

  // DELETE
  @DeleteMapping("/{tagdocumentoid}")
  public void deleteTagDocumento(@PathVariable Long tagDocumentoid) {
    tagDocumentoRepository.deleteById(tagDocumentoid);
  }

}
