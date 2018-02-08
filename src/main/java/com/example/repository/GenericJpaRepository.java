package com.example.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface genérica responsável por modificar o comportamento do JpaRepository 
 * para um contexto de exclusão lógica
 * 
 * @author tadorno
 *
 * @param <T> Entidade
 * @param <ID> Id da Entidade
 */
@NoRepositoryBean
public interface GenericJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>{
	
	@Override
	@Query("SELECT e FROM #{#entityName} e WHERE e.excluido=false")
	public List<T> findAll();
	
	@Override
	@Query("SELECT e FROM #{#entityName} e WHERE e.excluido=false AND e.id=?1")
	public T findOne(ID id);
	
	@Override
	@Query("SELECT count(e)>0 FROM #{#entityName} e WHERE e.excluido=false AND e.id=?1")
	public boolean exists(ID id);
	
	@Override
	@Transactional
	@Modifying
	@Query("UPDATE #{#entityName} e SET e.excluido=true, e.dataExclusao = CURRENT_TIMESTAMP WHERE e.id=?1")
	public void delete(ID id);
	
	@Override
	@Query("SELECT count(e) FROM #{#entityName} e WHERE e.excluido=false")
	public long count();
}
