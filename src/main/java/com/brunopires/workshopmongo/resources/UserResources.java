package com.brunopires.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunopires.workshopmongo.domain.User;
import com.brunopires.workshopmongo.dto.UserDTO;
import com.brunopires.workshopmongo.services.UserService;

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

}
