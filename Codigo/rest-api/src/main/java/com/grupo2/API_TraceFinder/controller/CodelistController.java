package com.grupo2.API_TraceFinder.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.grupo2.API_TraceFinder.DBConexao;
import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.classes.RelacaoBlocoTraco;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRq;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRs;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRq;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoRepository;
import com.grupo2.API_TraceFinder.repository.RelacaoBlocoTracoRepository;
import com.grupo2.API_TraceFinder.repository.TracoDocRepository;

@RestController
@RequestMapping("/codelist")
public class CodelistController {

  private CodelistRepository codelistRepository = null;
  private DocumentoRepository documentoRepository;
  private RelacaoBlocoTracoRepository relacaoBlocoTracoRepository;
  private TracoDocRepository tracoDocRepository;

  public CodelistController(CodelistRepository codelistRepository, DocumentoRepository documentoRepository,
      RelacaoBlocoTracoRepository relacaoBlocoTracoRepository, TracoDocRepository tracoDocRepository) {
    this.codelistRepository = codelistRepository;
    this.documentoRepository = documentoRepository;
    this.relacaoBlocoTracoRepository = relacaoBlocoTracoRepository;
    this.tracoDocRepository = tracoDocRepository;
  }

  // Definindo raiz para criação dos diretórios dos manuais
  private String raiz = "C:\\trace_finder\\";

  // SELECT de todos//
  @GetMapping("/")
  public List<CodelistRs> selectAll() {
    var codelist = codelistRepository.findAll();
    return codelist.stream().map((CdList) -> CodelistRs.converter(CdList, Collections.EMPTY_LIST))
        .collect(Collectors.toList());
  }

  // SELECT por ID //
  @GetMapping("/{id}")
  public CodelistRs selectID(@PathVariable("id") Long id) {
    var CdList = codelistRepository.getOne(id);
    return CodelistRs.converter(CdList, Collections.EMPTY_LIST);
  }

  // SELECT os Codelist's de determinado documento//
  @GetMapping("/codelistdoc")
  public List<CodelistRs> selectJoin(@RequestParam(value = "docid", required = false) Long docid) {
    var codelist = codelistRepository.SelectCodelistDoc(docid);
    return codelist.stream().map((codList) -> CodelistRs.converter(codList, Collections.EMPTY_LIST))
        .collect(Collectors.toList());
  }

  // SELECT os Codelist's de determinado documento//
  @GetMapping("/blocotraco")
  public List<CodelistRs> selectTracoBloco(@RequestParam(value = "tracoid", required = false) Long tracoid) {
    var codelist = codelistRepository.SelectTracoCodelist(tracoid);
    return codelist.stream().map((codList) -> CodelistRs.converter(codList, Collections.EMPTY_LIST))
        .collect(Collectors.toList());
  }

  // INSERT //
  // precisa do ID do documento//
  @PostMapping("/{docId}")
  public void insertCodelist(@RequestBody CodelistRq CdList, DocumentoRq Doc, @PathVariable Long docId)
      throws Exception {
    // Busca na tabela documento, salvando dados do caminho dos diretórios
    var doc = documentoRepository.getOne(docId);
    String docNome = doc.getDocumentonome();
    String docPn = doc.getDocumentopn();
    String docCaminho = doc.getDocumentocaminho();

    String pasta = docCaminho + "\\" + docNome + "-" + docPn;

    var cdList = new Codelist();
    cdList.setCodelistcaminho(pasta);
    cdList.setCodelistcodebloco(CdList.getCodelistcodebloco());
    cdList.setCodelistnomebloco(CdList.getCodelistnomebloco());
    cdList.setCodelistsecao(CdList.getCodelistsecao());
    cdList.setCodelistsubsecao(CdList.getCodelistsubsecao());
    cdList.setDocumentoid(CdList.getDocumentoid());
    codelistRepository.save(cdList);

    // criando diretório do bloco

    String caminhoBloco = pasta + "\\" + CdList.getCodelistnomebloco();
    File newDir1 = new File("\\" + caminhoBloco);
    newDir1.mkdir();
    if (CdList.getCodelistsecao() != "") {
      caminhoBloco = caminhoBloco + "\\" + CdList.getCodelistsecao();
      File newDir2 = new File("\\" + caminhoBloco);
      newDir2.mkdir();
    }
    if (CdList.getCodelistsubsecao() != "") {
      caminhoBloco = caminhoBloco + "\\" + CdList.getCodelistsubsecao();
      File newDir3 = new File("\\" + caminhoBloco);
      newDir3.mkdir();
    }

  }

  // UPDATE
  @PutMapping("/{id}")
  public void updateCodelist(@PathVariable Long id, @RequestBody CodelistRq CdList) throws Exception {
    var cdList = codelistRepository.findById(id);

    if (cdList.isPresent()) {
      var cdList2 = cdList.get();
      cdList2.setCodelistcaminho(CdList.getCodelistcaminho());
      cdList2.setCodelistcodebloco(CdList.getCodelistcodebloco());
      cdList2.setCodelistnomebloco(CdList.getCodelistnomebloco());
      cdList2.setCodelistsecao(CdList.getCodelistsecao());
      cdList2.setCodelistsubsecao(CdList.getCodelistsubsecao());
      cdList2.setDocumentoid(CdList.getDocumentoid());
      codelistRepository.save(cdList2);
    } else {
      throw new Exception("Documento não encontrado");
    }
  }

