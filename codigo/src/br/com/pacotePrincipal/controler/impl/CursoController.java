package br.com.pacotePrincipal.controler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacotePrincipal.controler.ICursoController;
import br.com.pacotePrincipal.dao.ICursoDao;
import br.com.pacotePrincipal.entidade.Curso;

@Service
public class CursoController implements ICursoController{
	
	@Autowired
	private ICursoDao cursoDao;
	
	
	@Override
	public Curso findById(Long id) {
		return cursoDao.findById(id);
	}

	@Override
	public Iterable<Curso> findAll() {
		return cursoDao.findAll();
	}

	@Override
	public <S extends Curso> S save(S curso) {
		
		return cursoDao.save(curso);
	}

	@Override
	public void delete(Long id) {
		cursoDao.delete(id);
		
	}

}
