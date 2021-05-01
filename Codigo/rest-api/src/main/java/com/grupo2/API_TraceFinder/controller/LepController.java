package com.grupo2.API_TraceFinder.controller;

import java.io.IOException;
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
		
	
	// Criar PDF LEP, precisa do ID do arquivo
	@GetMapping("/gerarlep")
	public List<LepRs> criarLep(@RequestParam(value = "arqid", required = false) Long arqid, MultipartFile arq) throws IOException
	{
		// Pegando ID do Codelist
		var arquivo = arquivoRepository.getOne(arqid);
		Long CodelistId = arquivo.getCodelistid();
				
		// Pegando ID do Documento, e caminho para salvar arquivo LEP através do Codelist
		var codelist = codelistRepository.getOne(CodelistId);
		Long DocumentoId = codelist.getDocumentoid();
		String CodelistCaminho = codelist.getCodelistcaminho();
		String CodelistBloco = codelist.getCodelistnomebloco();
		String CodelistSecao = codelist.getCodelistsecao();
		String CodelistSubsecao = codelist.getCodelistsubsecao();

		// Pegando nome do documento
		var documento = documentoRepository.getOne(DocumentoId);
		String DocumentoNome = documento.getDocumentonome();
		String DocumentoPn = documento.getDocumentopn();
		String DocNome = DocumentoNome+"-"+DocumentoPn;
		
		String CaminhoLep = CodelistCaminho+"\\"+CodelistBloco; //Criando caminho para salvar a LEP
		if(CodelistSecao != "") {CaminhoLep = CaminhoLep+"\\"+CodelistSecao;}
		if(CodelistSubsecao != "") {CaminhoLep = CaminhoLep+"\\"+CodelistSubsecao;}
		
		String nomeArquivo = DocNome+"-"+CodelistSecao; // Criando nome do arqvuivo seguindo padrão do mackup (nome doc + secao + subsecao + num - bloco)
		if(CodelistSubsecao != "") {nomeArquivo = nomeArquivo+"-"+CodelistSubsecao;}
		nomeArquivo = nomeArquivo+"-"+CodelistBloco;
				
		/* ****************************************************************************************** */
		
				
		ArrayList<LepRs> variavel = new ArrayList<>();
		var lep = lepRepository.SelectLepArquivo(arqid);
		
		variavel = (ArrayList<LepRs>) lep.stream().map((LepList) -> LepRs.converter(LepList)).collect(Collectors.toList());
		
		PDDocument pDDocument = PDDocument.load(new File("C:\\trace_finder\\modeloLEP.pdf"));
        PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();       
                 
	    for (int i=1; i < variavel.size(); i++) 
	    {   
	    	/*
	    	 * Rodar linha por linha
	    	 * na hora de criar a LEP
	    	 * */
	    	
	    	
	    	String num = Integer.toString(i);
	    	String lepBloco = ((List<LepRs>) variavel).get(i).getLepBloco();
	    	String lepCode = ((List<LepRs>) variavel).get(i).getLepCode();
	    	String lepPage = ((List<LepRs>) variavel).get(i).getLepPagina();
	    	String lepmodificacao = ((List<LepRs>) variavel).get(i).getLepModificacao();
	    	String leprevisao = ((List<LepRs>) variavel).get(i).getLepRevisao();
	    	int numPag = pDDocument.getNumberOfPages();
	    	
	    	PDField field = pDAcroForm.getField("bloco"+num);
	        field.setValue(lepBloco);
	        field = pDAcroForm.getField("code"+num);
	        field.setValue(lepCode);        
	        field = pDAcroForm.getField("page"+num);
	        field.setValue(lepPage);
	        field = pDAcroForm.getField("status"+num);
	        field.setValue(lepmodificacao);
	        field = pDAcroForm.getField("revisao"+num);
	        field.setValue(leprevisao);
	        field = pDAcroForm.getField("numPage");
	        field.setValue(Integer.toString(numPag));
	        field = pDAcroForm.getField("docNome"); // Verificar pq não apareceu
	        field.setValue(DocNome);
	        	        
	    }

	    pDDocument.save(CaminhoLep+"\\"+nomeArquivo+".pdf");
        pDDocument.close();

	    
	    return null;
	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<LepRs> selectAll() {
	   var lep = lepRepository.findAll();
	   return lep.stream().map((lp) -> LepRs.converter(lp)).collect(Collectors.toList());
	}
	
}
