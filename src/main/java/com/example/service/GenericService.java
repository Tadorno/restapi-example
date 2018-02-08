package com.example.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aspecto.ObjetoEntidade;
import com.example.exception.MensagemException;

/**
 * Versão Genérica para os Services.
 * 
 * @author tadorno
 * 
 * @param <T>entidade genérica
 * 
 * @param <ID>id da entidade
 */
public abstract class GenericService<T extends ObjetoEntidade<ID>, ID extends Serializable> {

	protected abstract JpaRepository<T, ID> getRepository();

	public long count() {
		return this.getRepository().count();
	}

	public void delete(ID id) throws MensagemException {
		if (this.exists(id)) {
			this.getRepository().delete(id);
		} else {
			throw new MensagemException("Entidade inexistente");
		}
	}

	public boolean exists(ID id) {
		return this.getRepository().exists(id);
	}

	public List<T> findAll() {
		return this.getRepository().findAll();
	}

	public T findOne(ID id) {
		return this.getRepository().findOne(id);
	}

	public void save(T entidade) throws MensagemException {
		if (entidade.getId() != null && !this.exists(entidade.getId())) {
			throw new MensagemException("Entidade Inexistente");
		}
		this.getRepository().save(entidade);
	}

}
