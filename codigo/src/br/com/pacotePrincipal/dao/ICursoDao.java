package br.com.pacotePrincipal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pacotePrincipal.entidade.Curso;

@Repository
public interface ICursoDao  extends CrudRepository<Curso, Long>{
	
	/**
	 * Pesquisa pelo id
	 * 
	 * @param id
	 * @return
	 */
	Curso findById(Long id);

	// Metodos Spring
	
	@Override
	public Iterable<Curso> findAll();
	
	@Override
	public <S extends Curso> S save(S curso);
	
	
	@Override
	public void delete(Long id);


}
