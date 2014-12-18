package br.com.ShoolDrive.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.ShoolDrive.entidade.Administrador;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.entidade.Role;
/**
 * 
 * @author Adriano
 * Classe Responsavel Pela Parte de Autenticação e premição do Usuario
 */
@Repository
public class AutenticacaoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Object getbyEmail(String email) {
		Object userObject;
		userObject = this.getAlunodb(email);
		if (userObject == null) {
			userObject = this.getAdmindb(email);
		}
		if (userObject == null) {
			userObject = this.getProfessordb(email);
		}

		return userObject;
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 * Metodo Resposavel por recuperar o aluno 
	 */
	private Object getAlunodb(String email) {
		try {
			return (Aluno) this.jdbcTemplate.queryForObject("select * from aluno where email = ?",new Object[]{email},
					new RowMapper<Aluno>() {
						@Override
						public Aluno mapRow(ResultSet rs, int arg1) throws SQLException {
							Aluno usuarioAluno = new Aluno();
							usuarioAluno.setId(rs.getLong("id"));
							usuarioAluno.setEmail(rs.getString("email"));
							usuarioAluno.setNome(rs.getString("nome"));
							usuarioAluno.setRole(AutenticacaoDao.this.getRole(rs.getString("user_role")));
							usuarioAluno.setSenha(rs.getString("senha"));
							return usuarioAluno;

						}
					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 
	 * @param email
	 * @return
	 * Metodo Resposavel por recuperar o Admin 
	 */
	private Object getAdmindb(String email) {

		try {
			return (Administrador) this.jdbcTemplate.queryForObject("select * from administrador where email = ?",new Object[]{email},
					new RowMapper<Administrador>() {

						@Override
						public Administrador mapRow(ResultSet rs, int arg1) throws SQLException {
							Administrador usuarioAdministrador = new Administrador();
							try {
								usuarioAdministrador.setId(rs.getLong("id"));
								usuarioAdministrador.setEmail(rs.getString("email"));
								usuarioAdministrador.setNome(rs.getString("nome"));
								usuarioAdministrador.setRole(AutenticacaoDao.this.getRole(rs.getString("user_role")));
								usuarioAdministrador.setSenha(rs.getString("senha"));
								return usuarioAdministrador;
							} catch (EmptyResultDataAccessException e) {
								e.printStackTrace();
								return null;
							} catch (Exception e) {
								e.printStackTrace();
								return null;
							}
						}
					});
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @param email
	 * @return
	 * Metodo Resposavel por recuperar o Professor
	 */
	private Object getProfessordb(String email) {

		try {
			return (Professor) this.jdbcTemplate.queryForObject("select * from professor where email = ?",new Object[]{email},
					new RowMapper<Professor>() {

						@Override
						public Professor mapRow(ResultSet rs, int arg1) throws SQLException {
							Professor usuarioProfessor = new Professor();
							try {
								usuarioProfessor.setId(rs.getLong("id"));
								usuarioProfessor.setEmail(rs.getString("email"));
								usuarioProfessor.setNome(rs.getString("nome"));
								usuarioProfessor.setRole(AutenticacaoDao.this.getRole(rs.getString("user_role")));
								usuarioProfessor.setSenha(rs.getString("senha"));
								return usuarioProfessor;
							} catch (EmptyResultDataAccessException e) {
								e.printStackTrace();
								return null;
							} catch (Exception e) {
								e.printStackTrace();
								return null;
							}
						}
					});
		} catch (EmptyResultDataAccessException e) {	
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	public final Role getRole(String role) {
		if (role.equals("ROLE_ADMIN")) {
			return Role.ROLE_ADMIN;
		} else if (role.equals("ROLE_PROFESSOR")) {
			return Role.ROLE_PROFESSOR;
		} else {
			return Role.ROLE_ALUNO;
		}
	}
}
