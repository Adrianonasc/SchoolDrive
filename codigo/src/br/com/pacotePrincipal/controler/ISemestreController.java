package br.com.pacotePrincipal.controler;

import br.com.pacotePrincipal.entidade.Semestre;
import br.com.pacotePrincipal.exception.RNException;

public interface ISemestreController {
	
	
	public void fecharSemestre(Long id) throws RNException;
	
	
	public <S extends Semestre> S save(S semestre) throws RNException;
	

	public Iterable<Semestre> findAll();
	
	
	public Semestre findOne(Long id);
}
