package br.com.ShoolDrive.controler.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.ITrabalhoController;
import br.com.ShoolDrive.dao.IAlunoDao;
import br.com.ShoolDrive.dao.IEntregaDao;
import br.com.ShoolDrive.dao.ITrabalho;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;
import br.com.ShoolDrive.exception.RNException;

@Service
public class TrabalhoControlle implements ITrabalhoController {

	@Autowired
	private ITrabalho trabalhoDao;

	@Autowired
	private IEntregaDao entregaDao;

	@Autowired
	private IAlunoDao alunoDao;

	@Override
	public void save(Trabalho trabalho) throws RNException {
		this.trabalhoDao.save(trabalho);
	}

	@Override
	public void delete(Trabalho trabalho) throws RNException {
		this.trabalhoDao.delete(trabalho);
	}

	@Override
	public Iterable<Trabalho> findAll() throws RNException {
		return this.trabalhoDao.findAll();
	}

	@Override
	public List<Trabalho> findByDisciplina(Disciplina disciplina) throws RNException {
		return this.trabalhoDao.findByDisciplina(disciplina);
	}

	@Override
	public Trabalho findOne(Long id) {
		return this.trabalhoDao.findOne(id);
	}

	@Override
	public void EntregaTrabalho(Entrega entrega, String emailALuno, Long trabalhoId) throws RNException {
		try {
			Date dataAutal = DateTime.now().toDate();
			Trabalho trabalho = this.trabalhoDao.findOne(trabalhoId);
			DateTime dataLimiteTrabalho = new DateTime(trabalho.getDataLimite());
			entrega.setDataEntrega(dataAutal);
			entrega.setAluno(this.alunoDao.findByEmail(emailALuno));
			if ((trabalho != null) || !dataLimiteTrabalho.isBeforeNow()) {
				entrega.setTrabalho(trabalho);
			}
			this.entregaDao.save(entrega);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean verificarEntrega(Long trabalhoId, String emailAluno) {
		Trabalho trabalho = this.trabalhoDao.findOne(trabalhoId);
		Aluno aluno = this.alunoDao.findByEmail(emailAluno);
		Entrega entrega = this.entregaDao.findByAlunoAndTrabalho(aluno, trabalho);
		if (entrega != null) {
			return true;
		}
		return false;
	}
}
