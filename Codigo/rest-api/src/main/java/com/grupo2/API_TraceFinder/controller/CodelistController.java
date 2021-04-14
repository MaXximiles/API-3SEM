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

import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRq;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRs;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRs;
import com.grupo2.API_TraceFinder.repository.CodelistCustomRepository;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;
import com.grupo2.API_TraceFinder.repository.DocumentoCustomRepository;

@RestController
@RequestMapping("/codelist")
public class CodelistController {
	
	private CodelistRepository codelistRepository = null;
	private CodelistCustomRepository codelistCustomRepository = null;
	
	public CodelistController(CodelistRepository codelistRepository, CodelistCustomRepository codelistCustomRepository) {
		this.codelistRepository = codelistRepository;
		this.codelistCustomRepository = codelistCustomRepository;
		
	}
	
	
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
	
	// SELECT CUSTOM JOIN Documento
		@GetMapping("/joindocumento")
		public List<CodelistRs> findCodelistByjoinDocumento(
				@RequestParam(value = "codelistid", required = false) Long codelistid, 
				@RequestParam(value = "codelistsecao", required = false) String codelistsecao,
				@RequestParam(value = "codelistsubsecao", required = false) String codelistsubsecao,
				@RequestParam(value = "codelistnbloco", required = false) String codelistnbloco,
				@RequestParam(value = "codelistcodebloco", required = false) String codelistcodebloco,
				@RequestParam(value = "codelistcaminho", required = false) String codelistcaminho,
				@RequestParam(value = "codelistdocumentoid", required = false) String codelistdocumentoid
		)
		{
			return this.codelistCustomRepository.findJoinDocumento(codelistid, codelistsecao, codelistsubsecao, codelistnbloco, codelistcodebloco, codelistcaminho, codelistdocumentoid)
					.stream()
					.map(CodelistRs::converter)
					.collect(Collectors.toList());
		}
	
	// INSERT //
	@PostMapping("/")
	public void insertCodelist(@RequestBody CodelistRq CdList)
	{
		var cdList = new Codelist();
		cdList.setCodelistcaminho(CdList.getCodelistcaminho());
		cdList.setCodelistcodebloco(CdList.getCodelistcodebloco());
		cdList.setCodelistnbloco(CdList.getCodelistnbloco());
		cdList.setCodelistsecao(CdList.getCodelistsecao());
		cdList.setCodelistsubsecao(CdList.getCodelistsubsecao());
		cdList.setCodelistdocumentoid(CdList.getCodelistdocumentoid());
		codelistRepository.save(cdList);
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
			cdList2.setCodelistnbloco(CdList.getCodelistnbloco());
			cdList2.setCodelistsecao(CdList.getCodelistsecao());
			cdList2.setCodelistsubsecao(CdList.getCodelistsubsecao());
			cdList2.setCodelistdocumentoid(CdList.getCodelistdocumentoid());
			codelistRepository.save(cdList2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteCodelist(@PathVariable Long id)
	{	
		codelistRepository.deleteById(id);
	}
	
}
