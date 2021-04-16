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

import com.grupo2.API_TraceFinder.classes.Bloco;
import com.grupo2.API_TraceFinder.controller.dto.BlocoRq;
import com.grupo2.API_TraceFinder.controller.dto.BlocoRs;
import com.grupo2.API_TraceFinder.repository.BlocoCustomRepository;
import com.grupo2.API_TraceFinder.repository.BlocoRepository;


@RestController
@RequestMapping("/bloco")
public class BlocoController {
	
	private BlocoRepository blocoRepository = null;
	private BlocoCustomRepository blocoCustomRepository = null;
	
	public BlocoController(BlocoRepository blocoRepository, BlocoCustomRepository blocoCustomRepository) {
		this.blocoRepository = blocoRepository;
		this.blocoCustomRepository = blocoCustomRepository;
	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<BlocoRs> selectAll()
	{
		var bloco = blocoRepository.findAll();
		return bloco.stream().map((BList) -> BlocoRs.converter(BList)).collect(Collectors.toList());	
	}
	
	// SELECT com Query//
	@GetMapping("/joindocumento")
	public List<BlocoRs> selectJoin(@RequestParam(value = "blocoid", required = false) Long blocoid)
	{
		var bloco = blocoRepository.SelectAll(blocoid);
		return bloco.stream().map((BlList) -> BlocoRs.converter(BlList)).collect(Collectors.toList());	
	}
	
	
	// SELECT Blocos de um documento//
	@GetMapping("/blocosdocumento")
	public List<BlocoRs> selectBlocosDoc(@RequestParam(value = "docid", required = false) Long docid)
	{
		var doc = blocoRepository.SelectBlocoDoc(docid);
		return doc.stream().map((BlList) -> BlocoRs.converter(BlList)).collect(Collectors.toList());	
	}
		
	// SELECT por ID //
	@GetMapping("/{id}")
	public BlocoRs selectID(@PathVariable("id") Long id)
	{
		var BlList = blocoRepository.getOne(id);
		return BlocoRs.converter(BlList);
	}
	
		
	
	// INSERT //
	@PostMapping("/")
	public void insertBloco(@RequestBody BlocoRq BlList)
	{
		var blList = new Bloco();
		blList.setBlococaminho(BlList.getBlococaminho());
		blList.setBlococodebloco(BlList.getBlococodebloco());
		blList.setBloconomebloco(BlList.getBloconomebloco());
		blList.setBlocosecao(BlList.getBlocosecao());
		blList.setBlocosubsecao(BlList.getBlocosubsecao());
		blocoRepository.save(blList);
	}
	
	// UPDATE
	@PutMapping("/{id}")
	public void updateBloco(@PathVariable Long id, @RequestBody BlocoRq CdList) throws Exception
	{
		var blList = blocoRepository.findById(id);
		
		if(blList.isPresent())
		{
			var blList2 = blList.get();
			blList2.setBlococaminho(CdList.getBlococaminho());
			blList2.setBlococodebloco(CdList.getBlococodebloco());
			blList2.setBloconomebloco(CdList.getBloconomebloco());
			blList2.setBlocosecao(CdList.getBlocosecao());
			blList2.setBlocosubsecao(CdList.getBlocosubsecao());
			blocoRepository.save(blList2);
		}
		else { throw new Exception("Bloco não encontrado"); }
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteBloco(@PathVariable Long id)
	{	
		blocoRepository.deleteById(id);
	}
	
}
