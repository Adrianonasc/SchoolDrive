package br.com.ShoolDrive.controler;

import java.util.List;

import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.exception.RNException;

public interface IDisciplinaController {
	
	public void delete(Long id);
	
	
	public Iterable<Disciplina> findAll();
	
	
	public Disciplina findOne(Long id);
	
	
	public <S extends Disciplina> S save(S disciplina);
	
	/**
	 * Recupera as disciplinas sem professor alocado
	 * @return
	 */
	public Iterable<Disciplina> listaDisciplina()throws RNException;
	
}
