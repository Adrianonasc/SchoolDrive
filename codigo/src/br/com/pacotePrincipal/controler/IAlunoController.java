package br.com.pacotePrincipal.controler;

import java.util.List;

import br.com.pacotePrincipal.entidade.Aluno;
import br.com.pacotePrincipal.entidade.Role;
import br.com.pacotePrincipal.entidade.Usuario;

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
