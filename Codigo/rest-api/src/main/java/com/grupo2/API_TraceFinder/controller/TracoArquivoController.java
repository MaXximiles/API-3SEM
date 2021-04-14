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

import com.grupo2.API_TraceFinder.classes.TracoArquivo;
import com.grupo2.API_TraceFinder.controller.dto.TracoArquivoRq;
import com.grupo2.API_TraceFinder.controller.dto.TracoArquivoRs;
import com.grupo2.API_TraceFinder.repository.TracoArquivoRepository;

@RestController
@RequestMapping("/traco_arquivo")
public class TracoArquivoController {
	
	private TracoArquivoRepository tracoArquivoRepository = null;
	
	public TracoArquivoController(TracoArquivoRepository TracoArquivoRepository) {	this.tracoArquivoRepository = TracoArquivoRepository;	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<TracoArquivoRs> selectAll()
	{
		var tArquivos = tracoArquivoRepository.findAll();
		return tArquivos.stream().map((tArq) -> TracoArquivoRs.converter(tArq)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public TracoArquivoRs selectID(@PathVariable("id") Long id)
	{
		var tArq = tracoArquivoRepository.getOne(id);	
		return TracoArquivoRs.converter(tArq);
	}
	
	// INSERT //
	@PostMapping("/")
	public void insertTracoArquivo(@RequestBody TracoArquivoRq tArquivo)
	{
		var tArq = new TracoArquivo();
		tArq.setTracoarquivonome(tArquivo.getTracoarquivonome());
		tArq.setTracoarquivodescricao(tArquivo.getTracoarquivodescricao());
		tArq.setTracoarquivocodigo(tArquivo.getTracoarquivocodigo());
		tracoArquivoRepository.save(tArq);
	}
		
	// UPDATE
	@PutMapping("/{id}")
	public void updateTracoArquivo(@PathVariable Long id, @RequestBody TracoArquivoRq tArquivo) throws Exception
	{
		var tArq = tracoArquivoRepository.findById(id);
		
		if(tArq.isPresent())
		{
			var tArq2 = tArq.get();
			tArq2.setTracoarquivonome(tArquivo.getTracoarquivonome());
			tArq2.setTracoarquivodescricao(tArquivo.getTracoarquivodescricao());
			tArq2.setTracoarquivocodigo(tArquivo.getTracoarquivocodigo());
			tracoArquivoRepository.save(tArq2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
		
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteTracoArquivo(@PathVariable Long id)
	{	
		tracoArquivoRepository.deleteById(id);
	}

}
