package br.com.pacotePrincipal.controler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacotePrincipal.controler.IDisciplinaController;
import br.com.pacotePrincipal.dao.IDisciplinaDao;
import br.com.pacotePrincipal.entidade.Disciplina;

@Service
public class DisciplinaController implements IDisciplinaController{

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

}
