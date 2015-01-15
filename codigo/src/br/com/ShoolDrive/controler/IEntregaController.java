package br.com.ShoolDrive.controler;

import java.util.List;
import java.util.Map;

import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;
import br.com.ShoolDrive.exception.RNException;

public interface IEntregaController {

	void delete(Entrega entrega);

	Iterable<Entrega> findAll();

	void save(Entrega entrega);

	/**
	 * @param trabalho
	 * @return List<Entrega> Metodo Resposavel por retornar todas as entregas do
	 *         trabalho
	 */
	List<Entrega> findByTrabalho(Trabalho trabalho);

	public Entrega findOne(Long entregaId);

	/**
	 * 
	 * @param trabalhoId
	 * @param emailAluno
	 * @return
	 * Metodo Resposavel por retornar a entrega do trabalho
	 */
	Entrega findByAlunoAndTrabalho(Long trabalhoId, String emailAluno);


	/***
	 * 
	 * 
	 * @param HashMap notas <p>
	 * @param Trabalho trabalho <p>
	 * <b>Key</b> = ID do Aluno <p>
	 * <b>Value</b> = Nota <p>
	 * Metodo Resposavel por publicar as notas dos alunos
	 */
	void publicarNotas(Map<String, String> notas, Trabalho trabalho) throws RNException;


}
