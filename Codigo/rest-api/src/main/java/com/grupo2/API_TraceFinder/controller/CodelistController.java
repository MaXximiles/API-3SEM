package com.grupo2.API_TraceFinder.controller;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.CollectionFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
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

import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRq;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRs;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRq;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoCustomRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoRepository;

@RestController
@RequestMapping("/codelist")
public class CodelistController {
	
	private CodelistRepository codelistRepository = null;
	private DocumentoRepository documentoRepository;
	
	
	public CodelistController(CodelistRepository codelistRepository, DocumentoRepository documentoRepository)
	{
		this.codelistRepository = codelistRepository;
		this.documentoRepository = documentoRepository;
	}
	
	
	// Definindo raiz para criação dos diretórios dos manuais 
	private String raiz = "C:\\trace_finder\\";
	
	// SELECT de todos//
	@GetMapping("/")
	public List<CodelistRs> selectAll()
	{
		var codelist = codelistRepository.findAll();
		return codelist.stream().map((CdList) -> CodelistRs.converter(CdList)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public CodelistRs selectID(@PathVariable("id") Long id)
	{
		var CdList = codelistRepository.getOne(id);
		return CodelistRs.converter(CdList);
	}
	
	// SELECT os Codelist's de determinado documento//
	@GetMapping("/codelistdoc")
	public List<CodelistRs> selectJoin(@RequestParam(value = "docid", required = false) Long docid)
	{
		var codelist = codelistRepository.SelectCodelistDoc(docid);
		return codelist.stream().map((codList) -> CodelistRs.converter(codList)).collect(Collectors.toList());	
	}
	
	// SELECT os Codelist's de determinado documento//
	@GetMapping("/blocotraco")
	public List<CodelistRs> selectTracoBloco(@RequestParam(value = "tracoid", required = false) Long tracoid)
	{
		var codelist = codelistRepository.SelectTracoCodelist(tracoid);
		return codelist.stream().map((codList) -> CodelistRs.converter(codList)).collect(Collectors.toList());	
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
		cdList.setCodelistnomebloco(CdList.getCodelistnomebloco());
		cdList.setCodelistsecao(CdList.getCodelistsecao());
		cdList.setCodelistsubsecao(CdList.getCodelistsubsecao());
		cdList.setDocumentoid(CdList.getDocumentoid());
		codelistRepository.save(cdList);
		
		// criando diretório do bloco
		
		String caminhoBloco = pasta+"\\"+CdList.getCodelistnomebloco();
		File newDir1 = new File("\\" + caminhoBloco);
		newDir1.mkdir();
		if(CdList.getCodelistsecao() != "") 
		{ 
			caminhoBloco = caminhoBloco+"\\"+CdList.getCodelistsecao();
			File newDir2 = new File("\\" + caminhoBloco);
		    newDir2.mkdir();
		}
		if(CdList.getCodelistsubsecao() != "")
		{ 
			caminhoBloco = caminhoBloco+"\\"+CdList.getCodelistsubsecao();
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
			cdList2.setCodelistsecao(CdList.getCodelistsecao());
			cdList2.setCodelistsubsecao(CdList.getCodelistsubsecao());
			cdList2.setDocumentoid(CdList.getDocumentoid());
			codelistRepository.save(cdList2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteCodelist(@PathVariable Long id)
	{	
		 var doc = codelistRepository.getOne(id);	
		 String nome = doc.getCodelistnomebloco();
		 String caminho = doc.getCodelistcaminho();
		 
		   
		 String pasta = caminho + "\\" + nome;
		 File folder = new File(pasta);
		 if (folder.isDirectory()) 
		 {
		 	File[] sun = folder.listFiles();
		 	for (File toDelete : sun){toDelete.delete();}
		 	folder.delete();
		 }
		
		codelistRepository.deleteById(id);
	}
	

	
}
