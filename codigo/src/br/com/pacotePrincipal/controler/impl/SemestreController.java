package br.com.pacotePrincipal.controler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pacotePrincipal.controler.ISemestreController;
import br.com.pacotePrincipal.dao.ISemestreDao;
import br.com.pacotePrincipal.entidade.Semestre;
import br.com.pacotePrincipal.exception.RNException;
import br.com.pacotePrincipal.util.Util;

@Service
public class SemestreController implements ISemestreController {

	@Autowired
	private ISemestreDao semestreDao;
	
	
	@Override
	public <S extends Semestre> S save(S semestre) throws RNException {
		if (!(semestre.getAnoSemestre().substring(0, 4).equals(Util.getDataAtual().substring(6, 10)))) {
			throw new RNException("Data de abertura do Semestre diferente do ano Atual !!");
		}
		if (semestreDao.countByStatus(true) > 0) {
			throw new RNException("Já Existe Semestre em aberto !!");
		}else{
			semestre.setStatus(true);
			return semestreDao.save(semestre);
		}
	}

	@Override
	public Iterable<Semestre> findAll() {
		return semestreDao.findAllByOrderByStatusDesc();
	}

	@Override
	public Semestre findOne(Long id) {
		return semestreDao.findOne(id);
	}

	@Override
	public void fecharSemestre(Long id) throws RNException {
		Semestre semestre = semestreDao.findOne(id);
		semestre.setStatus(false);
		semestreDao.save(semestre);
		/**
		 * Falta fazer toda a logica de fechamento de fechamento
		 */
		
	}


}
