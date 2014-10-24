package br.com.pacotePrincipal.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacotePrincipal.controler.IAlunoController;
import br.com.pacotePrincipal.dao.IAlunoDao;
import br.com.pacotePrincipal.entidade.Aluno;
import br.com.pacotePrincipal.entidade.Role;

@Service
public class AlunoControler implements IAlunoController {
	
	@Autowired
	private IAlunoDao alunoDao;
	
	
	@Override
	public Aluno findByEmail(String email) {
		return alunoDao.findByEmail(email);
	}

	@Override
	public Aluno findById(int id) {
		return alunoDao.findById(id);
	}

	@Override
	public Aluno findByRole(Role role) {
		// TODO Auto-generated method stub
		return alunoDao.findByRole(role);
	}

	@Override
	public void delete(Long id) {
		alunoDao.delete(id);
	}

	@Override
	public void save(Aluno aluno) {
		alunoDao.save(aluno);
	}

	@Override
	public List<Aluno> findAll() {
		return (List<Aluno>) alunoDao.findAll();
	}

}