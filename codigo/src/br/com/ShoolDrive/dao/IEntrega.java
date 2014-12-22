package br.com.ShoolDrive.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;

public interface IEntrega extends CrudRepository<Entrega, Long> {
	
	@Override
	public void delete(Entrega arg0);
	
	@Override
	public Iterable<Entrega> findAll();
	
	@Override
	public <S extends Entrega> S save(S arg0);
	
	
	/**
	 * @param trabalho
	 * @return List<Entrega>
	 * Metodo Resposavel por retornar todas as entregas do trabalho
	 */
	List<Entrega> findByTrabalho(Trabalho trabalho);
	
	

}
