package com.example.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.MensagemException;
import com.example.model.Exemplo;
import com.example.service.ExemploService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "UC-001" })
public class ExemploController {

	@Autowired
	private ExemploService exemploService;

	@GetMapping("/api/exemplos/{id}")
	@ApiOperation(value = "Buscar Exemplos por id", response = Exemplo.class, notes = "Busca um Exemplo a partir de uma id")
	public Exemplo findOne(@PathVariable(name = "id") Long id) {
		return exemploService.findOne(id);
	}

	@GetMapping("/api/exemplos")
	@ApiOperation(value = "Listar Exemplos", response = Exemplo.class, responseContainer = "List", notes = "Lista todos os Exemplos do BD")
	public List<Exemplo> findAll() {
		return exemploService.findAll();
	}

	@PostMapping("/api/exemplos")
	@ApiOperation(value = "Cadastrar/Alterar Exemplo", notes = "Cadastra um novo Exemplo ou altera um Exemplo existente")
	public void save(@RequestBody Exemplo exemplo, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			exemploService.save(exemplo);
		} catch (MensagemException e) {
			response.sendError(422, e.getMessage());

		}
	}

	@DeleteMapping("/api/exemplos/{id}")
	@ApiOperation(value = "Remover Exemplo", notes = "Remove um novo Exemplo")
	public void delete(@PathVariable(name = "id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			exemploService.delete(id);
		} catch (MensagemException e) {
			response.sendError(422, e.getMessage());

		}
	}

}
