package br.com.pacotePrincipal.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacotePrincipal.controler.IProfessorController;
import br.com.pacotePrincipal.dao.IProfessorDao;
import br.com.pacotePrincipal.entidade.Professor;
import br.com.pacotePrincipal.entidade.Role;
@Service
public class ProfessorControler implements IProfessorController {
	
	@Autowired
	private IProfessorDao professorDao;
	
	@Override
	public Professor findByEmail(String email) {
		return professorDao.findByEmail(email);
	}

	@Override
	public Professor findById(int id) {
		return professorDao.findById(id);
	}

	@Override
	public Professor findByRole(Role role) {
		return professorDao.findByRole(role);
	}

	@Override
	public void delete(Long id) {
		professorDao.delete(id);
		
	}

	@Override
	public void save(Professor professor) {
		professorDao.save(professor);
		
	}

	@Override
	public List<Professor> findAll() {		
		return (List<Professor>) professorDao.findAll();
	}

}
