package br.com.pacotePrincipal.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.pacotePrincipal.entidade.Professor;
import br.com.pacotePrincipal.entidade.Role;

public interface IProfessorDao extends CrudRepository<Professor, Long> {
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

	// Metodos Spring


	@Override
	public void delete(Long arg0);

	@Override
	public <S extends Professor> S save(S arg0);
}
