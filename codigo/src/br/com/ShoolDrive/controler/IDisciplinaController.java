package br.com.ShoolDrive.controler;

import java.util.List;

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
	public Iterable<Disciplina> listaDisciplina() throws RNException;


	/**
	 * 
	 * @return Lista de Disciplina
	 * Metodo Resposavel por Obtem todas as diciplinas que o Professor esta Alocado
	 */
	public Iterable<Disciplina> DisciplinasProfessor(Long idProfesor);

	/**
	 * @param emailAluno
	 * @param CursoId
	 * @return
	 * Metodo Resposavel por retornar todas as disciplinas daquele curso que o aluno não ja esteja registrado
	 */
	public List<Disciplina> DisciplinasCurso(Long cursoId, String emailAluno);

	/**
	 * 
	 * @return
	 * Metodo Resposavel por retornar todas as disciplinas que o Aluno Estaja registrado

	public List<Disciplina> disciplinasResistradas(String );*/


}
