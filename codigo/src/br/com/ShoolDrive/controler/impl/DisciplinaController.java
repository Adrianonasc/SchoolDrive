package br.com.ShoolDrive.controler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IAlunoController;
import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.dao.IDisciplinaDao;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.exception.RNException;

@Service
public class DisciplinaController implements IDisciplinaController {

	@Autowired
	private IDisciplinaDao disciplinaDao;

	@Autowired
	private IAlunoController AlunoController;

	@Override
	public void delete(Long id) {
		this.disciplinaDao.delete(id);
	}

	@Override
	public Iterable<Disciplina> findAll() {

		return this.disciplinaDao.findAll();
	}

	@Override
	public Disciplina findOne(Long id) {

		return this.disciplinaDao.findOne(id);
	}

	@Override
	public <S extends Disciplina> S save(S disciplina) {
		return this.disciplinaDao.save(disciplina);
	}

	@Override
	public List<Disciplina> listaDisciplina() throws RNException {
		List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();

		if (this.disciplinaDao.count() == 0) {
			throw new RNException("Não a Disciplinas Cadastrada!!");
		}
		for (Disciplina disciplina : this.disciplinaDao.findAll()) {
			if (disciplina.getProfessor() == null) {
				listaDisciplina.add(disciplina);
			}
		}
		if (listaDisciplina.size() == 0) {
			throw new RNException("Não a Disciplinas Sem professor alocado");
		}
		return listaDisciplina;
	}

	@Override
	public Iterable<Disciplina> DisciplinasProfessor(Long idProfesor) {
		return this.disciplinaDao.findByProfessorId(idProfesor);
	}

	@Override
	public List<Disciplina> DisciplinasCurso(Long CursoId, String emailAluno) {
		List<Disciplina> disciplinas = (List<Disciplina>) this.disciplinaDao.findByCursoId(CursoId);
		List<Disciplina> disciplinasRegistrada = this.AlunoController.findByEmail(emailAluno).getDisciplinas();

		for (Disciplina disciplinaRegistrada : disciplinasRegistrada) {
			for (Disciplina disciplinaCurso : disciplinas) {
				if (disciplinaCurso.getId() == disciplinaRegistrada.getId()) {
					disciplinas.remove(disciplinaCurso);
					break;
				}
			}
		}
		return disciplinas;
	}
}
