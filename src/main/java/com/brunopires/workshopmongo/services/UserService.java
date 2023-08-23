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
	
	public void delete(String id) {  // IMPLEMENTAÇÃO DELETE NO SERVICE
		repo.deleteById(id);
	}
	
	public User update(User obj) {  						// IMPLEMENTAÇÃO UPTADE NO SERVICE
		User newObj = findById(obj.getId());			    // INSTANCIAREMOS UM NEW OBJ PEGANDO OS DADOS DE UM OBJ ATRAVEZ DO GETID
		updateData(newObj, obj);  							// O METEDO UPDATEDATE VAI PEGAR O OBJ E LANÇAR PARA O NEWOBJ
		return repo.save(newObj);
		
	}
	
	private void updateData(User newObj, User obj) {       // METODO UPDATEDATE PEGA OS DADOS DO OBJ E LANÇA PRO NEW OBJ
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {     // PEGA UM DTOE TRANSFORMA EM USER
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
