package com.grupo2.API_TraceFinder.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRq;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRs;
import com.grupo2.API_TraceFinder.repository.ArquivoRepository;
import com.grupo2.API_TraceFinder.repository.ArquivoUpload;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

  private ArquivoRepository arquivoRepository = null;
  private ArquivoUpload arquivoUpload = null;
  private CodelistRepository codelistRepository = null;

  public ArquivoController(ArquivoRepository ArquivoRepository, ArquivoUpload ArquivoUpload,
      CodelistRepository CodelistRepository) {
    this.arquivoRepository = ArquivoRepository;
    this.arquivoUpload = ArquivoUpload;
    this.codelistRepository = CodelistRepository;
  }

  // SELECT de todos//
  @GetMapping("/")
  public List<ArquivoRs> selectAll() {
    var arquivos = arquivoRepository.findAll();
    return arquivos.stream().map((arq) -> ArquivoRs.converter(arq)).collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public ArquivoRs selectID(@PathVariable("id") Long id) {
    var doc = arquivoRepository.getOne(id);
    return ArquivoRs.converter(doc);
  }
  

  // INSERT //
  @PostMapping("/")
  public void insertArquivo(@RequestBody ArquivoRq arquivo, Long id, String nome)
  {
    var arq = new Arquivo();
    arq.setArquivonome(nome);
    arq.setCodelistid(id);
    arquivoRepository.save(arq);
  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateArquivo(@PathVariable Long id, @RequestBody ArquivoRq arquivo) throws Exception
  {
    var arq = arquivoRepository.findById(id);

    if (arq.isPresent()) 
    {
      var arq2 = arq.get();
      arq2.setArquivonome(arquivo.getArquivonome());
      arq2.setCodelistid(arquivo.getCodelistid());
      arquivoRepository.save(arq2);

    } else {
      throw new Exception("Arquivo não encontrado");
    }
  }

  // DELETE
  @DeleteMapping("/{arqid}")
  public void deleteArquivo(@PathVariable Long arqid) {
    var arq = arquivoRepository.getOne(arqid);
    Long codelist = arq.getCodelistid();
    String arqNome = arq.getArquivonome();

    var bloco = codelistRepository.getOne(codelist);
    String blocoNome = bloco.getCodelistnomebloco();
    String blocoCaminho = bloco.getCodelistcaminho();
    String pasta = blocoCaminho + "\\" + blocoNome;

    String caminho = pasta + "\\" + arqNome;

    File file = new File(caminho);
    file.delete();

    arquivoRepository.deleteById(arqid);
  }

  // Upload do arquivo necessário id do codelist
  @PostMapping("/upload/{id}")
  public void upload(@RequestBody MultipartFile arquivo, @PathVariable Long id) throws Exception 
  {
	  	  
	  arquivoUpload.salvarArquivo(arquivo, id);
	  // String arqNome = arquivo.getOriginalFilename();

  }
}
