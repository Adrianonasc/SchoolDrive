package br.com.ShoolDrive.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IEntregaController;
import br.com.ShoolDrive.dao.IAlunoDao;
import br.com.ShoolDrive.dao.IEntregaDao;
import br.com.ShoolDrive.dao.ITrabalho;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;

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
		return null;
	}

	@Override
	public Entrega findByAlunoAndTrabalho(Long trabalhoId, String emailAluno) {
		Aluno aluno = this.alunoDao.findByEmail(emailAluno);
		Trabalho trabalho = this.trabalhoDao.findOne(trabalhoId);
		return this.entregaDao.findByAlunoAndTrabalho(aluno, trabalho);
	}
}
