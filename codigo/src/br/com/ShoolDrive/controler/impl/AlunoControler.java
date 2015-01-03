package br.com.ShoolDrive.controler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IAlunoController;
import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.dao.IAlunoDao;
import br.com.ShoolDrive.entidade.Aluno;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.entidade.Role;
import br.com.ShoolDrive.exception.RNException;

@Service
public class AlunoControler implements IAlunoController {

	@Autowired
	private IAlunoDao alunoDao;

	@Autowired
	private IDisciplinaController disciplinaController;

	@Override
	public Aluno findByEmail(String email) {
		return this.alunoDao.findByEmail(email);
	}

	@Override
	public Aluno findById(int id) {
		return this.alunoDao.findById(id);
	}

	@Override
	public Aluno findByRole(Role role) {
		return this.alunoDao.findByRole(role);
	}

	@Override
	public void delete(Long id) {
		this.alunoDao.delete(id);
	}

	@Override
	public void save(Aluno aluno) {
		this.alunoDao.save(aluno);
	}

	@Override
	public List<Aluno> findAll() {
		return (List<Aluno>) this.alunoDao.findAll();
	}

	@Override
	public void registrarDisciplina(Long disciplinaId, String emailAluno) throws RNException {
		Aluno aluno = this.alunoDao.findByEmail(emailAluno);
		Disciplina disciplina = this.disciplinaController.findOne(disciplinaId);
		List<Disciplina> disciplinas;
		if (aluno.getDisciplinas().isEmpty()) {
			disciplinas = new ArrayList<Disciplina>();
			disciplinas.add(disciplina);
			aluno.setDisciplinas(disciplinas);
		}
		else {
			disciplinas = aluno.getDisciplinas();
			for (Disciplina disciplinaRegistrada : disciplinas) {
				if (disciplinaRegistrada.getId() == disciplina.getId()) {
					throw new RNException("Ocorreu um Erro ao Registrar Disciplina !");
				}
			}
			disciplinas.add(disciplina);
			aluno.setDisciplinas(disciplinas);
		}
		this.alunoDao.save(aluno);

	}
}
