package com.grupo2.API_TraceFinder.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.API_TraceFinder.classes.Documento;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRq;
import com.grupo2.API_TraceFinder.controller.dto.DocumentoRs;
import com.grupo2.API_TraceFinder.repository.documentoRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documentos")
public class DocumentoController 
{
	
	private documentoRepository DocumentoRepository = null;
	
	public DocumentoController(documentoRepository DocumentoRepository) {
		this.DocumentoRepository = DocumentoRepository;
	}
	
	
	// SELECT de todos//
	@GetMapping("/")
	public List<DocumentoRs> selectAll()
	{
		var documentos = DocumentoRepository.findAll();
		return documentos.stream().map((doc) -> DocumentoRs.converter(doc)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public DocumentoRs selectID(@PathVariable("id") Long id)
	{
		var doc = DocumentoRepository.getOne(id);
		
		return DocumentoRs.converter(doc);
	}
	
	// INSERT //
	@PostMapping("/")
	public void insertDocumento(@RequestBody DocumentoRq documento)
	{
		var doc = new Documento();
		
		doc.setDocumento_nome(documento.getDocumento_nome());
		doc.setDocumento_pn(documento.getDocumento_pn());
		doc.setDocumento_caminho(documento.getDocumento_caminho());
		DocumentoRepository.save(doc);
	}
	
	// UPDATE
	@PutMapping("/{id}")
	public void updateDocumento(@PathVariable Long id, @RequestBody DocumentoRq documento) throws Exception
	{
		var doc = DocumentoRepository.findById(id);
		
		if(doc.isPresent())
		{
			var doc2 = doc.get();
			doc2.setDocumento_nome(documento.getDocumento_nome());
			doc2.setDocumento_pn(documento.getDocumento_pn());
			doc2.setDocumento_caminho(documento.getDocumento_caminho());
			DocumentoRepository.save(doc2);
		}
		else { throw new Exception("Documento não encontrado"); }
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public void deleteDocumento(@PathVariable Long id)
	{	
		DocumentoRepository.deleteById(id);
	}
	
}
