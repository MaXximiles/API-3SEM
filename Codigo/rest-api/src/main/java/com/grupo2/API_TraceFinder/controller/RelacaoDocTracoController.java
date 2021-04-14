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

import com.grupo2.API_TraceFinder.classes.RelacaoDocTraco;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoDocTracoRq;
import com.grupo2.API_TraceFinder.controller.dto.RelacaoDocTracoRs;
import com.grupo2.API_TraceFinder.repository.RelacaoDocTracoRepository;

@RestController
@RequestMapping("/relacao_doc_traco")
public class RelacaoDocTracoController {
	
	private RelacaoDocTracoRepository RelacaoDocTracoRepository = null;
	
	public RelacaoDocTracoController(RelacaoDocTracoRepository relacaoDocTracoRepository) {
		this.RelacaoDocTracoRepository = relacaoDocTracoRepository;
	}
	
	// SELECT de todos//
	@GetMapping("/")
	public List<RelacaoDocTracoRs> selectAll()
	{
		var RelDocTraco = RelacaoDocTracoRepository.findAll();
		return RelDocTraco.stream().map((relDocTraco) -> RelacaoDocTracoRs.converter(relDocTraco)).collect(Collectors.toList());	
	}
		
	// SELECT por ID //
	@GetMapping("/{id}")
	public RelacaoDocTracoRs selectID(@PathVariable("id") Long id)
	{
		var relDocTraco = RelacaoDocTracoRepository.getOne(id);		
		return RelacaoDocTracoRs.converter(relDocTraco);
	}
		
	// INSERT //
	@PostMapping("/")
	public void insertRelacaoDocTraco(@RequestBody RelacaoDocTracoRq relDocTraco)
	{
		var rDocTraco = new RelacaoDocTraco();
		rDocTraco.setDocid(relDocTraco.getDocid());
		rDocTraco.setTracoid(relDocTraco.getTracoid());
		RelacaoDocTracoRepository.save(rDocTraco);
	}
		
	// UPDATE
	@PutMapping("/{id}")
	public void updateRelacaoDocTraco(@PathVariable Long id, @RequestBody RelacaoDocTracoRq relDocTraco) throws Exception
	{
		var rDocTraco = RelacaoDocTracoRepository.findById(id);
			
		if(rDocTraco.isPresent())
		{
			var rDocTraco2 = rDocTraco.get();
			rDocTraco2.setDocid(relDocTraco.getDocid());
			rDocTraco2.setTracoid(relDocTraco.getTracoid());
			RelacaoDocTracoRepository.save(rDocTraco2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
		
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteRelacaoDocTraco(@PathVariable Long id)
	{	
		RelacaoDocTracoRepository.deleteById(id);
	}
}
