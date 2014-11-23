package br.com.ShoolDrive.controler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.ICursoController;
import br.com.ShoolDrive.dao.ICursoDao;
import br.com.ShoolDrive.dao.ISemestreDao;
import br.com.ShoolDrive.entidade.Curso;
import br.com.ShoolDrive.entidade.Semestre;

@Service
public class CursoController implements ICursoController{
	
	@Autowired
	private ICursoDao cursoDao;
	
	@Autowired
	private ISemestreDao semestreDao;
	
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
		Semestre semestreCorrente= semestreDao.findByStatus(true); 
		if (semestreCorrente != null) {
			curso.setSemestre(semestreCorrente);
		}
		return cursoDao.save(curso);
	}

	@Override
	public void delete(Long id) {
		cursoDao.delete(id);
		
	}

}
