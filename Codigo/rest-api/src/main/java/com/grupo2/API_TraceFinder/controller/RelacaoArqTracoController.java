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


import com.grupo2.API_TraceFinder.classes.RelacaoArqTraco;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoArqTracoRq;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoArqTracoRs;
import com.grupo2.API_TraceFinder.repository.RelacaoArqTracoRepository;


@RestController
@RequestMapping("/relacao_arquivo_traco")
public class RelacaoArqTracoController {
	
	private RelacaoArqTracoRepository RelacaoArqTracoRepository = null;
	
	public RelacaoArqTracoController(RelacaoArqTracoRepository relacaoArqTracoRepository) {
		this.RelacaoArqTracoRepository = relacaoArqTracoRepository;
	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<RelacaoArqTracoRs> selectAll()
	{
		var RelArqTraco = RelacaoArqTracoRepository.findAll();
		return RelArqTraco.stream().map((relArqTraco) -> RelacaoArqTracoRs.converter(relArqTraco)).collect(Collectors.toList());	
	}
		
	// SELECT por ID //
	@GetMapping("/{id}")
	public RelacaoArqTracoRs selectID(@PathVariable("id") Long id)
	{
		var relArqTraco = RelacaoArqTracoRepository.getOne(id);		
		return RelacaoArqTracoRs.converter(relArqTraco);
	}
		
	// INSERT //
	@PostMapping("/")
	public void insertRelacaoArqTraco(@RequestBody RelacaoArqTracoRq relArqTraco)
	{
		var rArqTraco = new RelacaoArqTraco();
		rArqTraco.setArquivoid(relArqTraco.getArquivoid());
		rArqTraco.setTracoid(relArqTraco.getTracoid());
		RelacaoArqTracoRepository.save(rArqTraco);
	}
		
	// UPDATE
	@PutMapping("/{id}")
	public void updateRelacaoArqTraco(@PathVariable Long id, @RequestBody RelacaoArqTracoRq relArqTraco) throws Exception
	{
		var rArqTraco = RelacaoArqTracoRepository.findById(id);
			
		if(rArqTraco.isPresent())
		{
			var rArqTraco2 = rArqTraco.get();
			rArqTraco2.setArquivoid(relArqTraco.getArquivoid());
			rArqTraco2.setTracoid(relArqTraco.getTracoid());
			RelacaoArqTracoRepository.save(rArqTraco2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
		
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteRelacaoArqTraco(@PathVariable Long id)
	{	
		RelacaoArqTracoRepository.deleteById(id);
	}
	

}
