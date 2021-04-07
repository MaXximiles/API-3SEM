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

import com.grupo2.API_TraceFinder.classes.ArquivoPagina;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoPaginaRq;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoPaginaRs;
import com.grupo2.API_TraceFinder.repository.ArquivoPaginaRepository;



@RestController
@RequestMapping("/arquivos_paginas")
public class ArquivoPaginaController {

	private ArquivoPaginaRepository arquivoPaginaRepository = null;
	
	public ArquivoPaginaController(ArquivoPaginaRepository arquivoPaginaRepository) {
		this.arquivoPaginaRepository = arquivoPaginaRepository;
	}
	
	
	// SELECT de todos//
	@GetMapping("/")
	public List<ArquivoPaginaRs> selectAll()
	{
		var arqPag = arquivoPaginaRepository.findAll();
		return arqPag.stream().map((ArqPag) -> ArquivoPaginaRs.converter(ArqPag)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public ArquivoPaginaRs selectID(@PathVariable("id") Long id)
	{
		var doc = arquivoPaginaRepository.getOne(id);
		return ArquivoPaginaRs.converter(doc);
	}
	
	// INSERT //
	@PostMapping("/")
	public void insertArquivoPagina(@RequestBody ArquivoPaginaRq ArqPag)
	{
		var arqPag = new ArquivoPagina();
		
		arqPag.setArquivo_pagina_id(ArqPag.getArquivo_pagina_id());
		arqPag.setArquivo_id(ArqPag.getArquivo_id());
		arqPag.setArquivo_pagina_data_modificacao(ArqPag.getArquivo_pagina_data_modificacao());
		arqPag.setArquivo_pagina_modificacao(ArqPag.getArquivo_pagina_modificacao());
		arqPag.setArquivo_pagina_pagina(ArqPag.getArquivo_pagina_pagina());
		arqPag.setArquivo_pagina_revisao(ArqPag.getArquivo_pagina_revisao());
		arquivoPaginaRepository.save(arqPag);
	}
	
	// UPDATE
	@PutMapping("/{id}")
	public void updateArquivoPagona(@PathVariable Long id, @RequestBody ArquivoPaginaRq ArqPag) throws Exception
	{
		var arqPag = arquivoPaginaRepository.findById(id);
		
		if(arqPag.isPresent())
		{
			var arqPag2 = arqPag.get();
			
			arqPag2.setArquivo_pagina_id(ArqPag.getArquivo_pagina_id());
			arqPag2.setArquivo_id(ArqPag.getArquivo_id());
			arqPag2.setArquivo_pagina_data_modificacao(ArqPag.getArquivo_pagina_data_modificacao());
			arqPag2.setArquivo_pagina_modificacao(ArqPag.getArquivo_pagina_modificacao());
			arqPag2.setArquivo_pagina_pagina(ArqPag.getArquivo_pagina_pagina());
			arqPag2.setArquivo_pagina_revisao(ArqPag.getArquivo_pagina_revisao());
			arquivoPaginaRepository.save(arqPag2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteArquivoPagina(@PathVariable Long id)
	{	
		arquivoPaginaRepository.deleteById(id);
	}
	
	
}
