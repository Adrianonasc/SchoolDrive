package br.com.ShoolDrive.convert;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ShoolDrive.controler.ICursoController;
import br.com.ShoolDrive.entidade.Curso;

@Component
public class ConvertCurso extends PropertyEditorSupport {
	
	@Autowired
	private ICursoController cursoController; 
	
	@Override
	public void setAsText(String id) throws IllegalArgumentException {
		Curso curso = null;
		
		try {
			 curso = cursoController.findById(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setValue(curso);
	}

}
