package br.com.ShoolDrive.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.entidade.Role;
@Repository
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
	Professor findById(Long id);

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
	
	
	@Override
	public long count();
}
