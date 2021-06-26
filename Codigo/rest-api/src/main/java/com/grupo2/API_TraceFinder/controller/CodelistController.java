package com.grupo2.API_TraceFinder.controller;


import java.awt.geom.Rectangle2D;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.DBConexao;
import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRs;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRq;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRs;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRq;
import com.grupo2.API_TraceFinder.controller.dto.TracoDocRs;
import com.grupo2.API_TraceFinder.repository.ArquivoRepository;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoRepository;
import com.grupo2.API_TraceFinder.repository.LepRepository;
import com.grupo2.API_TraceFinder.repository.RelacaoBlocoTracoRepository;
import com.grupo2.API_TraceFinder.repository.TagRepository;
import com.grupo2.API_TraceFinder.repository.TracoDocRepository;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

@RestController
@RequestMapping("/codelist")
public class CodelistController 
{
	
	private CodelistRepository codelistRepository = null;
	private DocumentoRepository documentoRepository;
	private RelacaoBlocoTracoRepository relacaoBlocoTracoRepository;
	private TracoDocRepository tracoDocRepository;
	private ArquivoRepository arquivoRepository;
	private TagRepository tagRepository;
	private LepRepository lepRepository;
	
	
	public CodelistController(CodelistRepository codelistRepository, DocumentoRepository documentoRepository, RelacaoBlocoTracoRepository relacaoBlocoTracoRepository,
							  TracoDocRepository tracoDocRepository, ArquivoRepository arquivoRepository, TagRepository tagRepository, LepRepository lepRepository)
	{
		this.codelistRepository = codelistRepository;
		this.documentoRepository = documentoRepository;
		this.relacaoBlocoTracoRepository = relacaoBlocoTracoRepository;
		this.tracoDocRepository = tracoDocRepository;
		this.arquivoRepository = arquivoRepository;
		this.tagRepository = tagRepository;
		this.lepRepository = lepRepository;
		
	}
	
	
	// Definindo raiz para criação dos diretórios dos manuais 
	private String raiz = "C:\\trace_finder\\";
	
// SELECT de todos//
@GetMapping("/")
public List<CodelistRs> selectAll()
{
	var codelist = codelistRepository.findAll();
	return codelist.stream().map((CdList) -> CodelistRs.converter(CdList, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST)).collect(Collectors.toList());	
}
	
// SELECT Bloco por ID trazendo os traços com Tags //
@GetMapping("/{id}")
public List<CodelistRs> selectID(@RequestParam(value = "blocoid", required = false) Long blocoid) 
{
			
	 List<CodelistRs> lstCodelist = new ArrayList<>();
		
		var codelist = codelistRepository.SelectBlocoTracos(blocoid);
		
		for(Codelist c : codelist)
		{
			CodelistRs codelistRs = CodelistRs.converter(c, tracoDocRepository.selectTracosBloco(c.getCodelistid()), arquivoRepository.selectArquivos(c.getCodelistid()), tagRepository.selectTagBloco(c.getCodelistid()));
			lstCodelist.add(codelistRs);
		}
		
		
				
		return lstCodelist;	
}
	
// SELECT os Codelist's de determinado documento//
@GetMapping("/codelistdoc")
public List<CodelistRs> selectJoin(@RequestParam(value = "docid", required = false) Long docid)
{
	var codelist = codelistRepository.SelectCodelistDoc(docid);
	return codelist.stream().map((codList) -> CodelistRs.converter(codList, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST)).collect(Collectors.toList());	
}
	
	
// SELECT os Codelist's de determinado documento//
@GetMapping("/blocotraco")
public List<CodelistRs> selectTracoBloco(@RequestParam(value = "tracoid", required = false) Long tracoid)
{
	var codelist = codelistRepository.SelectTracoCodelist(tracoid);
	return codelist.stream().map((codList) -> CodelistRs.converter(codList, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST)).collect(Collectors.toList());	
}

//SELECT blocos do sistema filtrando pelo nome do bloco //
@GetMapping("/blocosnome")
public List<CodelistRs> selectNomesBloco(@RequestParam(value = "bloconome", required = false) String bloconome)
{
	var codelist = codelistRepository.SelectNomeBloco(bloconome);
	return codelist.stream().map((codList) -> CodelistRs.converter(codList, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST)).collect(Collectors.toList());	
}
		

