package com.brunopires.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunopires.workshopmongo.domain.Post;
import com.brunopires.workshopmongo.domain.User;
import com.brunopires.workshopmongo.dto.UserDTO;
import com.brunopires.workshopmongo.services.UserService;
//--> OS ENDPOINTS SÃO RESPONSAVEIS PELA REQUISIÇÃO NO HTTP, POR EX: /USERS /ID /PUT /GET ETC... <--//
@RestController
@RequestMapping(value="/users")
public class UserResources {
	
	@Autowired                      // O CONTROLADOR REST IMPLEMENTA O SERVIÇO
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); // MAPEIA A STREAM LIST E TRANSFORMA EM USERDTO COMO LISTA
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")                 // METODO DE LOCALIZAR POR ID
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping                               					  // METODO DE POSTAR(INSERIR)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{i}").buildAndExpand(obj.getId()).toUri(); // PEGA O ENDEREÇO DO NOVO OBJETO QUE FOI INSERIDO
		return ResponseEntity.created(uri).build();              // O CREATED RETORNAR O CODIGO 201(QUE É O PADRÃO QUANDO CRIA) COM O CABEÇALHO CONTENDO A LOCALIZAÇÃO
	}

	@DeleteMapping(value = "/{id}")                               // METODO DE DELETAR
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();                // DELETA E LANÇA O CODIGO 204(QUE É O PADRÃO PRA DELETE) 
	}
	
	@PutMapping(value = "/{id}")                                  // METODO DE ATUALIZAR USUARIO(UPDATE)
	public ResponseEntity<Void> uptdate(@PathVariable String id, @RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();                // DELETA E LANÇA O CODIGO 204(QUE É O PADRÃO PRA DELETE) 
	}
	
	@GetMapping(value = "/{id}/posts")                 // METODO DE LOCALIZAR POSTS POR ID
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
}
