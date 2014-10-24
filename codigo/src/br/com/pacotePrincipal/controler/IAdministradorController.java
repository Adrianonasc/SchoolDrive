package br.com.pacotePrincipal.controler;

import java.util.List;

import br.com.pacotePrincipal.entidade.Administrador;
import br.com.pacotePrincipal.entidade.Role;

/***
 * Interface ADMINISTRADOR Controler
 * @author Adriano
 *
 */
public interface IAdministradorController {

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
	
	/**
	 * Pesquisa Todos os Usuario
	 * @return
	 */
	List<Administrador> findAll();

	// Metodos Spring

	
	public void delete(Long id);

	
	public void save(Administrador admin);

}
