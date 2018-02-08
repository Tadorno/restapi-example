package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.model.Exemplo;
import com.example.repository.ExemploRepository;

@Service
public class ExemploService extends GenericService<Exemplo, Long>{
	
	@Autowired
	private ExemploRepository exemploRepository;

	protected  JpaRepository<Exemplo, Long> getRepository(){
		return exemploRepository;
	}
	
}
