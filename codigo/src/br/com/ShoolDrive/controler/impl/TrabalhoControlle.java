package br.com.ShoolDrive.controler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.ITrabalhoController;
import br.com.ShoolDrive.dao.ITrabalho;
import br.com.ShoolDrive.entidade.Disciplina;
import br.com.ShoolDrive.entidade.Trabalho;
import br.com.ShoolDrive.exception.RNException;

@Service
public class TrabalhoControlle implements ITrabalhoController {

    @Autowired
    private ITrabalho trabalhoDao;

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

}
