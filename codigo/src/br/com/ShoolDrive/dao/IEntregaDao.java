package br.com.ShoolDrive.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;

public interface IEntregaDao extends CrudRepository<Entrega, Long> {

	@Override
	public void delete(Entrega entrega);

	@Override
	public Iterable<Entrega> findAll();

	@Override
	public <S extends Entrega> S save(S entrega);


	/**
	 * @param trabalho
	 * @return List<Entrega>
	 * Metodo Resposavel por retornar todas as entregas do trabalho
	 */
	List<Entrega> findByTrabalho(Trabalho trabalho);

	/**
	 * 
	 * @param aluno
	 * @param trabalho
	 * @return
	 * Metodo Resposavel pela as pesquisa de todos os alunos
	 */
	public Entrega findByAlunoAndTrabalho(Aluno aluno, Trabalho trabalho);


}
