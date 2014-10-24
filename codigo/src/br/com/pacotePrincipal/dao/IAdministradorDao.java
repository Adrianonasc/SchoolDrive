package br.com.pacotePrincipal.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.pacotePrincipal.entidade.Administrador;
import br.com.pacotePrincipal.entidade.Role;

/***
 * Interface DAO ADMINISTRADOR
 * @author Adriano
 *
 */
public interface IAdministradorDao extends CrudRepository<Administrador, Long> {

	/**
	 * Pesquisa pelo email
	 * 
	 * @param email
	 * @return Usuario
	 */
	Administrador findByEmail(String email);

	/**
	 * Pesquisa pelo id
	 * 
	 * @param id
	 * @return
	 */
	Administrador findById(int id);

	/**
	 * Pesquisa pela tarefa
	 * 
	 * @param role
	 * @return
	 */
	Administrador findByRole(Role role);

	// Metodos Spring
	
	
	@Override
	public void delete(Long arg0);

	@Override
	public <S extends Administrador> S save(S arg0);

}
