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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.classes.RelacaoDocTraco;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoDocTracoRq;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoDocTracoRs;
import com.grupo2.API_TraceFinder.repository.RelacaoDocTracoRepository;

@RestController
@RequestMapping("/reldoctraco")
public class RelacaoDocTracoController {

  private RelacaoDocTracoRepository relacaoDocTracoRepository = null;

  public RelacaoDocTracoController(RelacaoDocTracoRepository relacaoDocTracoRepository) {
    this.relacaoDocTracoRepository = relacaoDocTracoRepository;
  }

  // SELECT de todos//
  @GetMapping("/")
  public List<RelacaoDocTracoRs> selectAll() {
    var RelDocTraco = relacaoDocTracoRepository.findAll();
    return RelDocTraco.stream().map((relDocTraco) -> RelacaoDocTracoRs.converter(relDocTraco))
        .collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public RelacaoDocTracoRs selectID(@PathVariable("id") Long id) {
    var relDocTraco = relacaoDocTracoRepository.getOne(id);
    return RelacaoDocTracoRs.converter(relDocTraco);
  }

  // SELECT Traços de um documento//
  @GetMapping("/tracosdocumento")
  public List<RelacaoDocTracoRs> selectTracosDoc(@RequestParam(value = "docid", required = false) Long docid) {
    var doc = relacaoDocTracoRepository.SelectTracosDoc(docid);
    return doc.stream().map((TrList) -> RelacaoDocTracoRs.converter(TrList)).collect(Collectors.toList());
  }

  // INSERT //
  @PostMapping("/")
  public void insertRelacaoDocTraco(@RequestBody RelacaoDocTracoRq relDocTraco) {
    var rDocTraco = new RelacaoDocTraco();
    rDocTraco.setDocid(relDocTraco.getDocid());
    rDocTraco.setTracoid(relDocTraco.getTracoid());
    relacaoDocTracoRepository.save(rDocTraco);
  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateRelacaoDocTraco(@PathVariable Long id, @RequestBody RelacaoDocTracoRq relDocTraco)
      throws Exception {
    var rDocTraco = relacaoDocTracoRepository.findById(id);

    if (rDocTraco.isPresent()) {
      var rDocTraco2 = rDocTraco.get();
      rDocTraco2.setDocid(relDocTraco.getDocid());
      rDocTraco2.setTracoid(relDocTraco.getTracoid());
      relacaoDocTracoRepository.save(rDocTraco2);
    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @PostMapping("/delete")
  public void deleteRelacaoCodelistTraco(@RequestBody RelacaoDocTracoRq relBlocoTraco) {
    relacaoDocTracoRepository.DeleteTracosDoc(Long.valueOf(relBlocoTraco.getDocid()),
        Long.valueOf(relBlocoTraco.getTracoid()));

  }

  // DELETE
  @DeleteMapping("/")
  public void deleteRelacaoDocTraco(@RequestBody List<Long> ids) {

    for (Long id : ids) {
      relacaoDocTracoRepository.deleteById(id);
    }
  }

  // SELECT Traços de um documento//
  @GetMapping("/deltracodoc")
  public void deleteTracosDoc(@RequestParam(value = "docid", required = false) Long docid,
      @RequestParam(value = "tracoid", required = false) Long tracoid) {
    relacaoDocTracoRepository.DeleteTracosDoc(docid, tracoid);
  }
}
