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
		
		arqPag.setArquivopaginaid(ArqPag.getArquivopaginaid());
		arqPag.setArquivoid(ArqPag.getArquivoid());
		arqPag.setArquivopaginadatamodificacao(ArqPag.getArquivopaginadatamodificacao());
		arqPag.setArquivopaginamodificacao(ArqPag.getArquivopaginamodificacao());
		arqPag.setArquivopaginapagina(ArqPag.getArquivopaginapagina());
		arqPag.setArquivopaginarevisao(ArqPag.getArquivopaginarevisao());
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
			
			arqPag2.setArquivopaginaid(ArqPag.getArquivopaginaid());
			arqPag2.setArquivoid(ArqPag.getArquivoid());
			arqPag2.setArquivopaginadatamodificacao(ArqPag.getArquivopaginadatamodificacao());
			arqPag2.setArquivopaginamodificacao(ArqPag.getArquivopaginamodificacao());
			arqPag2.setArquivopaginapagina(ArqPag.getArquivopaginapagina());
			arqPag2.setArquivopaginarevisao(ArqPag.getArquivopaginarevisao());
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
