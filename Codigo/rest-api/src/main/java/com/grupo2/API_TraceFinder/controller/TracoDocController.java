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

import com.grupo2.API_TraceFinder.classes.TracoDoc;
import com.grupo2.API_TraceFinder.controller.dto.TracoDocRq;
import com.grupo2.API_TraceFinder.controller.dto.TracoDocRs;
import com.grupo2.API_TraceFinder.repository.TracoDocRepository;

@RestController
@RequestMapping("/traco_doc")
public class TracoDocController {

	private TracoDocRepository tracoDocRepository = null;
	
	public TracoDocController(TracoDocRepository TracoDocRepository) {	this.tracoDocRepository = TracoDocRepository;	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<TracoDocRs> selectAll()
	{
		var tDocumento = tracoDocRepository.findAll();
		return tDocumento.stream().map((tDoc) -> TracoDocRs.converter(tDoc)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public TracoDocRs selectID(@PathVariable("id") Long id)
	{
		var tDoc = tracoDocRepository.getOne(id);	
		return TracoDocRs.converter(tDoc);
	}
	
	// INSERT //
	@PostMapping("/")
	public void insertTracoDoc(@RequestBody TracoDocRq tDocumento)
	{
		var tDoc = new TracoDoc();
		tDoc.setTracodocnome(tDocumento.getTracodocnome());
		tDoc.setTracodocdescricao(tDocumento.getTracodocdescricao());
		tDoc.setTracodoccodigo(tDocumento.getTracodoccodigo());
		tracoDocRepository.save(tDoc);
	}
		
	// UPDATE
	@PutMapping("/{id}")
	public void updateTracoDoc(@PathVariable Long id, @RequestBody TracoDocRq tDocumento) throws Exception
	{
		var tDoc = tracoDocRepository.findById(id);
		
		if(tDoc.isPresent())
		{
			var tDoc2 = tDoc.get();
			tDoc2.setTracodocnome(tDocumento.getTracodocnome());
			tDoc2.setTracodocdescricao(tDocumento.getTracodocdescricao());
			tDoc2.setTracodoccodigo(tDocumento.getTracodoccodigo());
			tracoDocRepository.save(tDoc2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
		
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteTracoDoc(@PathVariable Long id)
	{	
		tracoDocRepository.deleteById(id);
	}
	
}
