package br.com.pacotePrincipal.controler;

import java.util.List;

import br.com.pacotePrincipal.entidade.Administrador;
import br.com.pacotePrincipal.entidade.Aluno;
import br.com.pacotePrincipal.entidade.Professor;
import br.com.pacotePrincipal.entidade.Role;
import br.com.pacotePrincipal.entidade.Usuario;

/**
 * Responsalvel pelo as Alteraçoes de Usuario
 * @author Adriano
 *
 */
public interface IUsuarioControler {
	
	/**
	 * Insere Um {@link Administrador} 	<b>OU</b><p>
	 * insere Um {@link Professor}<b>OU</b><p>
	 * insere Um {@link Aluno} <b>OU</b><p>
	 * @param administrador
	 * @param aluno
	 * @param professor
	 */
	void inserir(Administrador administrador, Aluno aluno, Professor professor);
	
	/**
	 * Delete o Usuario Pelo id
	 * @param id
	 */
	void deletar(Long id);
	
	
	/**
	 * Pesquisa pelo email
	 * @param email
	 * @return Usuario
	 */
	Object findbyEmail(String email);
	
	
	/***
	 * Metodo responsavel por procurar o usuario para autenticação
	 * @param email
	 * @return
	 */
	Object procurarEmail(String email);
	
	
	
	/**
	 *Pesquisa pelo id 
	 * @param id
	 * @return
	 */
	Usuario findbyId(int id);
	
	/**
	 * Pesquisa pela tarefa
	 * @param role
	 * @return
	 */
	Usuario findbyRole(Role role);
	
	/**
	 * Pesquisa Todos os Usuario
	 * @return
	 */
	List<Usuario> findAll();
	
	
}
