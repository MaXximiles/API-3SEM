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

import com.grupo2.API_TraceFinder.classes.Codelist;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRq;
import com.grupo2.API_TraceFinder.controller.dto.CodelistRs;
import com.grupo2.API_TraceFinder.repository.CodelistRepository;

@RestController
@RequestMapping("/codelist")
public class CodelistController {
	
	private CodelistRepository codelistRepository = null;
	
	public CodelistController(CodelistRepository codelistRepository) {
		this.codelistRepository = codelistRepository;
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
	
	// INSERT //
	@PostMapping("/")
	public void insertCodelist(@RequestBody CodelistRq CdList)
	{
		var cdList = new Codelist();
		cdList.setCodelist_caminho(CdList.getCodelist_caminho());
		cdList.setCodelist_codebloco(CdList.getCodelist_codebloco());
		cdList.setCodelist_nbloco(CdList.getCodelist_nbloco());
		cdList.setCodelist_secao(CdList.getCodelist_secao());
		cdList.setCodelist_subsecao(CdList.getCodelist_subsecao());
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
			cdList2.setCodelist_caminho(CdList.getCodelist_caminho());
			cdList2.setCodelist_codebloco(CdList.getCodelist_codebloco());
			cdList2.setCodelist_nbloco(CdList.getCodelist_nbloco());
			cdList2.setCodelist_secao(CdList.getCodelist_secao());
			cdList2.setCodelist_subsecao(CdList.getCodelist_subsecao());
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
