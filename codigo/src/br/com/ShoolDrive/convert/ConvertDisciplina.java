package br.com.ShoolDrive.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ShoolDrive.controler.IDisciplinaController;
import br.com.ShoolDrive.entidade.Disciplina;

/**
 * 
 * 17/12/2014
 * @author Adriano 
 * Classe Responsavel por converter o objeto para view
 */
@Component
public class ConvertDisciplina extends PropertyEditorSupport {
	
	@Autowired
	private IDisciplinaController disciplinaController; 
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Disciplina disciplina = null;
		
		try {
			disciplina = disciplinaController.findOne(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setValue(disciplina);
	}
}