  //Filtrando todos os blocos que fazem parte do traço selecionado Trazendo a lista de traços
 @GetMapping("/blocostracos")
 public List<CodelistRs> blocosTracos(@RequestParam(value = "docid", required = false) Long docid,
 									 @RequestParam(value = "tracoid", required = false) Long tracoid) 
 {
	 List<CodelistRs> lstCodelist = new ArrayList<>();
		
		var codelist = codelistRepository.SelectBlocosTraco(docid, tracoid);
		
		for(Codelist c : codelist)
		{
			CodelistRs codelistRs = CodelistRs.converter(c, tracoDocRepository.selectTracosBloco(c.getCodelistid()), arquivoRepository.selectArquivos(c.getCodelistid()), tagRepository.selectTagBloco(c.getCodelistid()));
			lstCodelist.add(codelistRs);
		}
				
		return lstCodelist;	
 }
  
 
 //Filtrando todos os blocos que fazem parte do documento selecionado Trazendo a lista de traços
@GetMapping("/blocosdoctracos")
public List<CodelistRs> blocosTracos(@RequestParam(value = "docid", required = false) Long docid) 
{
	 List<CodelistRs> lstCodelist = new ArrayList<>();
	 
		var codelist = codelistRepository.SelectBlocosDocTraco(docid);
		
		for(Codelist c : codelist)
		{
			CodelistRs codelistRs = CodelistRs.converter(c, tracoDocRepository.selectTracosBloco(c.getCodelistid()), arquivoRepository.selectArquivos(c.getCodelistid()), tagRepository.selectTagBloco(c.getCodelistid()));
			lstCodelist.add(codelistRs);
		}
				
		return lstCodelist;	
}
 

//Filtrando todos os tracos que fazem parte do bloco
@GetMapping("/tracosbloco")
public List<TracoDocRs> tracosBlocos(@RequestParam(value = "blocoid", required = false) Long blocoid) 
{		
	var tracos = tracoDocRepository.selectTracosBloco(blocoid);
	return tracos.stream().map((CdList) -> TracoDocRs.converter(CdList)).collect(Collectors.toList());
	
}

 

//SELECT das Revisões do documento para FULL //
@GetMapping("/revisoes")
public List selectRevisoes(@RequestParam(value = "docid", required = false) Long docid)
{
	return arquivoRepository.selectRevisoes(docid);
}

//SELECT das Revisões do documento para Delta //
@GetMapping("/revisoesdelta")
public List selectRevisoesDelta(@RequestParam(value = "docid", required = false) Long docid,
								@RequestParam(value = "tracoid", required = false) Long tracoid)
{
	return arquivoRepository.SelectRevisoesDelta(docid, tracoid);
}



// INSERT //
// precisa do ID do documento//
@PostMapping("/{docId}")
public void insertCodelist(@RequestBody CodelistRq CdList, DocumentoRq Doc, @PathVariable Long docId) throws Exception
{						
	// Busca na tabela documento, salvando dados do caminho dos diretórios
	var doc = documentoRepository.getOne(docId);	
    String docNome = doc.getDocumentonome();
    String docPn = doc.getDocumentopn();
    String docCaminho = doc.getDocumentocaminho();
    
    String pasta = docCaminho + "\\" + docNome + "-" + docPn;  
    	    
	var cdList = new Codelist();
	cdList.setCodelistcaminho(pasta);
	cdList.setCodelistcodebloco(CdList.getCodelistcodebloco());
	cdList.setCodelistnumbloco(CdList.getCodelistnumbloco());
	cdList.setCodelistnomebloco(CdList.getCodelistnomebloco());
	cdList.setCodelistsecao(CdList.getCodelistsecao());
	cdList.setCodelistsubsecao(CdList.getCodelistsubsecao());
	cdList.setDocumentoid(CdList.getDocumentoid());
	codelistRepository.save(cdList);
	
	// criando diretório do bloco 
	
	String caminhoBloco = pasta+"\\"+CdList.getCodelistsecao();
	File newDir1 = new File("\\" + caminhoBloco);
	newDir1.mkdir();
	if(CdList.getCodelistsubsecao() != "") 
	{ 
		caminhoBloco = caminhoBloco+"\\"+CdList.getCodelistsubsecao();
		File newDir2 = new File("\\" + caminhoBloco);
	    newDir2.mkdir();
	}
	if(CdList.getCodelistnumbloco() != "")
	{ 
		caminhoBloco = caminhoBloco+"\\"+CdList.getCodelistnumbloco()+"_"+CdList.getCodelistnomebloco();
		File newDir3 = new File("\\" + caminhoBloco);
	    newDir3.mkdir();
	}

}

// UPDATE
@PutMapping("/{id}")
public void updateCodelist(@PathVariable Long id, @RequestBody CodelistRq CdList) throws Exception
{
	var cdList = codelistRepository.findById(id);
	
	if(cdList.isPresent())
	{
		var cdList2 = cdList.get();
		cdList2.setCodelistcaminho(CdList.getCodelistcaminho());
		cdList2.setCodelistcodebloco(CdList.getCodelistcodebloco());
		cdList2.setCodelistnomebloco(CdList.getCodelistnomebloco());
		cdList2.setCodelistnumbloco(CdList.getCodelistnumbloco());
		cdList2.setCodelistsecao(CdList.getCodelistsecao());
		cdList2.setCodelistsubsecao(CdList.getCodelistsubsecao());
		cdList2.setDocumentoid(CdList.getDocumentoid());
		codelistRepository.save(cdList2);
	}
	else { throw new Exception("Documento não encontrado"); }
}

// DELETE ARQUIVOS E PASTAS	
public void removerArquivos(File f) {
    if (f.isDirectory()) {
        File[] files = f.listFiles();
        for (File file : files) {
            removerArquivos(file);
        }
    }
    f.delete();
 }


// DELETE
@DeleteMapping("/{id}")
public void deleteCodelist(@PathVariable Long id)
{	
	// Pegando caminho do arquivo 
	//String arquivoCaminho = arquivoRepository.selectArquivoCaminho(id);	
	var arquivo = arquivoRepository.selectArquivos(id); 
	String arquivoCaminho = null;
	Long arquivoId = null;
	if(!arquivo.isEmpty()) 
	{
		arquivoCaminho = arquivo.get(0).getArquivocaminho();
		arquivoId = arquivo.get(0).getArquivoid();
	}
	// Pegando caminho do bloco
	var doc = codelistRepository.getOne(id);
	Long CodelistId = doc.getCodelistid();
	Long DocumentoId = doc.getDocumentoid();
	String num = doc.getCodelistnumbloco();
	String nome = doc.getCodelistnomebloco();
	String caminho = doc.getCodelistcaminho();
	String code = doc.getCodelistcodebloco();
	String CodelistSecao = doc.getCodelistsecao();
	String CodelistSubsecao = doc.getCodelistsubsecao();
	
	
	String caminhoBloco = caminho+"\\"+CodelistSecao;
	if(CodelistSubsecao != ""){	caminhoBloco = caminhoBloco+"\\"+CodelistSubsecao;}
	if(num != ""){caminhoBloco = caminhoBloco+"\\"+num+"_"+nome;}
	 
	
	 File folder = new File(caminhoBloco);
	 boolean Caminho = folder.exists();
	 boolean Diretorio = folder.isDirectory();
	 
	 
	 

		 if(arquivoCaminho != null)
		 {	
			 if (folder.list().length == 1) 
			 {
				File pasta = new File(caminho+"\\"+CodelistSecao);
				removerArquivos(pasta);
			}
			 else 
			 {
				 File arquivo1 = new File(arquivoCaminho);
				 arquivo1.delete();
			 }
		 }
		 else
		 {	
			
			if (folder.exists() && folder.list().length == 0) 
			{	
				File pasta = new File(caminho+"\\"+CodelistSecao);
				removerArquivos(pasta);
			}
		 }
	  
	 
	 	
	codelistRepository.deleteById(id);
	arquivoRepository.deleteBlocoId(id);
	if (arquivoId != null) 
	{
		lepRepository.deleteArquivoLep(arquivoId);
	}
	
	relacaoBlocoTracoRepository.deleteRelacaoBlocoTraco(id);
	
}


