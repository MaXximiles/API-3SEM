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

import com.grupo2.API_TraceFinder.classes.Tag;
import com.grupo2.API_TraceFinder.classes.TagBloco;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRq;
import com.grupo2.API_TraceFinder.controller.dto.TagBlocoRs;
import com.grupo2.API_TraceFinder.controller.dto.TagRq;
import com.grupo2.API_TraceFinder.controller.dto.TagRs;
import com.grupo2.API_TraceFinder.repository.TagRepository;

@RestController
@RequestMapping("/tag")
public class TagController {
	
	private TagRepository tagRepository = null;
	
	public TagController(TagRepository TagRepository) {
		
		this.tagRepository = TagRepository;
	
	}

	// SELECT de todos//
	@GetMapping("/")
	public List<TagRs> selectAll()
	{
		var tag = tagRepository.findAll();
		return tag.stream().map((tg) -> TagRs.converter(tg)).collect(Collectors.toList());	
	}
	
	// SELECT por ID //
	@GetMapping("/{id}")
	public TagRs selectID(@PathVariable("id") Long id)
	{
		var tag = tagRepository.getOne(id);	
		return TagRs.converter(tag);
	}
	
	// INSERT //   
	@PostMapping("/")
	public void insertTag(@RequestBody TagRq tag, String tagNome)
	{
		var tag1 = new Tag();
		tag1.setTagNome(tagNome);
		tagRepository.save(tag1);
	}
	
	// UPDATE
	@PutMapping("/{id}")
	public void updateTag(@PathVariable Long id, @RequestBody TagRq tag) throws Exception
	{
		var tag1 = tagRepository.findById(id);
		
		if(tag1.isPresent())
		{
			var tag3 = tag1.get();
			tag3.setTagNome(tag.getTagNome());
			tagRepository.save(tag3);
			
		}
		else { throw new Exception("Documento não encontrado"); }
	}
	
	// DELETE
	@DeleteMapping("/{tagid}")
	public void deleteTagBloco(@PathVariable Long tagid)
	{			 
	 	 tagRepository.deleteById(tagid);
	}
	
}
