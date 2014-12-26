package br.com.ShoolDrive.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.entidade.Trabalho;

@Repository
public interface ITrabalho extends CrudRepository<Trabalho, Long> {

    @Override
    public void delete(Trabalho arg0);

    @Override
    public <S extends Trabalho> S save(S arg0);

    @Override
    public Iterable<Trabalho> findAll();

    List<Trabalho> findByDisciplina(Disciplina disciplina);

}
