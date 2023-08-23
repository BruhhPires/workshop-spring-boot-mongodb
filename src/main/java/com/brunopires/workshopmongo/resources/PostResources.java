package com.brunopires.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunopires.workshopmongo.domain.Post;
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
	
	
}
