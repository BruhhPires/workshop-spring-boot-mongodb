package com.brunopires.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunopires.workshopmongo.domain.Post;
import com.brunopires.workshopmongo.repository.PostRepository;
import com.brunopires.workshopmongo.services.exception.ObjectNotFoundExceptions;

@Service
public class PostService {
	
	@Autowired								// O SERVIÇO IMPLEMENTA O REPOSITORIO
	private PostRepository repo;
	
		
	public Post findById(String id){	    // RETONAR O USUARIO POR ID COM BASE NO ID RECEBIDO E SE NÃO EXISTIR, O USER VAI SER NULO E A LAMBDA TRATA A EXCEÇÃO
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado"));		
	}
	
	public List<Post> findByTitle(String text){  // METODO DE BUSCA
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){  // METODO FULL DE BUSCA
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); // <---- ADCIONA 24 HORAS A DATA MAXIMA
		return repo.fullSearch(text, minDate, maxDate);
		
	}
}
