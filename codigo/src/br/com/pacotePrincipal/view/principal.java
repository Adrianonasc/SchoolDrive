package br.com.pacotePrincipal.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.pacotePrincipal.controler.IUsuarioControler;
import br.com.pacotePrincipal.util.AliasPaginas;

@Controller
public class principal {
	
	@Autowired
	private IUsuarioControler usuarioControler;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication authe = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority grant : authe.getAuthorities()) {
			if (grant.getAuthority().equals("ROLE_ADMIN")) {
				model.setViewName(AliasPaginas.HOME_ADMIN);
			} else if (grant.getAuthority().equals("ROLE_ALUNO")) {
				model.setViewName(AliasPaginas.HOME_ALUNO);
			} else if (grant.getAuthority().equals("ROLE_PROFESSOR")) {
				model.setViewName(AliasPaginas.HOME_PROFESSOR);
			}
		}
		model.addObject("nomeUsuario", authe.getName());
		return model;
	}


	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		System.out.println("Saindo");
		return "redirect:/";
	}



}
