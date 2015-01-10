package br.com.ShoolDrive.controler;

import java.util.List;

import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;

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



	/**
	 * 
	 * @param trabalhoId
	 * @param emailAluno
	 * @return
	 * Metodo Resposavel por retornar a entrega do trabalho
	 */
	Entrega findByAlunoAndTrabalho(Long trabalhoId, String emailAluno);


}
