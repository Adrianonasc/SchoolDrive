package br.com.ShoolDrive.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ShoolDrive.entidade.Disciplina;

@Repository
public interface IDisciplinaDao extends CrudRepository<Disciplina, Long> {

	@Override
	void delete(Long id);
	
	
	Iterable<Disciplina> findAll();
	
	@Override
	Disciplina findOne(Long id);
	
	@Override
	 <S extends Disciplina> S save(S disciplina);
	
	@Override
	 long count();
	
	
	Iterable<Disciplina> findByProfessorId(Long professorId);
	
	
	
}
