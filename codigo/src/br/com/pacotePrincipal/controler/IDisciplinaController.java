package br.com.pacotePrincipal.controler;

import br.com.pacotePrincipal.entidade.Disciplina;

public interface IDisciplinaController {
	
	public void delete(Long id);
	
	
	public Iterable<Disciplina> findAll();
	
	
	public Disciplina findOne(Long id);
	
	
	public <S extends Disciplina> S save(S disciplina);
}