  // DELETE
  @DeleteMapping("/{id}")
  public void deleteCodelist(@PathVariable Long id) {
    var doc = codelistRepository.getOne(id);
    String nome = doc.getCodelistnomebloco();
    String caminho = doc.getCodelistcaminho();

    String pasta = caminho + "\\" + nome;
    File folder = new File(pasta);
    if (folder.isDirectory()) {
      File[] sun = folder.listFiles();
      for (File toDelete : sun) {
        toDelete.delete();
      }
      folder.delete();
    }

    codelistRepository.deleteById(id);
  }

  // Filtrando todos os blocos que fazem parte do traço selecionado
  @GetMapping("/blocostraco")
  public List<CodelistRs> blocosTraco(@RequestParam(value = "docid", required = false) Long docid,
      @RequestParam(value = "tracoid", required = false) Long tracoid) {
    var codelist = codelistRepository.SelectBlocosTraco(docid, tracoid);
    return codelist.stream().map((codList) -> CodelistRs.converter(codList, Collections.EMPTY_LIST))
        .collect(Collectors.toList());
  }

  // Filtrando todos os blocos que fazem parte do traço selecionado Trazendo a
  // lista de traços
  @GetMapping("/blocostracos")
  public List<CodelistRs> blocosTracos(@RequestParam(value = "docid", required = false) Long docid,
      @RequestParam(value = "tracoid", required = false) Long tracoid) {
    List<CodelistRs> lstCodelist = new ArrayList<>();

    var codelist = codelistRepository.SelectBlocosTraco(docid, tracoid);

    for (Codelist c : codelist) {
      CodelistRs codelistRs = CodelistRs.converter(c, tracoDocRepository.selectTracosBloco(c.getCodelistid()));
      lstCodelist.add(codelistRs);
    }

    return lstCodelist;
  }

  // Gerar FULL

  /*
   * Pesquisar todos os blocos do documento que são do traço selecionado OK Pegar
   * todos os caminhos dos blocos e baixar os arquivos em um só
   */

  @GetMapping("/gerarfull")
  public List<CodelistRs> gerarfull(@RequestParam(value = "docid", required = false) Long docid,
      @RequestParam(value = "tracoid", required = false) Long tracoid) throws Exception {

    // Pegando nome do documento
    var documento = documentoRepository.getOne(docid);
    String DocumentoNome = documento.getDocumentonome();
    String DocumentoPn = documento.getDocumentopn();
    String DocCaminho = documento.getDocumentocaminho();
    String DocNome = DocumentoNome + "-" + DocumentoPn;
    String DirDoc = DocCaminho + DocNome;

    var traco = tracoDocRepository.getOne(tracoid);
    String tracoNome = traco.getTracodocnome();
    String tracoCode = traco.getTracodoccodigo();

    Connection conn1 = null;
    ResultSet resultadoBanco1 = null;
    conn1 = DBConexao.abrirConexao();
    Statement stm1 = conn1.createStatement();

    String sql1 = "SELECT traco_doc_nome, traco_doc_codigo, codelist_id, codelist_secao, codelist_subsecao, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
        + "	FROM codelist " + " INNER JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id "
        + " INNER JOIN traco_doc ON traco_doc.traco_doc_id = relacao_bloco_traco.traco_id " + " WHERE documento_id = "
        + docid + "  AND traco_id = " + tracoid + " ;";
    resultadoBanco1 = stm1.executeQuery(sql1);

    PDFMergerUtility PDFmerger = new PDFMergerUtility();
    PDFmerger.setDestinationFileName(DirDoc + "_" + tracoNome + "_" + tracoCode + "FULL.pdf");

    int i = 1;

    while (resultadoBanco1.next()) {
      String caminhoBloco = resultadoBanco1.getString("codelist_caminho");
      String Bloco = resultadoBanco1.getString("codelist_nomebloco");
      String Secao = resultadoBanco1.getString("codelist_secao");
      String subSecao = resultadoBanco1.getString("codelist_subsecao");

      String caminhoarquivo = caminhoBloco + "\\" + Bloco; // Criando caminho para carregar o arquivo
      if (Secao != "") {
        caminhoarquivo = caminhoarquivo + "\\" + Secao;
      }
      if (subSecao != "") {
        caminhoarquivo = caminhoarquivo + "\\" + subSecao;
      }

      String nomeArquivo = DocNome + "-" + Secao; // Criando nome do arquivo seguindo padrão do mockup (nome doc + secao
                                                  // + subsecao + num - bloco)
      if (subSecao != "") {
        nomeArquivo = nomeArquivo + "-" + subSecao;
      }
      nomeArquivo = nomeArquivo + "-" + Bloco;

      File file = new File(caminhoarquivo + "\\" + nomeArquivo + ".pdf");

      PDFmerger.addSource(file);

      i++;
    }

    PDFmerger.mergeDocuments();

    return null;
  }

}
