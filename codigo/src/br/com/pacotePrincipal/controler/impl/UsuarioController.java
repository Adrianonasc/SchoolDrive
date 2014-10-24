package br.com.pacotePrincipal.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacotePrincipal.controler.IUsuarioControler;
import br.com.pacotePrincipal.dao.AutenticacaoDao;
import br.com.pacotePrincipal.dao.IAdministradorDao;
import br.com.pacotePrincipal.dao.IAlunoDao;
import br.com.pacotePrincipal.dao.IProfessorDao;
import br.com.pacotePrincipal.dao.IUsuarioDao;
import br.com.pacotePrincipal.entidade.Administrador;
import br.com.pacotePrincipal.entidade.Aluno;
import br.com.pacotePrincipal.entidade.Professor;
import br.com.pacotePrincipal.entidade.Role;
import br.com.pacotePrincipal.entidade.Usuario;

@Service
public class UsuarioController implements IUsuarioControler {

	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IAdministradorDao administradorDao;
	@Autowired
	private IAlunoDao alunoDao;
	@Autowired
	private IProfessorDao professorDao;
	@Autowired
	private AutenticacaoDao autenticacaoDao;

	public UsuarioController() {
	}

	@Override
	public void inserir(Administrador administrador, Aluno aluno, Professor professor) {
		if (administrador != null) {
			administradorDao.save(administrador);
		}
		if (aluno != null) {
			alunoDao.save(aluno);
		}
		if (professor != null) {
			professorDao.save(professor);
		}
	}

	@Override
	public void deletar(Long id) {
		usuarioDao.delete(id);
	}

	@Override
	public Usuario findbyEmail(String email) {
		return usuarioDao.findByEmail(email);
	}

	@Override
	public Usuario findbyId(int id) {
		return usuarioDao.findById(id);
	}

	@Override
	public Usuario findbyRole(Role role) {
		return usuarioDao.findByRole(role);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Object procurarEmail(String email) {
		return autenticacaoDao.getbyEmail(email);
	}

}
