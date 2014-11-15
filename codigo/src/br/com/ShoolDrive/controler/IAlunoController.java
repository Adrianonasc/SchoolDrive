package br.com.ShoolDrive.controler;

import java.util.List;

import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Role;
import br.com.ShoolDrive.entidade.Usuario;

public interface IAlunoController{
	/**
	 * Pesquisa pelo email
	 * 
	 * @param email
	 * @return Usuario
	 */
	Aluno findByEmail(String email);

	/**
	 * Pesquisa pelo id
	 * 
	 * @param id
	 * @return
	 */
	Aluno findById(int id);

	/**
	 * Pesquisa pela role
	 * 
	 * @param role
	 * @return
	 */
	Aluno findByRole(Role role);

	// Metodos Spring
	
	/**
	 * Pesquisa Todos os Usuario
	 * @return
	 */
	List<Aluno> findAll();

	
	public void delete(Long id);
	
	
	public void save(Aluno arg0);

	
}