  //Gerar FULL
  
  /*
   * Pesquisar todos os blocos do documento que são do traço selecionado OK
   * Pegar todos os caminhos dos blocos e baixar os arquivos em um só
   */
	
  
  @GetMapping("/gerarfull")
  public List<CodelistRs> gerarfull(@RequestParam(value = "docid", required = false) Long docid, 
		  							@RequestParam(value = "tracoid", required = false) Long tracoid,
		  							@RequestParam(value = "revisao", required = false) String revisao) throws Exception 
  { 
	  
	// Pegando nome do documento
	var documento = documentoRepository.getOne(docid);
	String DocumentoNome = documento.getDocumentonome();
	String DocumentoPn = documento.getDocumentopn();
	String DocCaminho = documento.getDocumentocaminho();
	String DocNome = DocumentoNome+"-"+DocumentoPn;
	String DirDoc = DocCaminho+DocNome;
	
	
	var traco = tracoDocRepository.getOne(tracoid);
	String tracoNome = traco.getTracodocnome();
	String tracoCode = traco.getTracodoccodigo();
	
	String rev[] = revisao.split(" ");
	
	String revision;
	
	if (revisao.equals("ORIGINAL")) {  revision = "ORIGINAL"; } 
	else{revision = "REV"+rev[1];}
	
	String and;
	if(rev[0] == "ORIGINAL")
	{
		and = " AND arquivo_revisao LIKE 'ORIGINAL'";
	}
	else
	{	
		and = " AND arquivo_revisao <= '"+revisao+"'";
	}
	
	 
	  
	 Connection conn1 = null;
	 ResultSet resultadoBanco1 = null;
	 conn1 = DBConexao.abrirConexao();
	 Statement stm1 = conn1.createStatement();
		 
	 String sql1 = "SELECT traco_doc_nome, traco_doc_codigo, codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
				+ "	FROM codelist "
				+ " INNER JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id "
				+ " INNER JOIN traco_doc ON traco_doc.traco_doc_id = relacao_bloco_traco.traco_id "
				+ " INNER JOIN arquivo ON arquivo.codelist_id = codelist.codelist_id "
				+ " WHERE documento_id = "+docid+"  AND traco_id = "+tracoid+" "+and+" ;";
	 resultadoBanco1 = stm1.executeQuery(sql1);
	 System.out.println(sql1);
	 
	 PDFMergerUtility PDFmerger = new PDFMergerUtility();
	 PDFmerger.setDestinationFileName(DirDoc+"\\"+DocNome+"-"+tracoNome+"-"+tracoCode+"-"+revision+"-FULL.pdf");
	 
	 int i = 1;

	 while(resultadoBanco1.next())
	 { 
		 String caminhoBloco = resultadoBanco1.getString("codelist_caminho");
		 String numBloco = resultadoBanco1.getString("codelist_numbloco");
		 String Bloco = resultadoBanco1.getString("codelist_nomebloco");
		 String Code = resultadoBanco1.getString("codelist_codebloco");
		 String Secao = resultadoBanco1.getString("codelist_secao");
		 String subSecao = resultadoBanco1.getString("codelist_subsecao");
		 		 
		String caminhoarquivo = caminhoBloco+"\\"+Secao; //Criando caminho para carregar o arquivo
		if(subSecao != "") {caminhoarquivo = caminhoarquivo+"\\"+subSecao;}
		caminhoarquivo = caminhoarquivo+"\\"+numBloco+"_"+Bloco;
		
		
		String nomeArquivo = DocNome+"-"+Secao; // Criando nome do arquivo seguindo padrão do mockup (nome doc + secao + subsecao + num - bloco)
		if(subSecao != "") {nomeArquivo = nomeArquivo+"-"+subSecao;}
		nomeArquivo = nomeArquivo+"-"+numBloco+"c"+Code;
		
		
		 File file = new File(caminhoarquivo+"\\"+nomeArquivo+".pdf");
		 		 
		 PDFmerger.addSource(file);
		 
		 i++;
		 
	 }
	 
	 
	 PDFmerger.mergeDocuments();
	 
	return null;
  }


  
  //Gerar DELTA
  
