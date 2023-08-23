package com.brunopires.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.brunopires.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")     // QUERRY DE BUSCA DO MONGO DB -- 1 - BUSCA O 'TITLE', ' ?0 = 1 ATRIBUTO, 'I' = ignora case
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text); // LOCALIZA O VALOR IGNORANDO O CASE
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
