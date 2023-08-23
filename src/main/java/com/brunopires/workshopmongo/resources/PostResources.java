package com.brunopires.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brunopires.workshopmongo.domain.Post;
import com.brunopires.workshopmongo.resources.util.URL;
import com.brunopires.workshopmongo.services.PostService;
//--> OS ENDPOINTS SÃO RESPONSAVEIS PELA REQUISIÇÃO NO HTTP, POR EX: /USERS /ID /PUT /GET ETC... <--//
@RestController
@RequestMapping(value="/posts")
public class PostResources {
	
	@Autowired                      // O CONTROLADOR REST IMPLEMENTA O SERVIÇO
	private PostService service;

	
	@GetMapping(value = "/{id}")                 // METODO DE LOCALIZAR POR ID
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/titlesearch")                 // METODO DE LOCALIZAR POR SEARCH"TEXT"
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
}
