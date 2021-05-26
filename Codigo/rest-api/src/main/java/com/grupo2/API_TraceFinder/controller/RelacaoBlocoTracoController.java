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

import com.grupo2.API_TraceFinder.classes.RelacaoBlocoTraco;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoBlocoTracoRq;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoBlocoTracoRs;
import com.grupo2.API_TraceFinder.repository.RelacaoBlocoTracoRepository;

@RestController
@RequestMapping("/relacao_bloco_traco")
public class RelacaoBlocoTracoController {

  private RelacaoBlocoTracoRepository relacaoBlocoTracoRepository = null;

  public RelacaoBlocoTracoController(RelacaoBlocoTracoRepository relacaoArqTracoRepository) {
    this.relacaoBlocoTracoRepository = relacaoArqTracoRepository;
  }

  // SELECT de todos//
  @GetMapping("/")
  public List<RelacaoBlocoTracoRs> selectAll() {
    var RelArqTraco = relacaoBlocoTracoRepository.findAll();
    return RelArqTraco.stream().map((relArqTraco) -> RelacaoBlocoTracoRs.converter(relArqTraco))
        .collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public RelacaoBlocoTracoRs selectID(@PathVariable("id") Long id) {
    var relArqTraco = relacaoBlocoTracoRepository.getOne(id);
    return RelacaoBlocoTracoRs.converter(relArqTraco);
  }

  // INSERT //
  @PostMapping("/")
  public void insertRelacaoBlocoTraco(@RequestBody RelacaoBlocoTracoRq relBlocoTraco) {
    if (relBlocoTraco.getBlocoid() != null && relBlocoTraco.getTracoid() != null) {
      var rBlocoTraco = new RelacaoBlocoTraco();
      rBlocoTraco.setBlocoid(relBlocoTraco.getBlocoid());
      rBlocoTraco.setTracoid(relBlocoTraco.getTracoid());
      relacaoBlocoTracoRepository.save(rBlocoTraco);
    }
  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateRelacaoBlocoTraco(@PathVariable Long id, @RequestBody RelacaoBlocoTracoRq relBlocoTraco)
      throws Exception {
    var rBlocoTraco = relacaoBlocoTracoRepository.findById(id);

    if (rBlocoTraco.isPresent()) {
      var rBlocoTraco2 = rBlocoTraco.get();
      rBlocoTraco2.setBlocoid(relBlocoTraco.getBlocoid());
      rBlocoTraco2.setTracoid(relBlocoTraco.getTracoid());
      relacaoBlocoTracoRepository.save(rBlocoTraco2);
    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @PostMapping("/delete")
  public void deleteRelacaoDocTraco(@RequestBody RelacaoBlocoTraco relDocTraco) {
    relacaoBlocoTracoRepository.DeleteTracosBloco(Long.parseLong(relDocTraco.getBlocoid()),
        Long.parseLong(relDocTraco.getTracoid()));
  }

  // DELETE
  @DeleteMapping("/{id}")
  public void deleteRelacaoArqTraco(@PathVariable Long id) {
    relacaoBlocoTracoRepository.deleteById(id);
  }

}
