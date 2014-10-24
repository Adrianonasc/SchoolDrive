package br.com.pacotePrincipal.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.pacotePrincipal.entidade.Aluno;
import br.com.pacotePrincipal.entidade.Role;

public interface IAlunoDao extends CrudRepository<Aluno, Long>{
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
		
	@Override
	public void delete(Long arg0);
	
	@Override
	public <S extends Aluno> S save(S arg0);

	
}
