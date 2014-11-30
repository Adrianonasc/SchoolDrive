package br.com.ShoolDrive.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.ISemestreController;
import br.com.ShoolDrive.dao.IAlunoDao;
import br.com.ShoolDrive.dao.ICursoDao;
import br.com.ShoolDrive.dao.IDisciplinaDao;
import br.com.ShoolDrive.dao.ISemestreDao;
import br.com.ShoolDrive.entidade.Curso;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.entidade.Semestre;
import br.com.ShoolDrive.exception.RNException;
import br.com.ShoolDrive.util.Util;

@Service
public class SemestreController implements ISemestreController {

	@Autowired
	private ISemestreDao semestreDao;

	@Autowired
	private ICursoDao cursoDao;

	@Autowired
	private IDisciplinaDao disciplinaDao;

	@Autowired
	private IAlunoDao alunoDao;

	@Override
	public <S extends Semestre> S save(S semestre) throws RNException {
		// Verifica se tem semestre com o mesmo nome
		Boolean IsSemestreIqual = semestreDao.findByanoSemestre(semestre
				.getAnoSemestre()) != null ? true : false;
		if ((Integer.parseInt(semestre.getAnoSemestre().substring(5)) < 1 || 
			(Integer.parseInt(semestre.getAnoSemestre().substring(5)) > 2 ))) {
			throw new RNException("Semestre Invalido !!");
		}
		if (semestreDao.countByStatus(true) > 0) {
			throw new RNException(
					"Não é possível criar um novo semestre com um semestre aberto!!");
		} else {
			if (semestreDao.count() > 0) {
				if (IsSemestreIqual) {
					throw new RNException(
							"Não é possível criar um semestre com o ano e ou Periodo, menor ou igual aos últimos listados!!");
				}
				/*if (!(Integer.parseInt(semestre.getAnoSemestre().substring(5)) > Integer
						.parseInt(((List<Semestre>) semestreDao
								.findAllByOrderByStatusDesc()).iterator()
								.next().getAnoSemestre().substring(5)))) {
					throw new RNException(
							"Não é possível criar um semestre com o ano e ou Periodo, menor ou igual aos últimos listados!!");
				}*/
			}
			if (!(semestre.getAnoSemestre().substring(0, 4).equals(Util
					.getDataAtual().substring(6, 10)))) {
				throw new RNException(
						"Data de abertura do Semestre diferente do ano Atual !!");
			}

			semestre.setStatus(true);
			semestreDao.save(semestre);
			List<Curso> cursos = (List<Curso>) cursoDao.findAll();
			for (Curso curso : cursos) {
				curso.setSemestre(semestre);
				cursoDao.save(curso);
			}
			return semestre;
		}
	}

	@Override
	public Iterable<Semestre> findAll() {
		return semestreDao.findAllByOrderByStatusDesc();
	}

	@Override
	public Semestre findOne(Long id) {
		return semestreDao.findOne(id);
	}

	@Override
	public void fecharSemestre(Long id) throws RNException {
		Semestre semestre = semestreDao.findOne(id);
		semestre.setStatus(false);
		semestreDao.save(semestre);

		// recupera todas as disciplinas e desaloca todos os professores e
		// atualiza
		List<Disciplina> disciplinas = (List<Disciplina>) disciplinaDao
				.findAll();
		for (Disciplina disciplina : disciplinas) {
			disciplina.setProfessor(null);
			disciplinaDao.save(disciplina);
		}

		// Retira todos os alunos das disciplinas
		semestreDao.deletarRegistroDisciciplina();

		// recupera todos os cursos e retira do semestre atual
		List<Curso> cursos = (List<Curso>) cursoDao.findAll();
		for (Curso curso : cursos) {
			curso.setSemestre(null);
			cursoDao.save(curso);
		}
	}
}
