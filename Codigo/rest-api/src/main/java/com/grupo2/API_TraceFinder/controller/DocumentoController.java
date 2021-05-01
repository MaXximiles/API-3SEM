package com.grupo2.API_TraceFinder.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRq;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRq;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRs;
import com.grupo2.API_TraceFinder.repository.DocumentoCustomRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoRepository;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
  private DocumentoRepository documentoRepository = null;
  private DocumentoCustomRepository documentoCustomRepository = null;

  public DocumentoController(DocumentoRepository documentoRepository,
      DocumentoCustomRepository documentoCustomRepository) {
    this.documentoRepository = documentoRepository;
    this.documentoCustomRepository = documentoCustomRepository;
  }
  
  // Definindo raiz para criação dos diretórios dos manuais 
  private String raiz = "C:\\trace_finder\\";

  // SELECT de todos//
  @GetMapping("/")
  public List<DocumentoRs> selectAll() {
    var documentos = documentoRepository.findAll();
    return documentos.stream().map((doc) -> DocumentoRs.converter(doc)).collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public DocumentoRs selectID(@PathVariable("id") Long id) {
    var doc = documentoRepository.getOne(id);
    return DocumentoRs.converter(doc);
  }

  // SELECT por Nome
  @GetMapping("/filtronome")
  public List<DocumentoRs> findDocumentoBydocumentonome(@RequestParam("documentonome") String documentonome) {
    return this.documentoRepository.findBydocumentonomeContains(documentonome).stream().map(DocumentoRs::converter)
        .collect(Collectors.toList());
  }

  // SELECT CUSTOM por Nome e PN
  @GetMapping("/filtronomepn")
  public List<DocumentoRs> findDocumentoBydocumentonomepn(
      @RequestParam(value = "documentoid", required = false) Long documentoid,
      @RequestParam(value = "documentonome", required = false) String documentonome,
      @RequestParam(value = "documentopn", required = false) String documentopn,
      @RequestParam(value = "documentocaminho", required = false) String documentocaminho) {
    return this.documentoCustomRepository.find(documentoid, documentonome, documentopn, documentocaminho).stream()
        .map(DocumentoRs::converter).collect(Collectors.toList());
  }

  // SELECT com Query//
  @GetMapping("/likenomepn")
  public List<DocumentoRs> SelectLike(@RequestParam(value = "docnome", required = false) String docnome,
      @RequestParam(value = "docpn", required = false) String docpn) {
    var doc = documentoRepository.SelectDocumentoLikeNomePn(docnome, docpn);
    return doc.stream().map((BlList) -> DocumentoRs.converter(BlList)).collect(Collectors.toList());
  }

  // SELECT Documentos que contem aquele bloco//
  @GetMapping("/documentobloco")
  public List<DocumentoRs> selectDocBloco(@RequestParam(value = "blocoid", required = false) Long blocoid) {
    var documento = documentoRepository.SelectDocBloco(blocoid);
    return documento.stream().map((BlList) -> DocumentoRs.converter(BlList)).collect(Collectors.toList());
  }

  // INSERT //
  @PostMapping("/")
  public void insertDocumento(@RequestBody DocumentoRq documento) 
  {
    var doc = new Documento();
    doc.setDocumentonome(documento.getDocumentonome());
    doc.setDocumentopn(documento.getDocumentopn());
    doc.setDocumentocaminho(raiz);
    documentoRepository.save(doc);
    
    String pasta = documento.getDocumentonome() + "-" + documento.getDocumentopn();  
    File newDir = new File(raiz + pasta);
    newDir.mkdir();
  }
  
  // UPDATE
  @PutMapping("/{id}")
  public void updateDocumento(@PathVariable Long id, @RequestBody DocumentoRq documento) throws Exception {
    var doc = documentoRepository.findById(id);

    if (doc.isPresent()) {
      var doc2 = doc.get();
      doc2.setDocumentonome(documento.getDocumentonome());
      doc2.setDocumentopn(documento.getDocumentopn());
      doc2.setDocumentocaminho(documento.getDocumentocaminho());
      documentoRepository.save(doc2);
    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @DeleteMapping("/{id}")
  public void deleteDocumento(@PathVariable Long id)
  {
	  var doc = documentoRepository.getOne(id);	
	  String docNome = doc.getDocumentonome();
	  String docPn = doc.getDocumentopn();
	  String docCaminho = doc.getDocumentocaminho();
	   
	  String pasta = docCaminho + "\\" + docNome + "-" + docPn;
	  File folder = new File(pasta);
	  if (folder.isDirectory()) 
	  {
	  	File[] sun = folder.listFiles();
	  	for (File toDelete : sun){toDelete.delete();}
	  	folder.delete();
	  }
	  
	  documentoRepository.deleteById(id);
  }
  
  
    
}
