package br.com.ShoolDrive.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ShoolDrive.controler.IProfessorController;
import br.com.ShoolDrive.entidade.Professor;


/**
 * 
 * 17/12/2014
 * @author Adriano 
 * Classe Responsavel por converter o objeto para view
 */
@Component
public class ConvertProfessor  extends PropertyEditorSupport {
	
	@Autowired
	private IProfessorController professorControler; 
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Professor professor = null;
		
		try {
			 professor = professorControler.findById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setValue(professor);
	}
}
