package br.com.pacotePrincipal.controler;

import br.com.pacotePrincipal.entidade.Curso;

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