  /*
   * Pesquisar todos os blocos do documento que são do traço selecionado OK
   * Pegar todos os caminhos dos blocos e baixar os arquivos em um só
   */
	
  
  @GetMapping("/gerardelta")
  public List<CodelistRs> gerarDelta(@RequestParam(value = "docid", required = false) Long docid, 
		  							@RequestParam(value = "tracoid", required = false) Long tracoid,
		  							@RequestParam(value = "revisao", required = false) String revisao) throws Exception 
  { 
	  
	// Pegando nome do documento
	var documento = documentoRepository.getOne(docid);
	String DocumentoNome = documento.getDocumentonome();
	String DocumentoPn = documento.getDocumentopn();
	String DocCaminho = documento.getDocumentocaminho();
	String DocNome = DocumentoNome+"-"+DocumentoPn;
	String DirDoc = DocCaminho+DocNome;
	
	
	var traco = tracoDocRepository.getOne(tracoid);
	String tracoNome = traco.getTracodocnome();
	String tracoCode = traco.getTracodoccodigo();
	String rev[] = revisao.split(" ");
	String revision;
	
	if (revisao.equals("ORIGINAL")) {  revision = "ORIGINAL"; }
	else 
	{
	revision = "REV"+rev[1]; 
	}
	
	String and;
	if(rev[0] == "REVISION"){ and = " AND arquivo_revisao LIKE '"+revisao+"'"; }
	else{ and = " AND arquivo_revisao LIKE 'ORIGINAL'"; }
	
	 
	  
	 Connection conn1 = null;
	 ResultSet resultadoBanco1 = null;
	 conn1 = DBConexao.abrirConexao();
	 Statement stm1 = conn1.createStatement();
		 
	 String sql1 = "SELECT traco_doc_nome, traco_doc_codigo, codelist.codelist_id, codelist_secao, codelist_subsecao, codelist_numbloco, codelist_nomebloco, codelist_codebloco, codelist_caminho, documento_id "
				+ "	FROM codelist "
				+ " INNER JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id "
				+ " INNER JOIN traco_doc ON traco_doc.traco_doc_id = relacao_bloco_traco.traco_id "
				+ " INNER JOIN arquivo ON arquivo.codelist_id = codelist.codelist_id "
				+ " WHERE documento_id = "+docid+"  AND traco_id = "+tracoid+" ;";
	 resultadoBanco1 = stm1.executeQuery(sql1);
	 
	 /*Criando arquivo Delta*/
	 PDDocument novodelta = new PDDocument();
	 novodelta.save(DirDoc+"\\"+DocNome+"-"+tracoNome+"-"+tracoCode+"-"+revision+"-DELTA.pdf");
	 novodelta.close();
	
	 File file = new File(DirDoc+"\\"+DocNome+"-"+tracoNome+"-"+tracoCode+"-"+revision+"-DELTA.pdf");
	 PDDocument delta = PDDocument.load(file);
	 
	 int i = 1;

	 while(resultadoBanco1.next())
	 { 
		 String caminhoBloco = resultadoBanco1.getString("codelist_caminho");
		 String numBloco = resultadoBanco1.getString("codelist_numbloco");
		 String Bloco = resultadoBanco1.getString("codelist_nomebloco");
		 String Code = resultadoBanco1.getString("codelist_codebloco");
		 String Secao = resultadoBanco1.getString("codelist_secao");
		 String subSecao = resultadoBanco1.getString("codelist_subsecao");
		
		 
		String caminhoarquivo = caminhoBloco+"\\"+Secao; //Criando caminho para carregar o arquivo
		if(subSecao != "") {caminhoarquivo = caminhoarquivo+"\\"+subSecao;}
		caminhoarquivo = caminhoarquivo+"\\"+numBloco+"_"+Bloco;
		 
		String nomeArquivo = DocNome+"-"+Secao; // Criando nome do arquivo seguindo padrão do mockup (nome doc + secao + subsecao + num - bloco)
		if(subSecao != "") {nomeArquivo = nomeArquivo+"-"+subSecao;}
		nomeArquivo = nomeArquivo+"-"+numBloco+"c"+Code;

		 File file1 = new File(caminhoarquivo+"\\"+nomeArquivo+".pdf");
		 PDDocument document = PDDocument.load(file1);
		 int numPag = document.getNumberOfPages();
		 	
		 int contPage = 0;
		 String rev1 = null;
		 
		// Laço para inserção das paginas na LEP
		for(int n = 1; n <= numPag; n++)
		{	
		    	PDPage Pages = document.getPage(contPage);
	            		
	            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	            stripper.setSortByPosition(true);

	            Rectangle2D area = new Rectangle2D.Float(0f, 570f, 400f, 60f);
	            stripper.addRegion("rodape", area);
	            stripper.extractRegions(Pages);            

	            String pdfFileInText = stripper.getTextForRegion("rodape");
	            
	            int posicao = pdfFileInText.lastIndexOf("REVISION");
	            
	            
	            if(posicao != -1){ rev1 = pdfFileInText.substring(posicao, posicao+11); } 
	            else{ rev1 = "ORIGINAL"; }
	            
	            if(revisao.equals(rev1))
	            {
	            	delta.importPage(Pages);
	            	delta.save(file);
	            }
	            
	            contPage++;
		}
		//document.close(); // Deixar comentado
		 
		i++;
	 }
	 delta.close();

	return null; 
	 
  } 

  
}
