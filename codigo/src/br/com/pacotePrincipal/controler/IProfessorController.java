package br.com.pacotePrincipal.controler;

import java.util.List;

import br.com.pacotePrincipal.entidade.Professor;
import br.com.pacotePrincipal.entidade.Role;
import br.com.pacotePrincipal.entidade.Usuario;

public interface IProfessorController{
	/**
	 * Pesquisa pelo email
	 * 
	 * @param email
	 * @return Usuario
	 */
	Professor findByEmail(String email);

	/**
	 * Pesquisa pelo id
	 * 
	 * @param id
	 * @return
	 */
	Professor findById(int id);

	/**
	 * Pesquisa pela role
	 * 
	 * @param role
	 * @return
	 */
	Professor findByRole(Role role);

	
	/**
	 * Pesquisa Todos os Usuario
	 * @return
	 */
	List<Professor> findAll();
	// Metodos Spring

	
	public void delete(Long arg0);
	
	
	public void save(Professor arg0);
}
