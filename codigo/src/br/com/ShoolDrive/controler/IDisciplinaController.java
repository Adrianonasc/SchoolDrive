package br.com.ShoolDrive.controler;

import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.exception.RNException;


/**
 * 
 * 17/12/2014
 * @author Adriano 
 * Classe Responsavel por RN Disciplina
 */
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
