package br.com.ShoolDrive.controler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ShoolDrive.controler.IEntregaController;
import br.com.ShoolDrive.entidade.Entrega;
import br.com.ShoolDrive.entidade.Trabalho;

@Service
public class EntregaController implements IEntregaController {

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

}
