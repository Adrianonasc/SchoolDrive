package br.com.ShoolDrive.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IProfessorController;
import br.com.ShoolDrive.dao.IProfessorDao;
import br.com.ShoolDrive.entidade.Professor;
import br.com.ShoolDrive.entidade.Role;
import br.com.ShoolDrive.exception.RNException;
@Service
public class ProfessorControler implements IProfessorController {
	
	@Autowired
	private IProfessorDao professorDao;
	
	@Override
	public Professor findByEmail(String email) {
		return professorDao.findByEmail(email);
	}

	@Override
	public Professor findById(Long id) {
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
	public List<Professor> findAll() throws RNException {
		if (professorDao.count() == 0) {
			throw new RNException("Não ha professor Cadastrado");
		}
		return (List<Professor>) professorDao.findAll();
	}

}
