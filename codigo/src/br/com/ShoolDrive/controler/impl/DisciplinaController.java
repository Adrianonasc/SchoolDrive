package br.com.ShoolDrive.controler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.dao.IDisciplinaDao;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.exception.RNException;

@Service
public class DisciplinaController implements IDisciplinaController {

	@Autowired
	private IDisciplinaDao disciplinaDao;

	@Override
	public void delete(Long id) {
		disciplinaDao.delete(id);
	}

	@Override
	public Iterable<Disciplina> findAll() {

		return disciplinaDao.findAll();
	}

	@Override
	public Disciplina findOne(Long id) {

		return disciplinaDao.findOne(id);
	}

	@Override
	public <S extends Disciplina> S save(S disciplina) {
		return disciplinaDao.save(disciplina);
	}

	@Override
	public List<Disciplina> listaDisciplina() throws RNException {
		List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
		
		if (disciplinaDao.count() == 0) {
			throw new RNException("Não a Disciplinas Cadastrada!!");
		}
		for (Disciplina disciplina : disciplinaDao.findAll()) {
			if (disciplina.getProfessor() == null) {
				listaDisciplina.add(disciplina);
			}
		}
		if (listaDisciplina.size() == 0) {
			throw new RNException("Não a Disciplinas Sem professor alocado");
		}
		return listaDisciplina;
	}
}
