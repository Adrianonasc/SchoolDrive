package br.com.ShoolDrive.controler.impl;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IEntregaController;
import br.com.ShoolDrive.dao.IAlunoDao;
import br.com.ShoolDrive.dao.IEntregaDao;
import br.com.ShoolDrive.dao.ITrabalho;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;
import br.com.ShoolDrive.exception.RNException;

@Service
public class EntregaController implements IEntregaController {

	@Autowired
	private ITrabalho trabalhoDao;

	@Autowired
	private IEntregaDao entregaDao;

	@Autowired
	private IAlunoDao alunoDao;


	@Override
	public void delete(Entrega entrega) {

	}

	@Override
	public Iterable<Entrega> findAll() {
		return null;
	}

	@Override
	public void save(Entrega entrega) {

	}

	@Override
	public List<Entrega> findByTrabalho(Trabalho trabalho) {
		return this.entregaDao.findByTrabalho(trabalho);
	}

	@Override
	public Entrega findByAlunoAndTrabalho(Long trabalhoId, String emailAluno) {
		Aluno aluno = this.alunoDao.findByEmail(emailAluno);
		Trabalho trabalho = this.trabalhoDao.findOne(trabalhoId);
		return this.entregaDao.findByAlunoAndTrabalho(aluno, trabalho);
	}

	@Override
	public Entrega findOne(Long entregaId) {
		return this.entregaDao.findOne(entregaId);
	}

	@Override
	public void publicarNotas(Map<String, String> notas, Trabalho trabalho) throws RNException {
		List<Entrega> entregas = this.entregaDao.findByTrabalho(trabalho);
		DateTime dataTrabalho = new DateTime(trabalho.getDataLimite());
		for (String alunoId : notas.keySet()) {
			for (Entrega entrega : entregas) {
				if (entrega.getAluno().getId() == Long.parseLong(alunoId)) {
					entrega.setNota(notas.get(alunoId));
				}
			}
		}

		if (dataTrabalho.isBeforeNow()) {
			throw new RNException("Atualizaçoes de notas fora do prazo");
		}
		else {
			// atualiza todas as entregas
			this.entregaDao.save(entregas);
		}
	}
}
