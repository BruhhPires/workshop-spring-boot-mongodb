package com.brunopires.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunopires.workshopmongo.domain.User;
import com.brunopires.workshopmongo.dto.UserDTO;
import com.brunopires.workshopmongo.repository.UserRepository;
import com.brunopires.workshopmongo.services.exception.ObjectNotFoundExceptions;

@Service
public class UserService {
	
	@Autowired								// O SERVIÇO IMPLEMENTA O REPOSITORIO
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id){	    // RETONAR O USUARIO POR ID COM BASE NO ID RECEBIDO E SE NÃO EXISTIR, O USER VAI SER NULO E A LAMBDA TRATA A EXCEÇÃO
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));		
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
		
	}
	
	public void delete (String id) {  // IMPLEMENTAÇÃO DELETE NO SERVICE
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {     // PEGA UM DTOE TRANSFORMA EM USER
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
