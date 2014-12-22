package br.com.ShoolDrive.view;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
@Secured("ROLE_PROFESSOR")
public class PrincipalProfessorView {

	public PrincipalProfessorView() {
		
	}
	
	
	
	
}
