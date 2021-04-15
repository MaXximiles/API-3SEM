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

import com.grupo2.API_TraceFinder.classes.Arquivo;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRq;
import com.grupo2.API_TraceFinder.controller.dto.ArquivoRs;
import com.grupo2.API_TraceFinder.repository.ArquivoRepository;


@RestController
@RequestMapping("/arquivos")
public class ArquivoController {
	
	private ArquivoRepository arquivoRepository = null;
	
	public ArquivoController(ArquivoRepository ArquivoRepository) {	this.arquivoRepository = ArquivoRepository;	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<ArquivoRs> selectAll()
	{
		var arquivos = arquivoRepository.findAll();
		return arquivos.stream().map((arq) -> ArquivoRs.converter(arq)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public ArquivoRs selectID(@PathVariable("id") Long id)
	{
		var doc = arquivoRepository.getOne(id);	
		return ArquivoRs.converter(doc);
	}
	
	// INSERT //   
	@PostMapping("/")
	public void insertArquivo(@RequestBody ArquivoRq arquivo)
	{
		var arq = new Arquivo();
		arq.setArquivonome(arquivo.getArquivonome());
		arq.setCodelistid(arquivo.getCodelistid());
		arquivoRepository.save(arq);
	}
		
	// UPDATE
	@PutMapping("/{id}")
	public void updateArquivo(@PathVariable Long id, @RequestBody ArquivoRq arquivo) throws Exception
	{
		var arq = arquivoRepository.findById(id);
		
		if(arq.isPresent())
		{
			var arq2 = arq.get();
			arq2.setArquivonome(arquivo.getArquivonome());
			arq2.setCodelistid(arquivo.getCodelistid());
			arquivoRepository.save(arq2);
			
		}
		else { throw new Exception("Documento não encontrado"); }
	}
		
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteArquivo(@PathVariable Long id)
	{	
		arquivoRepository.deleteById(id);
	}
}
