package br.com.ShoolDrive.controler;

import java.util.List;

import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.entidade.Role;
import br.com.ShoolDrive.exception.RNException;

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
	Professor findById(Long id);

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
	List<Professor> findAll()throws RNException ;
	// Metodos Spring

	
	public void delete(Long id);
	
	
	public void save(Professor professor);
}
