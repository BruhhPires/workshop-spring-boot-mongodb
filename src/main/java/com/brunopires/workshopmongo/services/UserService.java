package com.brunopires.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunopires.workshopmongo.domain.User;
import com.brunopires.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired								// O SERVIÇO IMPLEMENTA O REPOSITORIO
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	

}
