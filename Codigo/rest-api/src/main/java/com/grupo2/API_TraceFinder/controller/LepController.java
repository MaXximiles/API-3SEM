package com.grupo2.API_TraceFinder.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import java.io.File;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grupo2.API_TraceFinder.DBConexao;
import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.classes.Lep;
import com.grupo2.API_TraceFinder.controller.dto.LepRq;
import com.grupo2.API_TraceFinder.controller.dto.LepRs;
import com.grupo2.API_TraceFinder.repository.ArquivoRepository;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoRepository;
import com.grupo2.API_TraceFinder.repository.LepRepository;
import com.grupo2.API_TraceFinder.repository.RelacaoBlocoTracoRepository;
import com.itextpdf.io.codec.Base64.InputStream;
import com.itextpdf.kernel.utils.PdfMerger;

import ch.qos.logback.core.util.Loader;


@RestController
@RequestMapping("/lep")
public class LepController {

	
	private LepRepository lepRepository;
	private DocumentoRepository documentoRepository;
	private CodelistRepository codelistRepository;
	private ArquivoRepository arquivoRepository;
	private RelacaoBlocoTracoRepository relacaoBlocoTracoRepository;
	
	public LepController(LepRepository LepRepository, CodelistRepository CodelistRepository, 
			DocumentoRepository DocumentoRepository, ArquivoRepository ArquivoRepository, RelacaoBlocoTracoRepository RelacaoBlocoTracoRepository)
	{
		this.lepRepository = LepRepository;
		this.documentoRepository = DocumentoRepository;
		this.codelistRepository = CodelistRepository;
		this.arquivoRepository = ArquivoRepository;
		this.relacaoBlocoTracoRepository = RelacaoBlocoTracoRepository;
	}
	
	
	// INSERT //   
	@PostMapping("/")
	public void insertLep(@RequestBody LepRq lep)
	{
		var lep1 = new Lep();
		lep1.setLepId(lep.getLepId());
		lep1.setLepBloco(lep.getLepBloco());
		lep1.setLepCode(lep.getLepCode());
		lep1.setLepModificacao(lep.getLepModificacao());
		lep1.setLepPagina(lep.getLepPagina());
		lep1.setLepRevisao(lep.getLepRevisao());
		lep1.setArquivoId(lep.getArquivoId());
		lep1.setDocumentoid(lep.getDocumentoid()); // Trazer id do Documento(manual)
		lepRepository.save(lep1);
	}
		
	
	// Criar PDF LEP, precisa do ID do codelist
	@GetMapping("/gerarlep")
	public List<LepRs> criarLep(@RequestParam(value = "codelistid", required = false) Long codelistid, MultipartFile arq) throws Exception
	{			
		// Pegando ID do Documento, e caminho para salvar arquivo LEP através do Codelist
		var codelist = codelistRepository.getOne(codelistid);
		Long DocumentoId = codelist.getDocumentoid();
		String CodelistCaminho = codelist.getCodelistcaminho();
		String CodelistCode = codelist.getCodelistcodebloco();
		String CodelistBloco = codelist.getCodelistnomebloco();
		String CodelistnumBloco = codelist.getCodelistnumbloco();
		String CodelistSecao = codelist.getCodelistsecao();
		String CodelistSubsecao = codelist.getCodelistsubsecao();
		
		// Verificando se já existe LEP criada
		String arquivoExiste = null;
		arquivoExiste = arquivoRepository.selectArquivoCaminho(codelistid);
		
		// Pegando traço do bloco
		String traco = relacaoBlocoTracoRepository.SelectTraco(codelistid);
				
		// Pegando nome do documento
		var documento = documentoRepository.getOne(DocumentoId);
		String DocumentoNome = documento.getDocumentonome();
		String DocumentoPn = documento.getDocumentopn();
		String DocNome = DocumentoNome+"-"+DocumentoPn;
		Long DocCdlistLep = documento.getDocumentocdlistlep();
		
		String CaminhoLep = CodelistCaminho+"\\"+CodelistSecao; //Criando caminho para salvar a LEP
		if(CodelistSubsecao != "") {CaminhoLep = CaminhoLep+"\\"+CodelistSubsecao;}
		CaminhoLep = CaminhoLep+"\\"+CodelistnumBloco+"_"+CodelistBloco;
		
		String nomeArquivo = DocNome+"-"+CodelistSecao; // Criando nome do arquivo seguindo padrão do mockup (nome doc + secao + subsecao + num - bloco)
		if(CodelistSubsecao != "") {nomeArquivo = nomeArquivo+"-"+CodelistSubsecao;}
		nomeArquivo = nomeArquivo+"-"+CodelistnumBloco+"c"+CodelistCode;
		
		
		//////////////////////////////////////////////////////////////////					 
		
 
		/* *****************************CARREGANDO ARQUIVO MODELO DE LEP ************************************************************* */
				
		// Destino
		PDDocument lepvazia = new PDDocument();
		lepvazia.save(CaminhoLep+"\\"+nomeArquivo+".pdf");
		lepvazia.close();
		
		File file1 = new File(CaminhoLep+"\\"+nomeArquivo+".pdf");
		PDDocument destino = PDDocument.load(file1);
		
		// Modelo
		File file2 = new File("src/main/resources/models/ModeloLEP.pdf");
		PDDocument modelo = PDDocument.load(file2);
		PDAcroForm pDAcroForm = modelo.getDocumentCatalog().getAcroForm();    
         
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
    	//PDFmerger.setDestinationFileName(CaminhoLep+"\\"+nomeArquivo+".pdf");

        Connection conn = null;
        ResultSet resultadoBanco = null;
        conn = DBConexao.abrirConexao();
        Statement stm = conn.createStatement();
        
       // String sql = "SELECT lep_id, lep_bloco, lep_code, lep_pagina, lep_modificacao, lep_revisao, arquivo_id, documento_id FROM lep WHERE documento_id = "+DocumentoId+""
        //		+ " ORDER BY lep_bloco;";
        
	   	 String sql = "SELECT lep_id, lep_bloco, lep_code, lep_pagina, lep_modificacao, lep_revisao, lep.arquivo_id, lep.documento_id "
					+ "	FROM codelist "
					+ " INNER JOIN relacao_bloco_traco ON relacao_bloco_traco.bloco_id = codelist.codelist_id "
					+ " INNER JOIN traco_doc ON traco_doc.traco_doc_id = relacao_bloco_traco.traco_id "
					+ " INNER JOIN arquivo ON arquivo.codelist_id = codelist.codelist_id "
					+ "	INNER JOIN lep ON lep.arquivo_id = arquivo.arquivo_id "
					+ " WHERE codelist.documento_id = "+DocumentoId+"  AND traco_id = "+traco+" ;";
        
        
        resultadoBanco = stm.executeQuery(sql);
        
                 
        int i = 1;
        int numPag = 1;
        Long lepId = null;
        Long arqId = null;
        while(resultadoBanco.next())
        { 
        		String num1 = Integer.toString(i);
		    	String lepBloco = (resultadoBanco.getString("lep_bloco"));
		    	arqId = (resultadoBanco.getLong("arquivo_id"));
		    	String lepCode = (resultadoBanco.getString("lep_code"));
		    	String lepPage = (resultadoBanco.getString("lep_pagina"));
		    	String lepmodificacao = (resultadoBanco.getString("lep_modificacao"));
		    	String leprevisao = (resultadoBanco.getString("lep_revisao"));
		    	lepId = (resultadoBanco.getLong("lep_id"));
		    		    			    	
	    		PDField field = pDAcroForm.getField("bloco"+num1);
		        field.setValue(lepBloco);
		        field = pDAcroForm.getField("code"+num1);
		        field.setValue(lepCode);        
		        field = pDAcroForm.getField("page"+num1);
		        field.setValue(lepPage);
		        field = pDAcroForm.getField("status"+num1);
		        field.setValue(lepmodificacao);
		        field = pDAcroForm.getField("revisao"+num1);
		        field.setValue(leprevisao);
		        field = pDAcroForm.getField("numPage");
		        field.setValue(Integer.toString(numPag));
		        field = pDAcroForm.getField("docNome"); 
		        field.setValue(DocNome);	
		        
		        i++;
		        
		        if(i % 20 == 0)
		        {	     
		        	PDFmerger.appendDocument(destino, modelo);
		        	i = 1;
		        	numPag++;
		        }
 
        }
        if(i % 20 != 0)
        {	     
        	PDFmerger.appendDocument(destino, modelo);
        	i = 1;
        	numPag++;
        }
        
        destino.save(file1);
        modelo.close();
        destino.close();
        
        File file3 = new File(CaminhoLep+"\\"+nomeArquivo+".pdf");
        PDDocument Lep = PDDocument.load(file3);
        int NumPag = Lep.getNumberOfPages();
        Lep.close();
			
		 Connection conn1 = null;
	     ResultSet resultadoBanco1 = null;
	     conn1 = DBConexao.abrirConexao();
	     Statement stm1 = conn1.createStatement();
	    
	     String sql1 = "SELECT MAX(lep_revisao) FROM lep WHERE documento_id = "+DocumentoId+";";
	     resultadoBanco1 = stm1.executeQuery(sql1);
	     
	     String revisao = "";
	     while(resultadoBanco1.next()){ revisao = resultadoBanco1.getString("MAX(lep_revisao)");}
		
	    /* Salvando Informações no Banco */
	     
	    if (arquivoExiste == null)
	    {
			
	        var lep2 = new Arquivo();
			lep2.setArquivonome(nomeArquivo);
			lep2.setCodelistid(codelistid);
			lep2.setArquivocaminho(CaminhoLep+"\\"+nomeArquivo+".pdf");
			lep2.setArquivorevisao(revisao);
			arquivoRepository.save(lep2);
			Long arquivo = lep2.getArquivoid();
	
			String modificacao = "";
			Long pagina = (long) NumPag;
			
			var lep1 = new Lep();
			lep1.setLepBloco(CodelistBloco);
			lep1.setLepCode(CodelistCode);
			lep1.setLepPagina(pagina); // Inserir quantidade de paginas  
			lep1.setLepModificacao(modificacao); // mudar quando inserir modificacao
			lep1.setLepRevisao(revisao); // mudar quando inserir revisao
			lep1.setArquivoId(arquivo);
			lep1.setDocumentoid(DocumentoId);
			lepRepository.save(lep1);

			

	    }
	    else 
	    {
	    	
	    	String modificacao = "";
			Long pagina = (long) NumPag;
			
	        var lep3 = lepRepository.findById(lepId);
	        
	        if (lep3.isPresent()) 
	        {
				var lep1 = lep3.get();
				lep1.setLepBloco(CodelistBloco);
				lep1.setLepCode(CodelistCode);
				lep1.setLepPagina(pagina); // Inserir quantidade de paginas  
				lep1.setLepModificacao(modificacao); // mudar quando inserir modificacao
				lep1.setLepRevisao(revisao); // mudar quando inserir revisao
				lep1.setArquivoId(arqId);
				lep1.setDocumentoid(DocumentoId);
				lepRepository.save(lep1);
	        }
	        
	        
	        var arq2 = arquivoRepository.findById(arqId);
	        
	        if (arq2.isPresent())
	        {
		        var lep2 = arq2.get();
				lep2.setArquivonome(nomeArquivo);
				lep2.setCodelistid(codelistid);
				lep2.setArquivocaminho(CaminhoLep+"\\"+nomeArquivo+".pdf");
				lep2.setArquivorevisao(revisao);
				arquivoRepository.save(lep2);
				Long arquivo = lep2.getArquivoid();
	        }
	    	

	    }
        
        
	    return null;
	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<LepRs> selectAll() 
	{
	   var lep = lepRepository.findAll();
	   return lep.stream().map((lp) -> LepRs.converter(lp)).collect(Collectors.toList());
	}
	
}
