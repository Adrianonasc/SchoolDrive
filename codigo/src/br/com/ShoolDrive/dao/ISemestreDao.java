package br.com.ShoolDrive.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ShoolDrive.entidade.Semestre;

@Repository
public interface ISemestreDao extends CrudRepository<Semestre, Long> {

	/**
	 * Verifica se existe no banco periodo com estatos passado como parametro
	 * @param status
	 * @return a quantidade encontrado
	 */
	long countByStatus(boolean status);
	
	/**
	 * Recupera o semestre com status passado
	 * @param status
	 * @return
	 */
	Semestre findByStatus(boolean status);
	
	Iterable<Semestre> findAllByOrderByStatusDesc(); 
	
	@Override
	 <S extends Semestre> S save(S semestre);
	
	
	@Override
	 Iterable<Semestre> findAll();
	
	
	@Override
	 Semestre findOne(Long id);
	
	
	
	Semestre findByanoSemestre(String anoSemestre);
	
	/**
	 * Metodo Responsavel Por Apagar todos registro dos aluno nas disciplinas
	 */
	@Modifying
	@Transactional
	@Query(value="TRUNCATE registrado_disciplina RESTART IDENTITY",nativeQuery=true)
	void deletarRegistroDisciciplina();
	
}
