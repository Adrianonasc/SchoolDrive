package br.com.pacotePrincipal.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pacotePrincipal.entidade.Disciplina;

@Repository
public interface IDisciplinaDao extends CrudRepository<Disciplina, Long> {

	@Override
	public void delete(Long id);
	
	@Override
	public Iterable<Disciplina> findAll();
	
	@Override
	public Disciplina findOne(Long id);
	
	@Override
	public <S extends Disciplina> S save(S disciplina);
	
	
}
