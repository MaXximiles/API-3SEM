package com.grupo2.API_TraceFinder.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
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
import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.classes.Lep;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRs;
import com.grupo2.API_TraceFinder.controller.dto.LepRq;
import com.grupo2.API_TraceFinder.controller.dto.LepRs;
import com.grupo2.API_TraceFinder.repository.ArquivoRepository;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoRepository;
import com.grupo2.API_TraceFinder.repository.LepRepository;


@RestController
@RequestMapping("/lep")
public class LepController {

	
	private LepRepository lepRepository;
	private DocumentoRepository documentoRepository;
	private CodelistRepository codelistRepository;
	private ArquivoRepository arquivoRepository;
	
	public LepController(LepRepository LepRepository, CodelistRepository CodelistRepository, DocumentoRepository DocumentoRepository, ArquivoRepository ArquivoRepository)
	{
		this.lepRepository = LepRepository;
		this.documentoRepository = DocumentoRepository;
		this.codelistRepository = CodelistRepository;
		this.arquivoRepository = ArquivoRepository;
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
		String CodelistSecao = codelist.getCodelistsecao();
		String CodelistSubsecao = codelist.getCodelistsubsecao();

		// Pegando nome do documento
		var documento = documentoRepository.getOne(DocumentoId);
		String DocumentoNome = documento.getDocumentonome();
		String DocumentoPn = documento.getDocumentopn();
		String DocNome = DocumentoNome+"-"+DocumentoPn;
		Long DocCdlistLep = documento.getDocumentocdlistlep();
		
		String CaminhoLep = CodelistCaminho+"\\"+CodelistBloco; //Criando caminho para salvar a LEP
		if(CodelistSecao != "") {CaminhoLep = CaminhoLep+"\\"+CodelistSecao;}
		if(CodelistSubsecao != "") {CaminhoLep = CaminhoLep+"\\"+CodelistSubsecao;}
		
		String nomeArquivo = DocNome+"-"+CodelistSecao; // Criando nome do arquivo seguindo padrão do mockup (nome doc + secao + subsecao + num - bloco)
		if(CodelistSubsecao != "") {nomeArquivo = nomeArquivo+"-"+CodelistSubsecao;}
		nomeArquivo = nomeArquivo+"-"+CodelistBloco;
		/////////////////////////
		
		PDDocument pDDocument1 = PDDocument.load(new File("src/main/resources/models/ModeloLEP.pdf"));
		pDDocument1.save(CaminhoLep+"\\"+nomeArquivo+".pdf");
		int NumPag = pDDocument1.getNumberOfPages();
		pDDocument1.close();
		
		
		//////////*/*/*/*/*/*/*/*/*/*/*
		if(DocCdlistLep == null || DocCdlistLep == 0)
		{
			
			var doc = documentoRepository.findById(DocumentoId);
		
		    if (doc.isPresent()) 
		    {
		      var doc2 = doc.get();
		      doc2.setDocumentocdlistlep(codelistid);
		      documentoRepository.save(doc2);
		    } 
		    else { throw new Exception("Documento não encontrado"); }
			
			var lep = new Arquivo();
			lep.setArquivonome(nomeArquivo);
			lep.setCodelistid(codelistid);
			arquivoRepository.save(lep);
			Long arquivo = lep.getArquivoid();
				
			 Connection conn1 = null;
		     ResultSet resultadoBanco1 = null;
		     conn1 = DBConexao.abrirConexao();
		     Statement stm1 = conn1.createStatement();
		    
		     String sql1 = "SELECT MAX(lep_revisao) FROM lep WHERE documento_id = "+DocumentoId+";";
		     resultadoBanco1 = stm1.executeQuery(sql1);
		     
		     String revisao = "";
		     while(resultadoBanco1.next()){ revisao = resultadoBanco1.getString("MAX(lep_revisao)");}
			
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

		/* ****************************************************************************************** */
								
		PDDocument pDDocument = PDDocument.load(new File(CaminhoLep+"\\"+nomeArquivo+".pdf"));
		//PDDocument pDDocument = PDDocument.load(new File("C:\\trace_finder\\ModeloLEP.pdf"));
        PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();       
               
        Connection conn = null;
        ResultSet resultadoBanco = null;
        conn = DBConexao.abrirConexao();
        Statement stm = conn.createStatement();
        
        String sql = "SELECT lep_id, lep_bloco, lep_code, lep_pagina, lep_modificacao, lep_revisao, arquivo_id, documento_id FROM lep WHERE documento_id = "+DocumentoId+""
        		+ " ORDER BY lep_bloco;";
        resultadoBanco = stm.executeQuery(sql);
        
        int i = 1;
        while(resultadoBanco.next())
        { 
        		String num1 = Integer.toString(i);
		    	String lepBloco = (resultadoBanco.getString("lep_bloco"));
		    	String arqId = (resultadoBanco.getString("arquivo_id"));
		    	String lepCode = (resultadoBanco.getString("lep_code"));
		    	String lepPage = (resultadoBanco.getString("lep_pagina"));
		    	String lepmodificacao = (resultadoBanco.getString("lep_modificacao"));
		    	String leprevisao = (resultadoBanco.getString("lep_revisao"));
		    	int numPag = pDDocument.getNumberOfPages();
		    			    	
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
        }
        pDDocument.save(CaminhoLep+"\\"+nomeArquivo+".pdf");
        pDDocument.close();
	    
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
