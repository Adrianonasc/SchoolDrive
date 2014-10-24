package br.com.pacotePrincipal.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.pacotePrincipal.entidade.Role;
import br.com.pacotePrincipal.entidade.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	/**
	 * Pesquisa pelo email
	 * 
	 * @param email
	 * @return Usuario
	 */
	Usuario findByEmail(String email);

	/**
	 * Pesquisa pelo id
	 * 
	 * @param id
	 * @return
	 */
	Usuario findById(int id);

	/**
	 * Pesquisa pela tarefa
	 * 
	 * @param role
	 * @return
	 */
	Usuario findByRole(Role role);



	@Override
	public void delete(Long arg0);

	@Override
	public <S extends Usuario> S save(S arg0);

}
