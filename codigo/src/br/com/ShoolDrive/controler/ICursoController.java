package br.com.ShoolDrive.controler;

import br.com.ShoolDrive.entidade.Curso;
/**
 * 
 * 17/12/2014
 * @author Adriano 
 * Classe Responsavel por RN Curso
 */
public interface ICursoController  {
	
	/**
	 * Pesquisa pelo id
	 * 
	 * @param id
	 * @return
	 */
	Curso findById(Long id);

	
	// Metodos Spring
	
	public Iterable<Curso> findAll();
	
	public <S extends Curso> S save(S curso);
	
	
	public void delete(Long arg0);


}
